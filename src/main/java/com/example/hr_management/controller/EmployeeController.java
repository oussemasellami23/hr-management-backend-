package com.example.hr_management.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.example.hr_management.dto.EmployeeCreateDTO;
import com.example.hr_management.dto.EmployeeMapper;
import com.example.hr_management.dto.EmployeeResponseDTO;
import com.example.hr_management.entity.Employee;
import com.example.hr_management.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees - Obtenir TOUS les employés
    // ═══════════════════════════════════════════════════════════
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> obtenirTousLesEmployees() {
        List<Employee> employees = employeeService.obtenirTousLesEmployees();
        List<EmployeeResponseDTO> response = employees.stream()
                .map(employeeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/{id} - Obtenir UN employé par ID
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> obtenirEmployeeParId(@PathVariable Long id) {
        Employee employee = employeeService.obtenirEmployeeParId(id);
        return ResponseEntity.ok(employeeMapper.toResponseDTO(employee));
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/email/{email} - Obtenir UN employé par email
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeResponseDTO> obtenirEmployeeParEmail(@PathVariable String email) {
        Employee employee = employeeService.obtenirEmployeeParEmail(email);
        return ResponseEntity.ok(employeeMapper.toResponseDTO(employee));
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/statut/{statut} - Obtenir les employés par statut
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<EmployeeResponseDTO>> obtenirEmployeesParStatut(
            @PathVariable Employee.EmployeeStatus statut) {
        List<Employee> employees = employeeService.obtenirEmployeesParStatut(statut);
        List<EmployeeResponseDTO> response = employees.stream()
                .map(employeeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/count - Compter les employés
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/count")
    public ResponseEntity<Long> compterEmployees() {
        return ResponseEntity.ok(employeeService.compterEmployees());
    }

    // ═══════════════════════════════════════════════════════════
    // POST /api/employees - Créer un nouvel employé (avec validation)
    // ═══════════════════════════════════════════════════════════
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> creerEmployee(
            @Valid @RequestBody EmployeeCreateDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeService.creerEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.toResponseDTO(saved));
    }
        // ═══════════════════════════════════════════════════════════
    // PUT /api/employees/{id} - Modifier un employé (avec validation)
    // ═══════════════════════════════════════════════════════════
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> modifierEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeCreateDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee updated = employeeService.modifierEmployee(id, employee);
        return ResponseEntity.ok(employeeMapper.toResponseDTO(updated));
    }

    // ═══════════════════════════════════════════════════════════
    // DELETE /api/employees/{id} - Supprimer un employé
    // ═══════════════════════════════════════════════════════════
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEmployee(@PathVariable Long id) {
        employeeService.supprimerEmployee(id);
        return ResponseEntity.ok("Employé supprimé avec succès! ID: " + id);
    }

        // ═══════════════════════════════════════════════════════════
    // PUT /api/employees/{id}/department/{departmentId} - Affecter département
    // ═══════════════════════════════════════════════════════════
    @PutMapping("/{id}/department/{departmentId}")
    public ResponseEntity<EmployeeResponseDTO> affecterDepartment(
            @PathVariable Long id,
            @PathVariable Long departmentId) {
        Employee employee = employeeService.affecterDepartment(id, departmentId);
        return ResponseEntity.ok(employeeMapper.toResponseDTO(employee));
    }

    // ═══════════════════════════════════════════════════════════
    // DELETE /api/employees/{id}/department - Retirer du département
    // ═══════════════════════════════════════════════════════════
    @DeleteMapping("/{id}/department")
    public ResponseEntity<EmployeeResponseDTO> retirerDepartment(@PathVariable Long id) {
        Employee employee = employeeService.retirerDepartment(id);
        return ResponseEntity.ok(employeeMapper.toResponseDTO(employee));
    }

    // ═══════════════════════════════════════════════════════════
    // GET /api/employees/department/{departmentId} - Employés d'un département
    // ═══════════════════════════════════════════════════════════
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponseDTO>> obtenirEmployeesParDepartment(
            @PathVariable Long departmentId) {
        List<Employee> employees = employeeService.obtenirEmployeesParDepartment(departmentId);
        List<EmployeeResponseDTO> response = employees.stream()
                .map(employeeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}