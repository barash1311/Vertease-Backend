package com.vertease.repository;

import com.vertease.entity.User;
import com.vertease.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification,Long> {
}
