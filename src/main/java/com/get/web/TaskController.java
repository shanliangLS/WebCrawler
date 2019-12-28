package com.get.web;

import com.get.comm.aop.LoggerManage;
import com.get.crawl.Crawl;
import com.get.crawl.domain.WebSiteCrawlPolicy;
import com.get.crawl.repository.WebSiteCrawlPolicyRepository;
import com.get.domain.Task;
import com.get.domain.Theme;
import com.get.domain.WebSiteSubtype;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.TaskRepository;
import com.get.repository.ThemeRepository;
import com.get.repository.WebSiteSubtypeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static javafx.scene.input.KeyCode.L;

@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController{
    @Autowired
    private TaskRepository taskRepository;
    private ThemeRepository themeRepository;
    private WebSiteSubtypeRepository subtypeRepository;
    private WebSiteCrawlPolicyRepository policyRepository;

    @RequestMapping(value = "/doCreateTask", method = RequestMethod.POST)
    @LoggerManage(description = "创建任务")
    public AjaxResult doCreateTask(String name,String themeId) {
        try {

            Gson gson=new Gson();
            //需要给我name和themeId
            //id userId <name> createTime flag start end listId

            Long userId = getUserId();
            Task findTask = taskRepository.findTaskByNameAndUserId(name, userId);
            if (findTask != null) {//任务名称已存在
                return failAjax(ExceptionMsg.TaskNameUsed);
            }

            //以下是创建新的任务
            Task task=new Task();
            task.setUserId(userId);
            task.setName(name);
            task.setCreateTime(System.currentTimeMillis());
            task.setFlag(0);//未执行
            task.setStart(0L);
            task.setEnd(0L);
            String themeIds[]=themeId.split(",");

            List<Long> listId= new ArrayList<Long>();
            for(int i=0;i<themeIds.length;i++){
                List<Long> temp=themeRepository.findThemeById(Long.parseLong(themeIds[i])).getListId();
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
    public AjaxResult getTask(){

        try {
            Long userId = getUserId();
            List<Task> findTasks = taskRepository.findTaskByUserId(userId);
            if (findTasks == null) {//没有任务
                return failAjax(ExceptionMsg.NoTask);
            }
            //以下是返回任务
//            taskRepository.deleteTaskById(task);
            return successAjax(findTasks);
        }catch (Exception e) {
            logger.error("删除任务失败", e);
            return errorAjax();
        }


    }



    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    @LoggerManage(description = "删除任务")
    public AjaxResult deleteTask(Task task){
        try {
            Long userId = getUserId();
            Task findTask = taskRepository.findTaskByIdAndUserId(task.getId(),userId);
            if (findTask == null) {//任务不存在，无法删除
                return failAjax(ExceptionMsg.TaskNotExist);
            }
            //以下是删除任务
            taskRepository.deleteTaskById(task);
            return successAjax();
        }catch (Exception e) {
            logger.error("删除任务失败", e);
            return errorAjax();
        }
    }
    @RequestMapping(value = "/startTask", method = RequestMethod.POST)
    @LoggerManage(description = "开始任务")
//    public AjaxResult startTask(String sid){
//        try {
//            Long userId = getUserId();
////            Task findTask = taskRepository.findTaskByIdAndUserId(task.getId(),userId);
////            if (findTask == null) {//任务不存在，无法删除
////                return failAjax(ExceptionMsg.TaskNotExist);
////            }
////            //以下是删除任务
////            taskRepository.deleteTaskById(task);
//
//            Long id=Long.parseLong(sid);
//            Task task = taskRepository.findTaskByIdAndUserId(id,userId);
//
//
//
//
//            try {
//                WebSiteSubtype subtype = subtypeRepository.findWebSiteSubtypeById(0L);
//                if (subtype == null) {
//                    return failAjax(ExceptionMsg.ParamError);
//                }
//                Gson gson = new Gson();
//                System.out.println(gson.toJson(subtype));
//
//                WebSiteCrawlPolicy policy = policyRepository.findWebSiteCrawlPolicyById(subtype.getSiteId());
//                if (policy == null) {
//                    return failAjax(ExceptionMsg.ParamError);
//                }
//                System.out.println(gson.toJson(policy));
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Crawl.crawlAllList(policy, subtype.getUrl());
//                    }
//                }).start();
//                return successAjax();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return errorAjax();
//            }
//
//
//
//
//
//
//
//
//
//
//            return successAjax();
//        }catch (Exception e) {
//            logger.error("删除任务失败", e);
//            return errorAjax();
//        }
//    }


    private void startCrawl(Long sub){


    }
}
