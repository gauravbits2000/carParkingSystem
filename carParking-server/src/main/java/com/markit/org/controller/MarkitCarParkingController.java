package com.markit.org.controller;

import java.util.List;

import com.markit.mcp.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.LoginBean;
import com.markit.org.service.LDAPService;
import com.markit.org.service.MarkitCarParkingService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
// All origins are allowed
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:8080")
public class MarkitCarParkingController {
	
	@Autowired
	private MarkitCarParkingService service;
	
	@Autowired
	private LDAPService ldapService;

	@RequestMapping("/")
	public String getHeaderInfo() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String token_direct = request.getHeader("Authorization");
		System.out.println(token_direct);

		String name = SecurityUtil.getClaimFromJWTToken("name");
		String last_name = SecurityUtil.getClaimFromJWTToken("last_name");
		String first_name = SecurityUtil.getClaimFromJWTToken("first_name");
		String email_address = SecurityUtil.getClaimFromJWTToken("email_address");
		String jwt = SecurityUtil.getClaimFromJWTToken("jwt");

	return "Hello "+token_direct + "---" +email_address;



	}
	
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
	
	@GetMapping("/markit-car-parking/fetch-markit-employees")
	public List<String> fetchMarkitEmployees(){
		// return ldapService.fetchMarkitEmployees(loginBean.getUsername(),loginBean.getPassword());
		return service.fetchMarkitEmployees();
	}
	
	@PostMapping("/markit-car-parking/car-parking-results")
	public List<String> fetchCarParkingResults(@RequestBody String quarter){
		return service.fetchCarParkingResults(quarter);
	}

}
