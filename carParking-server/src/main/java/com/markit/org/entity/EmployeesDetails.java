package com.markit.org.entity;

import javax.persistence.Column;
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
	@Column(name = "IS_ADMIN")
	private String isAdmin;
	
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
	
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public EmployeesDetails(String employeeName, String employeeId, String employeeEmail, String isAdmin) {
		super();
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeeEmail = employeeEmail;
		this.isAdmin = isAdmin;
	}
	public EmployeesDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
