package com.get.web;

import com.get.comm.aop.LoggerManage;
import com.get.domain.Information;
import com.get.domain.Task;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.InformationRepository;
import com.get.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/info")
public class InformationController extends BaseController {

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/getAllInformationList",method = RequestMethod.POST)
    @LoggerManage(description = "返回该用户所爬取的信息列表")
    public  AjaxResult getAllInformationList(){
        try {
            Long userId = getUserId();
            //任务id
//            根据任务寻找information
//            返回=》information（除了content）=》根据task_id找到表task（name）

//
            //任务id   （task ）   标题   关键字    发布时间 爬取时间 （information）   分类名称（classes）


            List<Task> taskList = taskRepository.findAllByUserId(userId);
            List<Information> informationList = new ArrayList<>();
            class ReturnData{
                private Long id;
                private Long taskId;
                private String taskName;
                private Long createTime;
                private String title;
                private List<String> keyWords;
                private String time;
                private String className;

                public Long getTaskId() {
                    return taskId;
                }

                public String getTaskName() {
                    return taskName;
                }

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public Long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Long createTime) {
                    this.createTime = createTime;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<String> getKeyWords() {
                    return keyWords;
                }

                public void setKeyWords(List<String> keyWords) {
                    this.keyWords = keyWords;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getClassName() {
                    return className;
                }

                public void setClassName(String className) {
                    this.className = className;
                }

                public void setTaskId(Long taskId) {
                    this.taskId = taskId;
                }

                public void setTaskName(String taskName) {
                    this.taskName = taskName;
                }
            }
            List<ReturnData> returnData=new ArrayList<ReturnData>();

            for (int i=0;i<taskList.size();i++) {

                List<Information> informationList1 = informationRepository.findAllExcludeContentByTaskId(taskList.get(i).getId());
                for (int j=0;j<informationList1.size();j++){
                    ReturnData tempReturnData=new ReturnData();
                    tempReturnData.setTaskId(taskList.get(i).getId());//初始化一条返回数据的taskId
                    tempReturnData.setTaskName(taskList.get(i).getName());

                    tempReturnData.setId(informationList1.get(j).getId());
                    tempReturnData.setTitle(informationList1.get(j).getTitle());//标题
                    tempReturnData.setCreateTime(informationList1.get(j).getCreateTime());//爬取时间
                    tempReturnData.setTime(informationList1.get(j).getTime());//发布时间
                    tempReturnData.setKeyWords(informationList1.get(j).getKeyWords());//关键字
                    String name= informationRepository.selectClassesNameById(informationList1.get(j).getClasses());
                    tempReturnData.setClassName(name);
                    returnData.add(tempReturnData);
                }
            }

            return successAjax(returnData);
        }catch (Exception e){
            e.printStackTrace();
            return errorAjax();
        }
    }


    @RequestMapping(value = "/getDetailInformation",method = RequestMethod.POST)
    @LoggerManage(description = "返回该用户所爬取的具体信息")
    public  AjaxResult getDetailInformation(String sid) {
        try {
            Long userId = getUserId();
//            title，content，author，url，picture
            class ReturnDetailData {
                private Long id;
                private String title;
                private String content;
                private String author;
                private String url;
                private String picture;

                public ReturnDetailData() {
                    id=0L;
                    title="";
                    content="";
                    author="";
                    url="";
                    picture="";
                }

                public Long getId() {
                    return id;
                }

                public String getTitle() {
                    return title;
                }

                public String getContent() {
                    return content;
                }

                public String getAuthor() {
                    return author;
                }

                public String getUrl() {
                    return url;
                }

                public String getPicture() {
                    return picture;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
                }
            }
            Long id=Long.parseLong(sid);
            Information information= informationRepository.selectInformationDetailById(id);

            ReturnDetailData returnDetailData =new ReturnDetailData();
            returnDetailData.setId(information.getId());
            returnDetailData.setTitle(information.getTitle());
            returnDetailData.setAuthor(information.getAuthor());
            returnDetailData.setContent(information.getContent());
            returnDetailData.setUrl(information.getUrl());
            returnDetailData.setPicture(information.getPicture());
            return  successAjax(returnDetailData);

        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }

    @RequestMapping(value = "/all")
    public AjaxResult all() {
        try {
            Long userId = getUserId();
            List<Task> taskList = taskRepository.findAllByUserId(userId);
            List<Information> informationList = new ArrayList<>();
            for (Task task : taskList) {
                List<Information> informationList1 = informationRepository.findAllByTaskId(task.getId());
                if (informationList1 == null || informationList1.size() <= 0) {
                    continue;
                }
                informationList.addAll(informationList1);
            }
            return successAjax(informationList);
        } catch (Exception e) {
            e.printStackTrace();
            return errorAjax();
        }
    }
}
