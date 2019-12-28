package com.get.repository;

import com.get.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByNameAndUserId(String name, Long userId);

    Task findTaskByIdAndUserId(Long id, Long userId);

    Task findTaskByIdAndNameAndUserId(Long id, String name, Long userId);

    List<Task> findTaskByUserId(Long userId);

    /**
     * 新增任务
     *
     */


    /**
     * 删除任务
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from task where id=#{#task.id} and userId=#{#task.userId}", nativeQuery = true)
    void deleteTaskById(@Param("task") Task task);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Task set completedNum=:num where userId=:userId and id =:taskId")
    int setTaskCompletedNum(@Param("userId") Long userId, @Param("taskId") Long taskId, @Param("num") Integer num);

    /**
     * 添加时间节点
     *
     */
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update Task set start=:start where userId=:userId and id =:taskId")
//    int updateTaskStartByIdAndUserId(@Param("id") Long id,@Param("userId") Long userId);
//
//    /**
//     * 已完成数量加一
//     *
//     */
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update Task set completedNum=completedNum+1 where id=:id")
//    int updateTaskCompletedNumById(@Param("id") Long id);
//
//
//    @Query("SELECT completedNum from Task where id=?1 and userId=?2")
//    Long selectTaskComletedNumByIdAndUserId(Long id , Long userId);


}
