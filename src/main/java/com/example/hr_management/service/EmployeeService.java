package com.example.hr_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hr_management.entity.Employee;
import com.example.hr_management.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service                    // Indique que c'est un Service Spring
@RequiredArgsConstructor    // Lombok génère le constructeur avec injection
public class EmployeeService {

    // Injection du Repository
    private final EmployeeRepository employeeRepository;

    // ═══════════════════════════════════════════════════════════
    // CRÉER un employé
    // ═══════════════════════════════════════════════════════════
    public Employee creerEmployee(Employee employee) {
        // Vérifier si l'email existe déjà
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email déjà utilisé: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // LIRE tous les employés
    // ═══════════════════════════════════════════════════════════
    public List<Employee> obtenirTousLesEmployees() {
        return employeeRepository.findAll();
    }

    // ═══════════════════════════════════════════════════════════
    // LIRE un employé par ID
    // ═══════════════════════════════════════════════════════════
    public Employee obtenirEmployeeParId(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'ID: " + id));
    }

    // ═══════════════════════════════════════════════════════════
    // LIRE un employé par Email
    // ═══════════════════════════════════════════════════════════
    public Employee obtenirEmployeeParEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'email: " + email));
    }

    // ═══════════════════════════════════════════════════════════
    // LIRE les employés par statut
    // ═══════════════════════════════════════════════════════════
    public List<Employee> obtenirEmployeesParStatut(Employee.EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    // ═══════════════════════════════════════════════════════════
    // MODIFIER un employé
    // ═══════════════════════════════════════════════════════════
    public Employee modifierEmployee(Long id, Employee employeeModifie) {
        // 1. Vérifier que l'employé existe
        Employee employeeExistant = obtenirEmployeeParId(id);
        
        // 2. Mettre à jour les champs
        employeeExistant.setFirstName(employeeModifie.getFirstName());
        employeeExistant.setLastName(employeeModifie.getLastName());
        employeeExistant.setEmail(employeeModifie.getEmail());
        employeeExistant.setPhone(employeeModifie.getPhone());
        employeeExistant.setHireDate(employeeModifie.getHireDate());
        employeeExistant.setSalary(employeeModifie.getSalary());
        employeeExistant.setPosition(employeeModifie.getPosition());
        employeeExistant.setStatus(employeeModifie.getStatus());
        
        // 3. Sauvegarder et retourner
        return employeeRepository.save(employeeExistant);
    }

    // ═══════════════════════════════════════════════════════════
    // SUPPRIMER un employé
    // ═══════════════════════════════════════════════════════════
    public void supprimerEmployee(Long id) {
        // Vérifier que l'employé existe
        Employee employee = obtenirEmployeeParId(id);
        employeeRepository.delete(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // COMPTER les employés
    // ═══════════════════════════════════════════════════════════
    public long compterEmployees() {
        return employeeRepository.count();
    }
}