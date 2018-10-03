package com.markit.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="CAR_PARKING_DETAILS")
public class CarParkingDetails 
{	
	@Id
	@Column(name="CAR_PARKING_ID")
	private String carParkingId;

	@Column(name="PARKING_FLOOR")
	private String parkingfloor;
		
	public CarParkingDetails() {
		super();
	}

	public CarParkingDetails(String carParkingId, String parkingfloor) 
	{
		super();
		this.carParkingId = carParkingId;
		this.parkingfloor = parkingfloor;
		
	}

	public String getCarParkingId() {
		return carParkingId;
	}

	public void setCarParkingId(String carParkingId) {
		this.carParkingId = carParkingId;
	}

	public String getParkingfloor() {
		return parkingfloor;
	}

	public void setParkingfloor(String parkingfloor) {
		this.parkingfloor = parkingfloor;
	}
	


	
	
}
