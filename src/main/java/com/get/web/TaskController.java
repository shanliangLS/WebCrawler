package com.get.web;

import com.get.comm.aop.LoggerManage;
import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.crawl.repository.WebSiteCrawlPolicyRepository;
import com.get.domain.Information;
import com.get.domain.Task;
import com.get.domain.WebSiteSubtype;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.InformationRepository;
import com.get.repository.TaskRepository;
import com.get.repository.ThemeRepository;
import com.get.repository.WebSiteSubtypeRepository;
import com.get.util.*;
import com.google.gson.Gson;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.get.crawl.Crawl.*;

@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController {
    @Autowired
    private TaskRepository taskRepository;
    private ThemeRepository themeRepository;
    private WebSiteSubtypeRepository subtypeRepository;
    private WebSiteCrawlPolicyRepository policyRepository;

    @Autowired
    private InformationRepository informationRepository;

    @RequestMapping(value = "/doCreateTask", method = RequestMethod.POST)
    @LoggerManage(description = "创建任务")
    public AjaxResult doCreateTask(String name, String themeId) {
        try {

            Gson gson = new Gson();
            //需要给我name和themeId
            //id userId <name> createTime flag start end listId

            Long userId = getUserId();
            Task findTask = taskRepository.findTaskByNameAndUserId(name, userId);
            if (findTask != null) {//任务名称已存在
                return failAjax(ExceptionMsg.TaskNameUsed);
            }

            //以下是创建新的任务
            Task task = new Task();
            task.setUserId(userId);
            task.setName(name);
            task.setCreateTime(System.currentTimeMillis());
            task.setFlag(0);//未执行
            task.setStart(0L);
            task.setEnd(0L);
            task.setCompletedNum(0L);
            String themeIds[] = themeId.split(",");

            List<Long> listId = new ArrayList<Long>();
            for (int i = 0; i < themeIds.length; i++) {
                List<Long> temp = themeRepository.findThemeById(Long.parseLong(themeIds[i])).getListId();
//                for (int j=0;j<temp.size();j++){
//                    listId.add(temp.get(j));
//                }
                listId.addAll(temp);
            }
            task.setListId(listId);
            taskRepository.save(task);
            return successAjax();
        } catch (Exception e) {
            logger.error("创建任务失败", e);
            return errorAjax();
        }
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    @LoggerManage(description = "获取所有任务")
    public AjaxResult getTask() {

        try {
            Long userId = getUserId();
            List<Task> findTasks = taskRepository.findTaskByUserId(userId);
            if (findTasks == null) {//没有任务
                return failAjax(ExceptionMsg.NoTask);
            }
            //以下是返回任务
//            taskRepository.deleteTaskById(task);
            return successAjax(findTasks);
        } catch (Exception e) {
            logger.error("删除任务失败", e);
            return errorAjax();
        }


    }


    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    @LoggerManage(description = "删除任务")
    public AjaxResult deleteTask(Task task) {
        try {
            Long userId = getUserId();
            Task findTask = taskRepository.findTaskByIdAndUserId(task.getId(), userId);
            if (findTask == null) {//任务不存在，无法删除
                return failAjax(ExceptionMsg.TaskNotExist);
            }
            //以下是删除任务
            taskRepository.deleteTaskById(task);
            return successAjax();
        } catch (Exception e) {
            logger.error("删除任务失败", e);
            return errorAjax();
        }
    }


    @RequestMapping(value = "/startTask", method = RequestMethod.POST)
    @LoggerManage(description = "开始任务")
    public AjaxResult startTask(String sid) {
        try {
            Long userId = getUserId();
//            Task findTask = taskRepository.findTaskByIdAndUserId(task.getId(),userId);
//            if (findTask == null) {//任务不存在，无法删除
//                return failAjax(ExceptionMsg.TaskNotExist);
//            }
//            //以下是删除任务
//            taskRepository.deleteTaskById(task);

            Long id = Long.parseLong(sid);
            Task task = taskRepository.findTaskByIdAndUserId(id, userId);
            if (task == null) {
                return failAjax(ExceptionMsg.TaskNotExist);
            }
            if(task.getFlag() != 0)
            {
                // 已经启动或者结束
                System.out.println("任务已经启动");
                return failAjax(ExceptionMsg.FAILED);
            }
            taskRepository.setTaskCompletedNum(getUserId(), task.getId(), 0);
            List<Long> listId = task.getListId();
            for (Long aListId : listId) {
                WebSiteSubtype subtype = subtypeRepository.findWebSiteSubtypeById(aListId);
                if (subtype == null) {
                    return failAjax(ExceptionMsg.ParamError);
                }
                WebSiteCrawlPolicy policy = policyRepository.findWebSiteCrawlPolicyById(subtype.getSiteId());
                if (policy == null) {
                    return failAjax(ExceptionMsg.ParamError);
                }
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            crawlAllList(policy, subtype.getUrl(), task.getId());
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return successAjax();
        } catch (Exception e) {
            logger.error("删除任务失败", e);
            return errorAjax();
        }
    }


    /**
     * 爬取一个list页面
     *
     * @param crawlPolicy policy
     * @param url         url
     * @return next url
     */
    private String crawlOneList(WebSiteCrawlPolicy crawlPolicy, String url, Long taskId) {
        final int downloadType = crawlPolicy.getListDownloadType();

        Document doc = getDocument(url, downloadType);
        if (doc != null) {
            List<String> articleUrlList = getListLinks(doc, crawlPolicy, url);
            System.out.printf("筛选页面:%s,得到文章链接:%s\n", url, articleUrlList);
            if (articleUrlList != null && articleUrlList.size() > 0) {
                for (String articleUrl : articleUrlList) {
                    crawlOneArticle(crawlPolicy, articleUrl, taskId);
                }
            }
            return getNextPageUrl(crawlPolicy, doc);
        }
        return null;
    }


    /**
     * 爬取一个文章
     */
    private void crawlOneArticle(WebSiteCrawlPolicy crawlPolicy, String url, Long taskId) {
        if (url.endsWith(".pdf")) {
            return;
        }
        final int downloadType = crawlPolicy.getArticleDownloadType();
        final Document document = getDocument(url, downloadType);
        if (document != null) {
            // 作者
            String author = getOneText(document, crawlPolicy.getAuthorSelectorType(), crawlPolicy.getAuthorSelector());
            if (StringUtil.isEmpty(author)) {
                author = crawlPolicy.getName();
            }
            // 标题
            String title = getOneText(document, crawlPolicy.getTitleSelectorType(), crawlPolicy.getTitleSelector());
            if (StringUtil.isEmpty(title)) {
                title = "";
            }
            // 内容
            final String content = getOneText(document, crawlPolicy.getContentSelectorType(), crawlPolicy.getContentSelector());
            // 时间
            final String time = getOneText(document, crawlPolicy.getTimeSelectorType(), crawlPolicy.getTimeSelector());
            // 图片 url
            final String photoUrl = getOnePhoto(document, crawlPolicy.getPhotoCss(), url);

            // 关键词
            String keywords = JiebaUtil.getKeyWords(title + content);
            // 类别
            String aClass = NewsUtil.classify(title + content);

            Information information = new Information();
            // 作者
            information.setAuthor(author);
            // 内容
            information.setContent(content);
            // 爬去时间
            information.setCreateTime(System.currentTimeMillis());
            // 设置photo url
            information.setPicture(photoUrl);
            // 设置标题
            information.setTitle(title);
            // 设置关键词
            information.setKeyWords(transKeyWordToList(keywords));
            // 设置url
            information.setUrl(url);
            // 设置时间
            information.setTime(time);
            // 设置类别
            information.setClasses(transClassesToId(aClass));
            // 设置任务id
            information.setTaskId(taskId);

            Gson gson = new Gson();
            System.out.println(gson.toJson(information));
            System.out.printf("图片为:%s\n", photoUrl);
            System.out.printf("摘要为:%s\n", SnowNlpUtil.getZy(title + content));
            System.out.printf("关键词为:%s\n", keywords);
            informationRepository.save(information);
        }
    }

    /**
     * 爬取所有页面
     *
     * @param crawlPolicy policy
     * @param url         url
     */
    public void crawlAllList(WebSiteCrawlPolicy crawlPolicy, String url, Long taskId) {
        int i = 0;
        String nextPageUrl = url;
        while (true) {
            nextPageUrl = crawlOneList(crawlPolicy, nextPageUrl, taskId);
            if (StringUtil.isEmpty(nextPageUrl)) {
                break;
            }
            i++;
            if (i > 1) {
                break;
            }
            nextPageUrl = UrlUtil.join(url, nextPageUrl);
            System.out.printf("下一页url:%s\n", nextPageUrl);
        }
    }

    /**
     * 将，分割的字符串转化成list
     *
     * @param keywords 关键词
     * @return keyword list
     */
    private static List<String> transKeyWordToList(String keywords) {
        if (keywords == null || "".equals(keywords)) {
            return null;
        } else {
            List<String> ss = new ArrayList<>();
            Collections.addAll(ss, keywords.split(","));
            if (ss.size() <= 0) {
                return null;
            }
            return ss;
        }
    }

    /**
     * 将类别转化成id
     *
     * @param aClass 类别
     * @return id
     */
    private static Long transClassesToId(String aClass) {
        String[] classes = {"其他", "体育", "财经", "房产", "家居", "教育", "科技", "时尚", "时政", "游戏", "娱乐"};
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].equals(aClass)) {
                return (long) i;
            }
        }
        return 0L;
    }

    public static void main(String[] args) {
        String s = "1,2,";
        System.out.println(transKeyWordToList(s));
    }

}
