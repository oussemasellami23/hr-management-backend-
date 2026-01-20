package com.example.hr_management.dto;

import java.time.LocalDate;

import com.example.hr_management.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private Double salary;
    private String position;
    private Employee.EmployeeStatus status;
    private String fullName;  // Champ calculé: prénom + nom
    private Long departmentId;
    private String departmentName;
}
