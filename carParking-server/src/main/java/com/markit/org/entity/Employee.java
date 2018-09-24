package com.markit.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE_REGISTRATION_DETAILS")
public class Employee {
	
	@Id
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="VEHICLE_REGISTRATION_NUMBER")
	private String vehicleRegistrationNumber;
	
	public Employee() {
		super();
	}

	public Employee(String employeeId, String employeeName, String vehicleRegistrationNumber) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
}
