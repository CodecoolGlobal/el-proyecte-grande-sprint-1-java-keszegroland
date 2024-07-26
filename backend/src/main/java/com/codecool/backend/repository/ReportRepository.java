package com.codecool.backend.repository;

import com.codecool.backend.model.Member;
import com.codecool.backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
   Optional<Report> findReportByMemberAndPostId(Member member, long postId);
}
