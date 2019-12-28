package com.get.repository;

import com.get.domain.Theme;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import com.get.domain.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

//    Theme findThemeByIdAndUserId(Long id,Long userId);
    Theme findThemeById(Long id);
    Theme findThemeByIdAndUserId(Long id ,Long userId);


    List<Theme> findThemesByUserId(Long userId);

    Theme findThemeByUserIdAndName(Long userId, String name);


    /**
     * 查询改名之后的主题名是否冲突
     * @param
     * @return 0=>无冲突  大于1=>有冲突
     */

    @Query(value = "select id,user_id,name from theme where name=?1 and id!=?2 and user_id=?3",nativeQuery = true)
    List<Theme> selectName(Long id,String name,Long userId);
//    @Query("update User set name=:name where email=:email")
//    int setName(@Param("name") String name, @Param("email") String email);
//    /**
//     * 根据多个id查询主题
//     * @param theme
//     */
//    @Query(value = "",nativeQuery = true)
//
    /*
     * 更新主题
     */
    @Modifying
    @Transactional
    @Query("update Theme set name=?1 where id=?2 and userId=?3")
    void updateThemeByIdAndUserId(String name,Long id,Long userId);
    @Modifying
    @Transactional
    @Query(value = "delete from theme_list_id where theme_id=?1",nativeQuery = true)
    void deleteThemeListIdByThemeId(Long id);
    @Modifying
    @Transactional
    @Query(value = "insert into theme_list_id(theme_id, list_id) VALUES (?1,?2)",nativeQuery = true)
    void insertThemeListIdByThemeId(Long theme_id,Long list_id);


    /*
     * 删除主题
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Theme where id=:id and userId=:userId")
    void deleteThemeByIdAndUserId(@Param("id") Long id,@Param("userId") Long userId);
}
