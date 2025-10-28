package com.fredlecoat.file_manager.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fredlecoat.file_manager.values.FileType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileType type;
    
    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    
    @ManyToOne
    @JoinColumn(name = "parent_folder_id", nullable = true)
    private FileEntity parent_folder;

    @OneToMany(mappedBy = "parent_folder", cascade = CascadeType.ALL)
    private List<FileEntity> subFiles;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> logs = new ArrayList<>();

    public FileEntity(
        String name,
        FileType type,
        Long size,
        LocalDateTime updatedLastTime
    ) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.updateDateTime = updatedLastTime;
        this.logs = new ArrayList<>();
    }

    public FileEntity(
        String name,
        FileType type,
        FileEntity parent,
        Long size,
        LocalDateTime updatedLastTime
    ) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.updateDateTime = updatedLastTime;
        this.parent_folder = parent.type == FileType.FOLDER ? parent : null;
        this.logs = new ArrayList<>();
    }

    public String getUri() {
        if (this.parent_folder == null) {
            return "/" + this.name + "/";
        } else {
            return this.parent_folder.getUri() + this.name;
        }
    }
}
