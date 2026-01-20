package com.example.hr_management.dto;

import org.springframework.stereotype.Component;

import com.example.hr_management.entity.Department;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentCreateDTO dto) {
        return Department.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .build();
    }

    public DepartmentResponseDTO toResponseDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .location(department.getLocation())
                .employeeCount(department.getEmployees() != null ? department.getEmployees().size() : 0)
                .build();
    }

    public void updateEntityFromDTO(DepartmentCreateDTO dto, Department department) {
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setLocation(dto.getLocation());
    }
}
