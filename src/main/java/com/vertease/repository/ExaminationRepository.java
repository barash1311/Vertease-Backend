package com.vertease.repository;

import com.vertease.entity.Examination;
import com.vertease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination,String> {
}
