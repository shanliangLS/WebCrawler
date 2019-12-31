package com.get.repository;

import com.get.domain.Information;
import com.get.domain.ReturnInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

    Information findInformationById(Long id);

    List<Information> findAllByTaskId(Long taskId);


    /**
     * 返回该用户除去内容的所有信息
     *
     * @param taskId
     * @return
     */
    @Query(value = "select * from information", nativeQuery = true)
    List<Information> findAllExcludeContentByTaskId(Long taskId);

    /**
     * 根据class查找表classes中的name
     */
    @Query(value = "select name from classes where id=?1", nativeQuery = true)
    String selectClassesNameById(Long id);

    /**
     * 返回信息的具体内容，id，title，content，author，url，picture
     */
    @Query(value = "select * from information where id=?1", nativeQuery = true)
    Information selectInformationDetailById(Long id);

    //    @Transactional
//    @Query("select id,title,createTime,author,content,picture,near,keyWords,classes from Information where classes=:classes")
//    List<Information> getByClasses(@Param("classes") String classes);
}
