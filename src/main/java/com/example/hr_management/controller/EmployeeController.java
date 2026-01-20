package com.example.hr_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_management.entity.Employee;
import com.example.hr_management.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController                         // Indique que c'est un Controller REST
@RequestMapping("/api/employees")       // URL de base: /api/employees
@RequiredArgsConstructor                // Injection automatique du Service
@CrossOrigin(origins = "*")             // Permet les requêtes depuis Angular
public class EmployeeController {

    // Injection du Service
    private final EmployeeService employeeService;

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees - Obtenir TOUS les employés
    // ═══════════════════════════════════════════════════════════
    @GetMapping
    public ResponseEntity<List<Employee>> obtenirTousLesEmployees() {
        List<Employee> employees = employeeService.obtenirTousLesEmployees();
        return ResponseEntity.ok(employees);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/{id} - Obtenir UN employé par ID
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/{id}")
    public ResponseEntity<Employee> obtenirEmployeeParId(@PathVariable Long id) {
        Employee employee = employeeService.obtenirEmployeeParId(id);
        return ResponseEntity.ok(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/email/{email} - Obtenir UN employé par email
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> obtenirEmployeeParEmail(@PathVariable String email) {
        Employee employee = employeeService.obtenirEmployeeParEmail(email);
        return ResponseEntity.ok(employee);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/statut/{statut} - Obtenir les employés par statut
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Employee>> obtenirEmployeesParStatut(
            @PathVariable Employee.EmployeeStatus statut) {
        List<Employee> employees = employeeService.obtenirEmployeesParStatut(statut);
        return ResponseEntity.ok(employees);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/count - Compter les employés
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/count")
    public ResponseEntity<Long> compterEmployees() {
        long count = employeeService.compterEmployees();
        return ResponseEntity.ok(count);
    }

    // ═══════════════════════════════════════════════════════════
    // POST /api/employees - Créer un nouvel employé
    // ═══════════════════════════════════════════════════════════
    @PostMapping
    public ResponseEntity<Employee> creerEmployee(@RequestBody Employee employee) {
        Employee nouveauEmployee = employeeService.creerEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauEmployee);
    }

    // ═══════════════════════════════════════════════════════════
    // PUT /api/employees/{id} - Modifier un employé
    // ═══════════════════════════════════════════════════════════
    @PutMapping("/{id}")
    public ResponseEntity<Employee> modifierEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        Employee employeeModifie = employeeService.modifierEmployee(id, employee);
        return ResponseEntity.ok(employeeModifie);
    }

    // ═══════════════════════════════════════════════════════════
    // DELETE /api/employees/{id} - Supprimer un employé
    // ═══════════════════════════════════════════════════════════
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEmployee(@PathVariable Long id) {
        employeeService.supprimerEmployee(id);
        return ResponseEntity.ok("Employé supprimé avec succès! ID: " + id);
    }
}
