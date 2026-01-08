package com.vertease.repository;

import com.vertease.entity.User;
import com.vertease.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepository extends JpaRepository<UserEntry,String> {
}
