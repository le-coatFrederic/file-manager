package com.fredlecoat.file_manager.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @Column(nullable = false)
    private LocalDateTime updateDateTime;

    @Column(nullable = false)
    private LocalDateTime accessDateTime;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String group;

    @Column(nullable = false)
    private String  rights;  

    @OneToOne(mappedBy = "metadata", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private File file;

    public Metadata(
        Long size,
        LocalDateTime creationDateTime,
        LocalDateTime updateDateTime,
        LocalDateTime accessDateTime,
        String owner,
        String group,
        String rights,
        File file
    ) {
        this.size = size;
        this.creationDateTime = creationDateTime;
        this.updateDateTime = updateDateTime;
        this.accessDateTime = accessDateTime;
        this.owner = owner;
        this.group = group;
        this.rights = rights;
        this.file = file;
    }
}
