package com.markit.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.EmployeeRegistration;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRegistration, String>{

}
