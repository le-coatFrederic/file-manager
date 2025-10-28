package com.fredlecoat.file_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredlecoat.file_manager.entities.FileEntity;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}
