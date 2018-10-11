package com.markit.org.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.repository.EmployeeRegistrationRepository;

@Service
public class LDAPService {

	@Autowired
	private EmployeeRegistrationRepository employeeRepository;



	public EmployeeRegistration doLDAPAuthetication(String username, String password) 
	{

		Hashtable<String, String> env = new Hashtable<String, String>();
		EmployeeRegistration employee = new EmployeeRegistration();

				
				
		
		employee.setEmployeeName("Gaurav Agarwal");
		employee.setEmail("gaurav.agarwal@gmail.com");
		employee.setEmployeeId("70013683");
		employee.setIsAuthorize("True");
		
			return employee;


	}

}
