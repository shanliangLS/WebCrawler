package com.get.web;

import com.get.domain.Information;
import com.get.domain.Task;
import com.get.domain.res.AjaxResult;
import com.get.domain.res.ExceptionMsg;
import com.get.repository.InformationRepository;
import com.get.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
