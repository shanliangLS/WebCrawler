package com.get.repository;

import com.get.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface InformationRepository extends JpaRepository<Information,Long> {

    Information findInformationById(Long id);

    List<Information> findAllByTaskId(Long taskId);

    //    @Transactional
//    @Query("select id,title,createTime,author,content,picture,near,keyWords,classes from Information where classes=:classes")
//    List<Information> getByClasses(@Param("classes") String classes);
}
