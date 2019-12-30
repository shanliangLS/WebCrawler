package com.get.repository;

import com.get.domain.Information;
import com.get.domain.InformationKeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationKeyWordRepository extends JpaRepository<InformationKeyWord,Long> {

/**
 *
 */

        @Query(value = "select id, information_id,key_words from information_key_words where information_id  in (select distinct id from information where task_id in (select id from task where user_id=?1));",nativeQuery = true)
    List<InformationKeyWord> selectKeyWordByUserId(Long userId);
}
