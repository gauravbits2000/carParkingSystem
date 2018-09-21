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

import com.markit.org.entity.Employee;
import com.markit.org.repository.EmployeeRepository;

@Service
public class MarkitCarParkingService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> registerEmployee(Employee employee){
		if(employee == null) {
			return null;
		}
		employeeRepository.save(employee);
		return employeeRepository.findAll();
	}

	public List<Employee> doCarParkingDraw(){
		
		log.info("Getting all registered Employees");
		
		List<Employee> employeeList = employeeRepository.findAll();
		
		if(employeeList == null) {
			return null;
		}
		
		List<Employee> winnersList = new ArrayList<Employee>();
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

}
