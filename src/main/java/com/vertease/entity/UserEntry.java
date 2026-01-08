package com.vertease.entity;

import com.vertease.entity.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private User patient;

    @Column(nullable = false)
    private String enteredBy;

    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(columnDefinition = "TEXT")
    private String comorbidities;

    private String primarySymptom;
    private String totalDuration;
    private String motionType;
    private String patternType;
    private String episodeDuration;
    private String remissionType;
    private String triggers;
    @Column(columnDefinition = "TEXT")
    private String associatedSymptoms;
    @Column(columnDefinition = "TEXT")
    private String earSymptoms;
    @Column(columnDefinition = "TEXT")
    private String cerebellarSymptoms;
    @Column(columnDefinition = "TEXT")
    private String cranialNerveSymptoms;
    @Column(columnDefinition = "TEXT")
    private String drugHistory;
    @Column(columnDefinition = "TEXT")
    private String postOnsetMedicationS;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
