package com.vertease.repository;

import com.vertease.entity.Examination;
import com.vertease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination,Long> {
}
