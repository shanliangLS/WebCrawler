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

    Theme findThemeByIdAndUserId(Long id,Long userId);
    Theme findThemeById(Long id);


    List<Theme> findThemesByUserId(Long userId);

    Theme findThemeByUserIdAndName(Long userId, String name);


    /**
     * 查询改名之后的主题名是否冲突
     * @param theme
     * @return 0=>无冲突  大于1=>有冲突
     */

    @Query(value = "select count(distinct name) from theme where name=#{#theme.name} and id<>#{#theme.id}",nativeQuery=true)
    int selectName(@Param("theme") Theme theme);

    /*
     * 更新主题
     */
    @Modifying
    @Transactional
    @Query(value = "update theme set name=#{#theme.name},listId=#{#theme.listId} where id=#{#theme.id} and userId=#{#theme.userId}",nativeQuery=true)
    void updateThemeByIdAndUserId(@Param("theme") Theme theme);

    /*
     * 删除主题
     */
    @Modifying
    @Transactional
    @Query(value = "delete from theme where id=#{#theme.id} and userId=#{#theme.userId}",nativeQuery=true)
    void deleteThemeByIdAndUserId(@Param("theme") Theme theme);
}
