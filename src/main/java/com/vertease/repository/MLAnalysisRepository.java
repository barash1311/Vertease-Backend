package com.vertease.repository;

import com.vertease.entity.MLAnalysis;
import com.vertease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MLAnalysisRepository extends JpaRepository<MLAnalysis,String> {
}
