package com.markit.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
