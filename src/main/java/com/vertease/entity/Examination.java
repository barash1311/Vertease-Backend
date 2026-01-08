package com.vertease.entity;

import com.vertease.entity.enums.Diagnosis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "examinations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private User doctor;

    @Column(columnDefinition = "TEXT")
    private String clinicalFindings;

    @Enumerated(EnumType.STRING)
    private Diagnosis diagnosisByDoctor;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 20)
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
