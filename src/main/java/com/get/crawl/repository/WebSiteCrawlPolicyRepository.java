package com.get.crawl.repository;

import com.get.crawl.domain.WebSiteCrawlPolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WebSiteCrawlPolicyRepository extends JpaRepository<WebSiteCrawlPolicy, Long> {

    List<WebSiteCrawlPolicy> findAll();

    WebSiteCrawlPolicy findWebSiteCrawlPolicyById(Long id);

//    int updateId(int yid, int nid);
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update WebSiteCrawlPolicy crawl " +
//            "set crawl.name = #{#policy.name}," +
//            "crawl.url = #{#policy.url}," +
//            "crawl.articleDownloadType = #{#policy.articleDownloadType}," +
//            "crawl.authorSelector = #{policy.authorSelector}," +
//            "crawl.authorSelectorType = #{#policy.authorSelectorType}," +
//            "crawl.titleSelectorType = #{#policy.titleSelectorType}," +
//            "crawl.titleSelector = #{#policy.titleSelector}," +
//            "crawl.photoCss = #{#policy.photoCss}," +
//            "crawl.")
//    int saveUpdate(@Param("policy") WebSiteCrawlPolicy policy);
}
