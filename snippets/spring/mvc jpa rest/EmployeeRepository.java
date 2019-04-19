package com.yashuul.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashuul.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
}
