package com.get.repository;

import com.get.domain.Information;
import com.get.domain.ReturnInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnInformationRepository extends JpaRepository<ReturnInformation,Long> {
    @Query(value = "select I.id,title,I.create_time,time,task_id,name from information AS I,task where I.task_id in (select id from task where user_id=?1) and task.id=I.task_id",nativeQuery = true)
//    select I.id,title,I.create_time,time,task_id,name from information AS I,task where I.task_id in (select id from task where user_id=1) and task.id=I.task_id;
//    @Query("select Task.id,title,Task.createTime,time,taskId,name,keyWords from Information,Task where I.taskId in (select id from Task where userId=?1)")
    List<ReturnInformation> selectInformationEumByUserId(Long userId);
//    {550, “中国电子信息工程科技发展十大趋势”在工程院发布, 1577621432686, 2019-12-23, 4, 0}
}
