package com.vertease.repository;

import com.vertease.entity.User;
import com.vertease.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,String> {
    List<Verification> findByIsCorrect(boolean isCorrect);
    List<Verification> findByMlAnalysisId(String mlAnalysisId);
    boolean existsByMlAnalysisId(String analysisId);
}
