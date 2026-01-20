package com.example.hr_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDTO {

    @NotBlank(message = "Le nom du département est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;

    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    private String description;

    @Size(max = 100, message = "La localisation ne peut pas dépasser 100 caractères")
    private String location;
}