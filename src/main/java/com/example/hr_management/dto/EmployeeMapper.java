package com.example.hr_management.dto;

import org.springframework.stereotype.Component;

import com.example.hr_management.entity.Employee;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeCreateDTO dto) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .hireDate(dto.getHireDate())
                .salary(dto.getSalary())
                .position(dto.getPosition())
                .status(dto.getStatus() != null ? dto.getStatus() : Employee.EmployeeStatus.ACTIF)
                .build();
    }

    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        EmployeeResponseDTO.EmployeeResponseDTOBuilder builder = EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .position(employee.getPosition())
                .status(employee.getStatus())
                .fullName(employee.getFirstName() + " " + employee.getLastName());

        // ═══════════════════════════════════════════════════════════
        // AJOUTER LES INFOS DU DÉPARTEMENT (SI EXISTE)
        // ═══════════════════════════════════════════════════════════
        if (employee.getDepartment() != null) {
            builder.departmentId(employee.getDepartment().getId());
            builder.departmentName(employee.getDepartment().getName());
        }

        return builder.build();
    }

    public void updateEntityFromDTO(EmployeeCreateDTO dto, Employee employee) {
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        employee.setPosition(dto.getPosition());
        if (dto.getStatus() != null) {
            employee.setStatus(dto.getStatus());
        }
    }
}