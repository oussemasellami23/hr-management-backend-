package com.example.hr_management.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;                    // Nom du département

    @Column(length = 255)
    private String description;             // Description

    @Column(length = 100)
    private String location;                // Localisation

    // ═══════════════════════════════════════════════════════════
    // RELATION: Un département a PLUSIEURS employés
    // ═══════════════════════════════════════════════════════════
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude           // Évite les boucles infinies
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
}
