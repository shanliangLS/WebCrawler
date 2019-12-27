package com.get.repository;

import com.get.domain.Task;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByNameAndUserId(String name,Long userId);
    Task findTaskByIdAndUserId(Long id,Long userId);
    Task findTaskByIdAndNameAndUserId(Long id ,String name,Long userId);

    /**
     * 新增任务
     *
     */




    /**
     * 删除任务
     *
     */
    @Modifying
    @Transactional
    @Query(value = "delete from task where id=#{#task.id} and userId=#{#task.userId}",nativeQuery=true)
    void deleteTaskById(@Param("task") Task task);


}
