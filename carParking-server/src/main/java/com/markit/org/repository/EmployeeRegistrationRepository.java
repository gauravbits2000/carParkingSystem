package com.markit.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.EmployeeRegistration;

@Repository
public interface EmployeeRegistrationRepository extends JpaRepository<EmployeeRegistration, String> {

	 @Query("FROM EmployeeRegistration  t where t.poolEmployeeId = :id") 
	 Optional<EmployeeRegistration> findByPoolEmployeeId(@Param("id") String id);
	 
	 @Query("FROM EmployeeRegistration  t where t.requestCategory = 'Pool Parking'") 
	 List<EmployeeRegistration> findByCarPool();
	 
	 @Query("FROM EmployeeRegistration  t where t.requestCategory = 'Female Night Shift'")
	 List<EmployeeRegistration> findByFemaleNightShift();
	 
	 @Query("FROM EmployeeRegistration  t where t.requestCategory = 'Medical Emergency'")
	 List<EmployeeRegistration> findByMedicalEmergency();
	
}
