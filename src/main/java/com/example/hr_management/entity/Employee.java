package com.example.hr_management.entity; 
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                           // Indique que c'est une table
@Table(name = "employees")        // Nom de la table dans MySQL
@Data                             // Génère getters, setters, toString (Lombok)
@NoArgsConstructor                // Génère constructeur vide
@AllArgsConstructor               // Génère constructeur avec tous les champs
@Builder                          // Permet de créer des objets facilement
public class Employee {

    @Id                                              // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;                        // Prénom

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;                         // Nom

    @Column(nullable = false, unique = true, length = 100)
    private String email;                            // Email (unique)

    @Column(length = 20)
    private String phone;                            // Téléphone

    @Column(name = "hire_date")
    private LocalDate hireDate;                      // Date d'embauche

    @Column(precision = 10)
    private Double salary;                           // Salaire

    @Column(length = 100)
    private String position;                         // Poste

    @Enumerated(EnumType.STRING)                     // Stocke le texte (pas le numéro)
    @Column(length = 20)
    private EmployeeStatus status;                   // Statut (ACTIF, INACTIF, etc.)

    // Enum pour le statut de l'employé
    public enum EmployeeStatus {
        ACTIF,
        INACTIF,
        EN_CONGE,
        LICENCIE
    }
}
