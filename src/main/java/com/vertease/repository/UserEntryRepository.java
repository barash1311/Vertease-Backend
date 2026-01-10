package com.vertease.repository;

import com.vertease.entity.User;
import com.vertease.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntryRepository extends JpaRepository<UserEntry,String> {
    List<UserEntry> findByPatientId(String patientId);
    Optional<UserEntry> findTopByPatient_IdOrderByCreatedAtDesc(String patient_id);
}
