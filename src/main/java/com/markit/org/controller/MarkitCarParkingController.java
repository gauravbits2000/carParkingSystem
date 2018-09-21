package com.markit.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.markit.org.entity.Employee;
import com.markit.org.service.MarkitCarParkingService;

@RestController
public class MarkitCarParkingController {
	
	@Autowired
	private MarkitCarParkingService service;
	
	@PostMapping("/markit-car-parking/employee/registration")
	public List<Employee> registerEmployee(@RequestBody Employee employee){
		return service.registerEmployee(employee);
	}
	
	@PostMapping("/markit-car-parking/lucky-draw/")
	public List<Employee> doCarParkingDraw(){
		return service.doCarParkingDraw();
	}

}
