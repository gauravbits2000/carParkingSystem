package com.markit.org;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.markit.org.entity.EmployeesDetails;
import com.markit.org.repository.EmployeeDetailsRepository;
import com.markit.org.service.ParkingDrawService;

@SpringBootApplication
public class MarkitCarParkingSystemApplication implements ApplicationRunner  {
	
	@Autowired
	private EmployeeDetailsRepository empDetailsRepository;
	
	@Autowired
	private  ParkingDrawService parkingDrawService;

	public static void main(String[] args) 
	{
		SpringApplication.run(MarkitCarParkingSystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
		
		System.out.println("Testing");
           

	}
}
