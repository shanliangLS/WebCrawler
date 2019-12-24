package com.get.repository;

import com.get.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findThemeById(Long id);

    List<Theme> findThemesByUserId(Long userId);

    Theme findThemeByUserIdAndName(Long userId, String name);
}
