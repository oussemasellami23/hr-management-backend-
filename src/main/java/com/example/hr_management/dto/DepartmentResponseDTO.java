package com.example.hr_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private int employeeCount;      // Nombre d'employés dans ce département
}
