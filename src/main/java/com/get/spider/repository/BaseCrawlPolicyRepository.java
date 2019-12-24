package com.get.spider.repository;

import com.get.spider.entity.policy.BaseCrawlPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseCrawlPolicyRepository extends JpaRepository<BaseCrawlPolicy, Long> {

    BaseCrawlPolicy findBaseCrawlPolicyByIdAndPolicyEnum(Long id, String policyEnum);


}
