package com.example.fikambananay2.service;

import com.example.fikambananay2.dto.DepartmentDTO;
import com.example.fikambananay2.entity.Department;
import com.example.fikambananay2.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(DepartmentDTO dto) {
        if (departmentRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("A department with this name already exists.");
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        return departmentRepository.save(department);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(department -> new DepartmentDTO(department.getName(), department.getDescription()))
                .collect(Collectors.toList());
    }
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departement introuvable avec l'id: " + id));
        return new DepartmentDTO(department.getName(), department.getDescription());
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departement introuvable avec l'id: " + id));

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        departmentRepository.save(department);

        return new DepartmentDTO(department.getName(), department.getDescription());
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departement introuvable avec l'id: " + id));

        departmentRepository.delete(department);
    }
}
