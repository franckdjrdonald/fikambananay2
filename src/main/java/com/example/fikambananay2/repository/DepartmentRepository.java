package com.example.fikambananay2.repository;

import com.example.fikambananay2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);

}
