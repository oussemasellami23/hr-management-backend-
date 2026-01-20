package com.example.hr_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hr_management.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // ═══════════════════════════════════════════════════════════
    // Méthodes GRATUITES (héritées de JpaRepository):
    // ═══════════════════════════════════════════════════════════
    // save(employee)           → Créer ou modifier
    // findById(id)             → Trouver par ID
    // findAll()                → Liste tous les employés
    // deleteById(id)           → Supprimer par ID
    // count()                  → Compter les employés
    // existsById(id)           → Vérifier si existe
    // ═══════════════════════════════════════════════════════════

    // ═══════════════════════════════════════════════════════════
    // Méthodes PERSONNALISÉES (Spring génère le SQL automatiquement!)
    // ═══════════════════════════════════════════════════════════
    
    // Trouver par email
    Optional<Employee> findByEmail(String email);
    
    // Trouver par nom (lastName)
    List<Employee> findByLastName(String lastName);
    
    // Trouver par statut
    List<Employee> findByStatus(Employee.EmployeeStatus status);
    
    // Trouver par poste
    List<Employee> findByPosition(String position);
    
    // Vérifier si un email existe déjà
    boolean existsByEmail(String email);
    
    // Trouver par prénom ET nom
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findByDepartmentId(Long departmentId);
}
