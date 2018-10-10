package com.markit.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.EmployeesDetails;
import com.markit.org.repository.EmployeeDetailsRepository;
import com.markit.org.repository.EmployeeRegistrationRepository;

@Service
public class MarkitCarParkingService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRegistrationRepository employeeRegistrationRepository;
	
	@Autowired
	private EmployeeDetailsRepository empDetailsepository;
	
	@Autowired
	private ParkingDrawService parkingDrawService;
	
	public List<EmployeeRegistration> registerEmployee(EmployeeRegistration employee){
		if(employee == null) {
			return null;
		}
		
		if(RequestCategory.POOL_PARKING.getCategory().equals(employee.getRequestCategory())) {
			EmployeesDetails empDetails = empDetailsepository.findByEmployeeName(employee.getPoolEmployee());
			employee.setPoolEmployeeId(empDetails.getEmployeeId());
		}
		
		if(RequestCategory.GENERAL_PARKING.getCategory().equals(employee.getRequestCategory()) 
				|| RequestCategory.MEDICAL_EMERGENCY.getCategory().equals(employee.getRequestCategory()) 
				|| RequestCategory.FEMALE_NIGHT_SHIFT.getCategory().equals(employee.getRequestCategory())) {
			// Find the employee Details in primary and if exists with car pool then remove that carpool information
			Optional<EmployeeRegistration> primaryEmployeeOptional = employeeRegistrationRepository.findById(employee.getEmployeeId());
			if(primaryEmployeeOptional.isPresent()) {
				employeeRegistrationRepository.delete(primaryEmployeeOptional.get());
			}
			
			// Find the employee Details in Pooled Employee	and remove pool employee info and create a new row for the employee in General Parking		
			Optional<EmployeeRegistration> pooledEmployeeOptional = employeeRegistrationRepository.findByPoolEmployeeId(employee.getEmployeeId());
			if(pooledEmployeeOptional.isPresent()) {
				EmployeeRegistration pooledEmployee = pooledEmployeeOptional.get();
				pooledEmployee.setPoolEmployee(null);
				pooledEmployee.setPoolEmployeeId(null);
				pooledEmployee.setPoolEmployeeVehicle(null);
				pooledEmployee.setIsCarPool(null);
				pooledEmployee.setVehicleRegistrationNumber(employee.getVehicleRegistrationNumber());
				pooledEmployee.setRequestCategory(RequestCategory.GENERAL_PARKING.getCategory());
				employeeRegistrationRepository.save(pooledEmployee);
			}
		}
		
		employeeRegistrationRepository.save(employee);
		return employeeRegistrationRepository.findAll();
	}

	public List<EmployeeRegistration> doCarParkingDraw(){
		
		log.info("Getting all registered Employees");
		Integer carPoolSlots = 5;
		Integer femaleLateShiftSlots = 5;
		Integer medicalEmergencySlots = 5;
		Integer generalSlots = 10;
		
		List<EmployeeRegistration> finalWinnersList = new ArrayList<EmployeeRegistration>();
		
		//Car Pool Draw
		if(carPoolSlots != null && carPoolSlots > 0) {
			List<EmployeeRegistration> carPoolWinnersList = parkingDrawService.doCarPoolDraw(carPoolSlots);
			finalWinnersList.addAll(carPoolWinnersList);
		}
		
		//Female Draw
		if(femaleLateShiftSlots != null && femaleLateShiftSlots > 0) {
			List<EmployeeRegistration> femaleLateShiftWinnersList = parkingDrawService.doFemaleLateShiftDraw(femaleLateShiftSlots);
			finalWinnersList.addAll(femaleLateShiftWinnersList);
		}
		
		if(medicalEmergencySlots != null && medicalEmergencySlots > 0) {
			List<EmployeeRegistration> medicalEmergencyWinnersList = parkingDrawService.doMedicalEmergencyDraw(medicalEmergencySlots);
			finalWinnersList.addAll(medicalEmergencyWinnersList);
		}
		
		//General Draw
		List<EmployeeRegistration> winnersList = parkingDrawService.doGeneralDraw(generalSlots);
		finalWinnersList.addAll(winnersList);
		
		
		log.info("returning all lucky winners");
		return finalWinnersList;
	}

	public List<EmployeeRegistration> viewEmployeeList() {
		List<EmployeeRegistration> employeeList = employeeRegistrationRepository.findAll();
		return employeeList;
	}

	public List<String> fetchMarkitEmployees() {
		List<EmployeesDetails> empDetailsList = empDetailsepository.findAll();
		List<String> empNameList = new ArrayList<String>();
		empDetailsList.forEach(empDetail ->  empNameList.add(empDetail.getEmployeeName()));
		return  empNameList;
		
	}
	
	public List<String> fetchCarParkingResults(String quarter){
		//TODO:
		return null;
	}
	

	enum RequestCategory{
		POOL_PARKING("Pool Parking"),
		GENERAL_PARKING("General Parking"),
		MEDICAL_EMERGENCY("Medical Emergency"),
		FEMALE_NIGHT_SHIFT("Female Night Shift");
		
		private String category;
		
		private RequestCategory(String category) {
			this.category = category;
		}
		
		public String getCategory() {
			return category;
		}
	}
	
}
