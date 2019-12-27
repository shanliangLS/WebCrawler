package com.get.web;

import com.get.comm.aop.LoggerManage;
import com.get.domain.Task;
import com.get.domain.Theme;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController{
    @Autowired
    private TaskRepository taskRepository;


    @RequestMapping(value = "/doCreateTask", method = RequestMethod.POST)
    @LoggerManage(description = "创建任务")
    public AjaxResult doCreateTask(Task task) {
        try {
            Long userId = getUserId();
            Task findTask = taskRepository.findTaskByNameAndUserId(task.getName(), userId);
            if (findTask != null) {//任务名称已存在
                return failAjax(ExceptionMsg.TaskNameUsed);
            }
            //以下是创建新的任务
            task.setUserId(userId);

            taskRepository.save(task);
            return successAjax();
        } catch (Exception e) {
            logger.error("创建任务失败", e);
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


}
