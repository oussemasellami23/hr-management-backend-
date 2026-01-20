package com.example.hr_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hr_management.entity.Department;
import com.example.hr_management.exception.ResourceNotFoundException;
import com.example.hr_management.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // CRÉER
    public Department creerDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Département existe déjà: " + department.getName());
        }
        return departmentRepository.save(department);
    }

    // LIRE TOUS
    public List<Department> obtenirTousLesDepartments() {
        return departmentRepository.findAll();
    }

    // LIRE PAR ID
    public Department obtenirDepartmentParId(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département", "id", id));
    }

    // MODIFIER
    public Department modifierDepartment(Long id, Department departmentModifie) {
        Department existant = obtenirDepartmentParId(id);
        existant.setName(departmentModifie.getName());
        existant.setDescription(departmentModifie.getDescription());
        existant.setLocation(departmentModifie.getLocation());
        return departmentRepository.save(existant);
    }

    // SUPPRIMER
    public void supprimerDepartment(Long id) {
        Department department = obtenirDepartmentParId(id);
        departmentRepository.delete(department);
    }

    // COMPTER
    public long compterDepartments() {
        return departmentRepository.count();
    }
}
