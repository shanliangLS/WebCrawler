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

    List<Task> findAllByUserId(Long userId);

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
    @Query(value = "delete from task_list_id where task_id=?1",nativeQuery = true)
    void deleteTaskListIdByTaskId(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from task_theme_ids where task_id=?1",nativeQuery = true)
    void deleteTaskThemeIdsByTaskId(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from task where id=?1 and user_id=?2",nativeQuery = true)
    void deleteTaskById(Long id,Long userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Task set completedNum=:num where userId=:userId and id =:taskId")
    int setTaskCompletedNum(@Param("userId") Long userId, @Param("taskId") Long taskId, @Param("num") Integer num);


    /**
     * 添加开始时间节点
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Task set start=:start where userId=:userId and id =:id")
    int updateTaskStartByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId, @Param("start") Long start);

    /**
     * 添加结束时间节点
     *
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update task set end=?3 where user_id=?2 and id =?1",nativeQuery = true)
    int updateTaskEndByIdAndUserId(Long id , Long userId, Long end);

    /**
     * 已完成数量加一
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Task set completedNum=completedNum+1 where id=:id")
    int updateTaskCompletedNumById(@Param("id") Long id);

    /**
     * 查询当前已完成的数据
     *
     * @param id
     * @param userId
     * @return
     */
    @Query("SELECT completedNum from Task where id=?1 and userId=?2")
    int selectTaskCompletedNumByIdAndUserId(Long id, Long userId);

    /**
     * 查询总的需要完成的数据
     */
    @Query(value = "select count(distinct list_id) from task_list_id where task_id=?1", nativeQuery = true)
    int selectTaskListIdCountListIdByTaskId(Long id);
}
