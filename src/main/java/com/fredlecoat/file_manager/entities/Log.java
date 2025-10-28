package com.fredlecoat.file_manager.entities;

import java.time.LocalDateTime;

import com.fredlecoat.file_manager.values.LogStatus;
import com.fredlecoat.file_manager.values.LogType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime executedTime;

    @Column(nullable = true)
    private LocalDateTime decisionTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogStatus status;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private FileEntity file;

    public Log(
        LocalDateTime executedTime,
        LogType type,
        FileEntity file
    ) {
        this.executedTime = executedTime;
        this.type = type;
        this.status = LogStatus.WAITING;
        this.file = file;
    }

    public Log(
        LocalDateTime executedTime,
        LocalDateTime decisionTime,
        LogType type,
        FileEntity file
    ) {
        this.executedTime = executedTime;
        this.type = type;
        this.status = LogStatus.WAITING;
        this.file = file;
        this.decisionTime = decisionTime;
    }
}
