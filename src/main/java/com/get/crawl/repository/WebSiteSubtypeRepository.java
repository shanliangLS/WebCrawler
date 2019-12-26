package com.get.crawl.repository;

import com.get.domain.WebSiteSubtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSiteSubtypeRepository extends JpaRepository<WebSiteSubtype, Long> {
    WebSiteSubtype findWebSiteSubtypeById(Long id);
}
