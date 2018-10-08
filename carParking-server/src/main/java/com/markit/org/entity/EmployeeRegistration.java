package com.markit.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "EMPLOYEE_REGISTRATION_DETAILS")
public class EmployeeRegistration {

	@Id
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "title")
	private String title;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "telephoneNumber")
	private String telephoneNumber;

	@Column(name = "location")
	private String location;

	@Column(name = "VEHICLE_REGISTRATION_NUMBER")
	private String vehicleRegistrationNumber;

	@Column(name = "IS_AUTHORIZED")
	private String isAuthorize;

	@Column(name = "IS_CAR_POOL")
	private String isCarPool;

	@Column(name = "CAR_PARKING_ID")
	private String carParkingId;

	@Column(name = "Pool_Employee")
	private String poolEmployee;

	@Column(name = "Pool_Employee_ID")
	private String poolEmployeeId;

	@Column(name = "Pool_Employee_Vehicle")
	private String poolEmployeeVehicle;

	@Transient
	private String imageUrl;

	public EmployeeRegistration() {
		super();
	}

	public EmployeeRegistration(String employeeId, String employeeName, String vehicleRegistrationNumber) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public EmployeeRegistration(String employeeId, String employeeName, String vehicleRegistrationNumber, String email,
			String title) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.email = email;
		this.title = title;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize;
	}

	public String getIsCarPool() {
		return isCarPool;
	}

	public void setIsCarPool(String isCarPool) {
		this.isCarPool = isCarPool;
	}

	public String getPoolEmployee() {
		return poolEmployee;
	}

	public void setPoolEmployee(String poolEmployee) {
		this.poolEmployee = poolEmployee;
	}

	public String getPoolEmployeeVehicle() {
		return poolEmployeeVehicle;
	}

	public void setPoolEmployeeVehicle(String poolEmployeeVehicle) {
		this.poolEmployeeVehicle = poolEmployeeVehicle;
	}

	public String getCarParkingId() {
		return carParkingId;
	}

	public void setCarParkingId(String carParkingId) {
		this.carParkingId = carParkingId;
	}

	public String getPoolEmployeeId() {
		return poolEmployeeId;
	}

	public void setPoolEmployeeId(String poolEmployeeId) {
		this.poolEmployeeId = poolEmployeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
