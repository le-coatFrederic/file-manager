package com.fredlecoat.file_manager.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fredlecoat.file_manager.domain.values.FileType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileType type;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id")
    private Metadata metadata;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Log> logs = new ArrayList<>();

    public File(
        String name,
        FileType type
    ) {
        this.name = name;
        this.type = type;
        this.logs = new ArrayList<>();
    }

    public File(
        String name,
        FileType type,
        Metadata metadata
    ) {
        this.name = name;
        this.type = type;
        this.metadata = metadata;
        this.logs = new ArrayList<>();
    }
}
