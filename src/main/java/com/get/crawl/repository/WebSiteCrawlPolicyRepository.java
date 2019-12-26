package com.get.crawl.repository;

import com.get.crawl.domain.WebSiteCrawlPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebSiteCrawlPolicyRepository extends JpaRepository<WebSiteCrawlPolicy, Long> {

    List<WebSiteCrawlPolicy> findAll();

    WebSiteCrawlPolicy findWebSiteCrawlPolicyById(Long id);

}
