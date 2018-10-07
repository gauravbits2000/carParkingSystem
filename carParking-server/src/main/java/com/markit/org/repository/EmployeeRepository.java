package com.markit.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.EmployeeRegistration;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeRegistration, String>
{

	 @Query("FROM EmployeeRegistration  t where t.poolEmployeeId = :id") 
	 EmployeeRegistration findByPoolEmployeeId(@Param("id") String id);
	 
	 @Query("FROM EmployeeRegistration  t where t.isCarPool = 'Y'") 
	 List<EmployeeRegistration> findByIsCarPool();
	
}
