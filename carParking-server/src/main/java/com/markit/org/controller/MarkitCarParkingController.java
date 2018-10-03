package com.markit.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.LoginBean;
import com.markit.org.service.LDAPService;
import com.markit.org.service.MarkitCarParkingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MarkitCarParkingController {
	
	@Autowired
	private MarkitCarParkingService service;
	
	@Autowired
	private LDAPService ldapService;
	
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
	public EmployeeRegistration verifyLogin(@RequestBody LoginBean loginBean){
		return ldapService.doLDAPAuthetication(loginBean.getUsername(),loginBean.getPassword());
		
	}
	
	@PostMapping("/markit-car-parking/fetch-markit-employees")
	public List<String> fetchMarkitEmployees(@RequestBody LoginBean loginBean){
		return ldapService.fetchMarkitEmployees(loginBean.getUsername(),loginBean.getPassword());
	}

}
