package com.fredlecoat.file_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredlecoat.file_manager.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
