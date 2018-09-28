package com.markit.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.markit.org.entity.Employee;
import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.LoginBean;
import com.markit.org.service.MarkitCarParkingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MarkitCarParkingController {
	
	@Autowired
	private MarkitCarParkingService service;
	
	@PostMapping("/markit-car-parking/employee-registration")
	public List<EmployeeRegistration> registerEmployee(@RequestBody EmployeeRegistration employee){
		return service.registerEmployee(employee);
	}
	
	@GetMapping("/markit-car-parking/lucky-draw/")
	public List<EmployeeRegistration> doCarParkingDraw(){
		return service.doCarParkingDraw();
	}
	
	@GetMapping("/fetch-employees")
	public List<EmployeeRegistration> viewEmployee(){
		return service.viewEmployeeList();
	}
	
	@PostMapping("/markit-car-parking/login")
	public Employee verifyLogin(@RequestBody LoginBean loginBean){
		return service.verifyLogin(loginBean);
		
	}

}
