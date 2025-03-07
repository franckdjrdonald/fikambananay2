package com.example.fikambananay2.controller;

import com.example.fikambananay2.dto.DepartmentDTO;
import com.example.fikambananay2.entity.Department;
import com.example.fikambananay2.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Endpoint pour la creation de departement
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDTO dto) {
        try {
            Department department = departmentService.createDepartment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(department);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint pour récupérer tous les départements
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Endpoint pour récupérer un département par ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        try {
            DepartmentDTO department = departmentService.getDepartmentById(id);
            return ResponseEntity.ok(department);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint pour mettre à jour un département
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDTO dto) {
        try {
            DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, dto);
            return ResponseEntity.ok(updatedDepartment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint pour supprimer un département
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


