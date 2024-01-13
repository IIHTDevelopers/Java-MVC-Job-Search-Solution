package com.yaksha.training.job.repository;

import com.yaksha.training.job.entity.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query(value = "Select j from Job j "
            + "where (:keyword IS NULL "
            + "OR lower(j.jobTitle) like %:keyword% "
            + "OR lower(j.jobDescription) like %:keyword%) AND j.isFav = false")
    Page<Job> findByJobTitleAndJobDescription(@Param("keyword") String keyword,
                                              Pageable pageable);

    Page<Job> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE job j set j.is_fav = :isFav where j.id = :id",
            nativeQuery = true)
    void updateIsFav(@Param("isFav") boolean isFav, @Param("id") Long id);


    @Query(value = "Select j from Job j "
            + "where (:keyword IS NULL "
            + "OR lower(j.jobTitle) like %:keyword% "
            + "OR lower(j.jobDescription) like %:keyword%) AND j.isFav = true")
    Page<Job> findFavJobByJobTitleAndJobDescription(@Param("keyword") String keyword,
                                              Pageable pageable);

}
