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

import com.example.hr_management.dto.DepartmentCreateDTO;
import com.example.hr_management.dto.DepartmentMapper;
import com.example.hr_management.dto.DepartmentResponseDTO;
import com.example.hr_management.entity.Department;
import com.example.hr_management.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    // GET /api/departments
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> obtenirTousLesDepartments() {
        List<Department> departments = departmentService.obtenirTousLesDepartments();
        List<DepartmentResponseDTO> response = departments.stream()
                .map(departmentMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // GET /api/departments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> obtenirDepartmentParId(@PathVariable Long id) {
        Department department = departmentService.obtenirDepartmentParId(id);
        return ResponseEntity.ok(departmentMapper.toResponseDTO(department));
    }

    // POST /api/departments
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> creerDepartment(
            @Valid @RequestBody DepartmentCreateDTO dto) {
        Department department = departmentMapper.toEntity(dto);
        Department saved = departmentService.creerDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentMapper.toResponseDTO(saved));
    }

    // PUT /api/departments/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> modifierDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentCreateDTO dto) {
        Department department = departmentMapper.toEntity(dto);
        Department updated = departmentService.modifierDepartment(id, department);
        return ResponseEntity.ok(departmentMapper.toResponseDTO(updated));
    }

    // DELETE /api/departments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerDepartment(@PathVariable Long id) {
        departmentService.supprimerDepartment(id);
        return ResponseEntity.ok("Département supprimé avec succès! ID: " + id);
    }

    // GET /api/departments/count
    @GetMapping("/count")
    public ResponseEntity<Long> compterDepartments() {
        return ResponseEntity.ok(departmentService.compterDepartments());
    }
}
