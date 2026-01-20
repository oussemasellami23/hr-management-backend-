package com.example.hr_management.service;

import com.example.hr_management.entity.Department;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.exception.ResourceNotFoundException;
import com.example.hr_management.repository.DepartmentRepository;
import com.example.hr_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;  // ← AJOUTE CECI

    // CRÉER
    public Employee creerEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email déjà utilisé: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    // LIRE TOUS
    public List<Employee> obtenirTousLesEmployees() {
        return employeeRepository.findAll();
    }

    // LIRE PAR ID
    public Employee obtenirEmployeeParId(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé", "id", id));
    }

    // LIRE PAR EMAIL
    public Employee obtenirEmployeeParEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employé", "email", email));
    }

    // LIRE PAR STATUT
    public List<Employee> obtenirEmployeesParStatut(Employee.EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    // MODIFIER
    public Employee modifierEmployee(Long id, Employee employeeModifie) {
        Employee employeeExistant = obtenirEmployeeParId(id);

        employeeExistant.setFirstName(employeeModifie.getFirstName());
        employeeExistant.setLastName(employeeModifie.getLastName());
        employeeExistant.setEmail(employeeModifie.getEmail());
        employeeExistant.setPhone(employeeModifie.getPhone());
        employeeExistant.setHireDate(employeeModifie.getHireDate());
        employeeExistant.setSalary(employeeModifie.getSalary());
        employeeExistant.setPosition(employeeModifie.getPosition());
        employeeExistant.setStatus(employeeModifie.getStatus());

        return employeeRepository.save(employeeExistant);
    }

    // SUPPRIMER
    public void supprimerEmployee(Long id) {
        Employee employee = obtenirEmployeeParId(id);
        employeeRepository.delete(employee);
    }

    // COMPTER
    public long compterEmployees() {
        return employeeRepository.count();
    }

    // ═══════════════════════════════════════════════════════════
    // AFFECTER UN EMPLOYÉ À UN DÉPARTEMENT (NOUVELLE MÉTHODE)
    // ═══════════════════════════════════════════════════════════
    public Employee affecterDepartment(Long employeeId, Long departmentId) {
        // 1. Trouver l'employé
        Employee employee = obtenirEmployeeParId(employeeId);

        // 2. Trouver le département
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Département", "id", departmentId));

        // 3. Affecter le département à l'employé
        employee.setDepartment(department);

        // 4. Sauvegarder et retourner
        return employeeRepository.save(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // RETIRER UN EMPLOYÉ DE SON DÉPARTEMENT
    // ═══════════════════════════════════════════════════════════
    public Employee retirerDepartment(Long employeeId) {
        Employee employee = obtenirEmployeeParId(employeeId);
        employee.setDepartment(null);
        return employeeRepository.save(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // OBTENIR LES EMPLOYÉS D'UN DÉPARTEMENT
    // ═══════════════════════════════════════════════════════════
    public List<Employee> obtenirEmployeesParDepartment(Long departmentId) {
        // Vérifier que le département existe
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Département", "id", departmentId));

        return employeeRepository.findByDepartmentId(departmentId);
    }
}