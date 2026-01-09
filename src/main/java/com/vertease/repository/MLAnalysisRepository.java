package com.vertease.repository;

import com.vertease.entity.MLAnalysis;
import com.vertease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MLAnalysisRepository extends JpaRepository<MLAnalysis,String> {
    Optional<MLAnalysis> findByExaminationId(String examinationId);
}
