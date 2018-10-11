package com.markit.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.markit.org.entity.CarParkingDetails;

@Repository
public interface CarParkingDetailsRepository extends JpaRepository<CarParkingDetails, String>{

}
