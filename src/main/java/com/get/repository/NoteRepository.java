package com.get.repository;

import com.get.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByUserIdOrderByCreateTimeDesc(Long userId);

    @Transactional
    int deleteNoteByIdAndUserId(Long id, Long userId);

    Note findNoteByIdAndUserId(Long id, Long userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Note set record =:record where id =:id and userId =:userId")
    int setRecord(@Param("record") String record, @Param("id") Long id, @Param("userId") Long userId);
}
