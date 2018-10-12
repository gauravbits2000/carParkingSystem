package com.markit.org.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="QUARTER_PARKING_RESULT")
public class QuarterParkingResult {
	
	@EmbeddedId
	private QuarterResultIdentity identity;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name = "VEHICLE_REGISTRATION_NUMBER")
	private String vehicleRegistrationNumber;
	
	@Column(name = "CAR_PARKING_ID")
	private String carParkingId;
	
	@Column(name = "POOL_EMPLOYEE_NAME")
	private String poolEmployeeName;
	
	@Column(name = "POOL_EMPLOYEE_ID")
	private String poolEmployeeId;
	
	@Column(name = "POOL_EMPLOYEE_VEHICLE")
	private String poolEmployeeVehicle;
	
	@Column(name = "REQUEST_CATEGORY")
	private String requestCategory;
	
	@Column(name = "EMPLOYEE_EMAIL")
	private String email;
	
	@Column(name = "POOL_EMPLOYEE_EMAIL")
	private String poolEmployeeEmailId;

	public QuarterParkingResult() {
		super();
	}

	public QuarterParkingResult(QuarterResultIdentity identity, String employeeName, String vehicleRegistrationNumber,
			String carParkingId, String poolEmployeeName, String poolEmployeeId, String poolEmployeeVehicle,
			String requestCategory, String email, String poolEmployeeEmailId) {
		super();
		this.identity = identity;
		this.employeeName = employeeName;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.carParkingId = carParkingId;
		this.poolEmployeeName = poolEmployeeName;
		this.poolEmployeeId = poolEmployeeId;
		this.poolEmployeeVehicle = poolEmployeeVehicle;
		this.requestCategory = requestCategory;
		this.email = email;
		this.poolEmployeeEmailId = poolEmployeeEmailId;
	}

	public QuarterResultIdentity getIdentity() {
		return identity;
	}

	public void setIdentity(QuarterResultIdentity identity) {
		this.identity = identity;
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

	public String getCarParkingId() {
		return carParkingId;
	}

	public void setCarParkingId(String carParkingId) {
		this.carParkingId = carParkingId;
	}

	public String getPoolEmployeeName() {
		return poolEmployeeName;
	}

	public void setPoolEmployeeName(String poolEmployeeName) {
		this.poolEmployeeName = poolEmployeeName;
	}

	public String getPoolEmployeeId() {
		return poolEmployeeId;
	}

	public void setPoolEmployeeId(String poolEmployeeId) {
		this.poolEmployeeId = poolEmployeeId;
	}

	public String getPoolEmployeeVehicle() {
		return poolEmployeeVehicle;
	}

	public void setPoolEmployeeVehicle(String poolEmployeeVehicle) {
		this.poolEmployeeVehicle = poolEmployeeVehicle;
	}

	public String getRequestCategory() {
		return requestCategory;
	}

	public void setRequestCategory(String requestCategory) {
		this.requestCategory = requestCategory;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPoolEmployeeEmailId() {
		return poolEmployeeEmailId;
	}

	public void setPoolEmployeeEmailId(String poolEmployeeEmailId) {
		this.poolEmployeeEmailId = poolEmployeeEmailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carParkingId == null) ? 0 : carParkingId.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		result = prime * result + ((poolEmployeeId == null) ? 0 : poolEmployeeId.hashCode());
		result = prime * result + ((poolEmployeeName == null) ? 0 : poolEmployeeName.hashCode());
		result = prime * result + ((poolEmployeeVehicle == null) ? 0 : poolEmployeeVehicle.hashCode());
		result = prime * result + ((requestCategory == null) ? 0 : requestCategory.hashCode());
		result = prime * result + ((vehicleRegistrationNumber == null) ? 0 : vehicleRegistrationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuarterParkingResult other = (QuarterParkingResult) obj;
		if (carParkingId == null) {
			if (other.carParkingId != null)
				return false;
		} else if (!carParkingId.equals(other.carParkingId))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		if (poolEmployeeId == null) {
			if (other.poolEmployeeId != null)
				return false;
		} else if (!poolEmployeeId.equals(other.poolEmployeeId))
			return false;
		if (poolEmployeeName == null) {
			if (other.poolEmployeeName != null)
				return false;
		} else if (!poolEmployeeName.equals(other.poolEmployeeName))
			return false;
		if (poolEmployeeVehicle == null) {
			if (other.poolEmployeeVehicle != null)
				return false;
		} else if (!poolEmployeeVehicle.equals(other.poolEmployeeVehicle))
			return false;
		if (requestCategory == null) {
			if (other.requestCategory != null)
				return false;
		} else if (!requestCategory.equals(other.requestCategory))
			return false;
		if (vehicleRegistrationNumber == null) {
			if (other.vehicleRegistrationNumber != null)
				return false;
		} else if (!vehicleRegistrationNumber.equals(other.vehicleRegistrationNumber))
			return false;
		return true;
	}
}
