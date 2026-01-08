package com.vertease.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ml_analysis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MLAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "examination_id",nullable = false)
    private Examination examination;

    @Column(length = 200)
    private String predictedDiagnosis;
    private Double confidenceScore;

    @Column(length = 50)
    private String modelVersion;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
