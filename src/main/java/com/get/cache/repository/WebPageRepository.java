package com.get.cache.repository;

import com.get.cache.domain.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Long> {
    WebPage findWebPageByUrl(String url);
}
