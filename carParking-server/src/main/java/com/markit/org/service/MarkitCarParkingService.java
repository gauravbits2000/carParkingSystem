package com.markit.org.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.EmployeesDetails;
import com.markit.org.repository.EmployeeDetailsRepository;
import com.markit.org.repository.EmployeeRepository;

@Service
public class MarkitCarParkingService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDetailsRepository empDetailsepository;
	
	public List<EmployeeRegistration> registerEmployee(EmployeeRegistration employee){
		if(employee == null) {
			return null;
		}
		
		if("true".equalsIgnoreCase(employee.getIsCarPool()) && employee.getPoolEmployee() != null){
			
			EmployeesDetails empDetails = empDetailsepository.findByEmployeeName(employee.getPoolEmployee());
			employee.setPoolEmployeeId(empDetails.getEmployeeId());
			
		}
		if("false".equalsIgnoreCase(employee.getIsCarPool()) && employee.getPoolEmployee() != null){
			/*//Updating Pool Employee Details
			EmployeesDetails empDetails = empDetailsepository.findByEmployeeName(employee.getPoolEmployee());
			Optional<EmployeeRegistration> poolEmpRegistration = employeeRepository.findById(empDetails.getEmployeeId());
			poolEmpRegistration.get().setIsCarPool("false");
			poolEmpRegistration.get().setPoolEmployeeId(null);
			poolEmpRegistration.get().setPoolEmployee(null);
			poolEmpRegistration.get().setPoolEmployeeVehicle(null);*/
			
			//Updating Primary Employee Details
			employee.setPoolEmployeeId(null);
			employee.setPoolEmployee(null);
			employee.setPoolEmployeeVehicle(null);
		}
		
		employeeRepository.save(employee);
		return employeeRepository.findAll();
	}

	public List<EmployeeRegistration> doCarParkingDraw(){
		
		log.info("Getting all registered Employees");
		Integer carPoolSlots = 5;
		Integer femaleLateShiftSlots = 5;
		Integer generalSlots = 10;
		
		List<EmployeeRegistration> finalWinnersList = new ArrayList<EmployeeRegistration>();
		
		//Car Pool Draw
		if(carPoolSlots != null && carPoolSlots > 0) {
			List<EmployeeRegistration> carPoolWinnersList = doCarPoolDraw(carPoolSlots);
			finalWinnersList.addAll(carPoolWinnersList);
		}
		
		//Female Draw
		if(femaleLateShiftSlots != null && femaleLateShiftSlots > 0) {
			List<EmployeeRegistration> femaleLateShiftWinnersList = doFemaleLateShiftDraw(femaleLateShiftSlots);
			finalWinnersList.addAll(femaleLateShiftWinnersList);
		}
		
		//General Draw
		List<EmployeeRegistration> winnersList = doGeneralDraw(generalSlots);
		finalWinnersList.addAll(winnersList);
		
		
		log.info("returning all lucky winners");
		return finalWinnersList;
	}

	public List<EmployeeRegistration> viewEmployeeList() {
		List<EmployeeRegistration> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	public List<String> fetchMarkitEmployees() {
		List<EmployeesDetails> empDetailsList = empDetailsepository.findAll();
		List<String> empNameList = new ArrayList<String>();
		empDetailsList.forEach(empDetail ->  empNameList.add(empDetail.getEmployeeName()));
		return  empNameList;
		
	}
	
	private List<EmployeeRegistration> doCarPoolDraw(Integer carPoolSlots){
		List<EmployeeRegistration> carPoolWinnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> carPoolEmployeeList = employeeRepository.findByIsCarPool();
		
		if(carPoolEmployeeList == null) {
			log.info("No one applied for Car Pool Parking"); 
			return carPoolWinnersList;
		}
		
		IntStream.range(1, carPoolSlots).forEach(i -> {
			log.info("START : Shuffling " + i + " Times");
			Collections.shuffle(carPoolEmployeeList, new SecureRandom());
			log.info("Choosing a lucky winner !!");
			carPoolWinnersList.add(carPoolEmployeeList.get(0));
			carPoolEmployeeList.remove(0);
			log.info("END : Shuffling");
		});
		
		return carPoolWinnersList;
	}
	
	private List<EmployeeRegistration> doGeneralDraw(Integer generalSlots){
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> employeeList = employeeRepository.findAll();
		
		if(employeeList == null) {
			return winnersList;
		}
		
		IntStream.range(1, generalSlots).forEach(i -> {
			log.info("START : Shuffling " + i + " Times");
			Collections.shuffle(employeeList, new SecureRandom());
			log.info("Choosing a lucky winner !!");
			winnersList.add(employeeList.get(0));
			employeeList.remove(0);
			log.info("END : Shuffling");
		});
		
		return winnersList;
	}
	
	private List<EmployeeRegistration> doFemaleLateShiftDraw(Integer femaleLateShiftSlots){
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> employeeList = employeeRepository.findAll();
		
		if(employeeList == null) {
			return winnersList;
		}
		
		IntStream.range(1, femaleLateShiftSlots).forEach(i -> {
			log.info("START : Shuffling " + i + " Times");
			Collections.shuffle(employeeList, new SecureRandom());
			log.info("Choosing a lucky winner !!");
			winnersList.add(employeeList.get(0));
			employeeList.remove(0);
			log.info("END : Shuffling");
		});
		
		return winnersList;
	}
	
}
