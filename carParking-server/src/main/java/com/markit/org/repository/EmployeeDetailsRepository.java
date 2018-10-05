package com.markit.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.markit.org.entity.EmployeesDetails;


@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeesDetails, String>{

	public EmployeesDetails findByEmployeeName(String poolEmployee);
	
   

}
