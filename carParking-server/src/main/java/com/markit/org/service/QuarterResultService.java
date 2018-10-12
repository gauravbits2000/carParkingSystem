package com.markit.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.QuarterParkingResult;
import com.markit.org.entity.QuarterResultIdentity;
import com.markit.org.repository.QuarterParkingResultRepository;

@Service
public class QuarterResultService {
	
	@Autowired
	private QuarterParkingResultRepository repo;

	
	public List<QuarterParkingResult> saveQuarterResults(List<EmployeeRegistration> winnersList, String quarter) {
		
		if(winnersList == null || winnersList.isEmpty()) {
			return null;
		}
		
		List<QuarterParkingResult> quarterParkingResultList = new ArrayList<QuarterParkingResult>();
		
		winnersList.stream().forEach(winner -> {
			QuarterParkingResult qpr = new QuarterParkingResult(new QuarterResultIdentity(winner.getEmployeeId(), quarter), 
					winner.getEmployeeName(), winner.getVehicleRegistrationNumber(), 
					winner.getCarParkingId(),  winner.getPoolEmployee(), 
					winner.getPoolEmployeeId(), winner.getPoolEmployeeVehicle(), winner.getRequestCategory(), 
					winner.getEmail(), winner.getPoolEmployeeEmailId());
			
			quarterParkingResultList.add(qpr);
		});
		
		repo.saveAll(quarterParkingResultList);
		
		return quarterParkingResultList;
	}
	
	public List<QuarterParkingResult> fetchQuarterResults(String quarter){
		return repo.findByQuarter(quarter);
	}
}
