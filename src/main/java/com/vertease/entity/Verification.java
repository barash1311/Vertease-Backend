package com.vertease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "verifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "ml_analysis_id",nullable = false)
    private MLAnalysis mlAnalysis;

    @ManyToOne
    @JoinColumn(name = "verified_by_user_id",nullable = false)
    private User verifiedBy;

    private boolean isCorrect;

    @Column(length = 200)
    private String correctDiagnosis;

    @Column(columnDefinition = "TEXT")
    private String comments;

    private Integer qualityRating;
    @CreationTimestamp
    private LocalDateTime verifiedAt;




}
