package com.markit.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.QuarterParkingResult;
import com.markit.org.entity.QuarterResultIdentity;

@Repository
public interface QuarterParkingResultRepository extends JpaRepository<QuarterParkingResult, QuarterResultIdentity>{
	
	@Query("FROM QuarterParkingResult  t where t.identity.quarter = :quarter")
	public List<QuarterParkingResult> findByQuarter(@Param("quarter") String quarter);

}
