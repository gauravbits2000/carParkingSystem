package com.markit.org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_DETAILS")
public class EmployeesDetails {
	
	private String employeeName;
	@Id
	private String employeeId;
	private String employeeEmail;
	
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public EmployeesDetails(String employeeName, String employeeId, String employeeEmail) {
		super();
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
	}
	public EmployeesDetails() {
		super();
	}
	
	

}
