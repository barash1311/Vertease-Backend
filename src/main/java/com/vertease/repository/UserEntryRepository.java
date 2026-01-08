package com.vertease.repository;

import com.vertease.entity.User;
import com.vertease.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntryRepository extends JpaRepository<UserEntry,Long> {
}
