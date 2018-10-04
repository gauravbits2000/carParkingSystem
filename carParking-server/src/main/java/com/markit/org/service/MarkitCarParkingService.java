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
import com.markit.org.entity.LoginBean;
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
		employeeRepository.save(employee);
		return employeeRepository.findAll();
	}

	public List<EmployeeRegistration> doCarParkingDraw(){
		
		log.info("Getting all registered Employees");
		
		List<EmployeeRegistration> employeeList = employeeRepository.findAll();
		
		if(employeeList == null) {
			return null;
		}
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		IntStream.range(1, 4).forEach(i -> {
			log.info("START : Shuffling " + i + " Times");
			Collections.shuffle(employeeList, new SecureRandom());
			log.info("Choosing a lucky winner !!");
			winnersList.add(employeeList.get(0));
			employeeList.remove(0);
			log.info("END : Shuffling");
		});
		
		log.info("returning all lucky winners");
		return winnersList;
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

	

}
