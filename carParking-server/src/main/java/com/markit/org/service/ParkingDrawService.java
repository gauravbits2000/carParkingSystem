package com.markit.org.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.CarParkingDetails;
import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.repository.CarParkingDetailsRepository;
import com.markit.org.repository.EmployeeRegistrationRepository;

@Service
public class ParkingDrawService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRegistrationRepository employeeRegistrationRepository;
	
	@Autowired
	private CarParkingDetailsRepository carParkingDetailsRepository;
	
	private Map<String, List<CarParkingDetails>> carParkingDetailsMap ;
	
	public void init() {
		List<CarParkingDetails> carParkingDetails = carParkingDetailsRepository.findAll();
		if(carParkingDetails != null && !carParkingDetails.isEmpty()) {
			carParkingDetailsMap = carParkingDetails.stream().collect(Collectors.groupingBy(CarParkingDetails::getParkingType));
		}
	}
	
	public List<EmployeeRegistration> doCarPoolDraw(Integer carPoolSlots) {
		log.info("************* Doing Car Pool Draw *************");
		
		List<EmployeeRegistration> carPoolWinnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> carPoolEmployeeList = employeeRegistrationRepository.findByCarPool();

		if (carPoolEmployeeList == null || carPoolEmployeeList.size() == 0) {
			log.info("No one applied for Car Pool Parking");
			return carPoolWinnersList;
		}

		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Pool Parking");
		
		// If car Pool Slots are more than applied, no need for random selection
		if (carPoolSlots > carPoolEmployeeList.size()) {
			int j=0;
			for(EmployeeRegistration carPoolEmployee : carPoolEmployeeList) {
				carPoolEmployee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				carPoolWinnersList.add(carPoolEmployee);
				j++;
			}
		} else {
			IntStream.range(0, carPoolSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(carPoolEmployeeList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = carPoolEmployeeList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				carPoolWinnersList.add(winner);
				
				carPoolEmployeeList.remove(0);
				log.info("END : Shuffling");
			});
		}
		log.info("************* Car Pool Draw Complete *************");
		return carPoolWinnersList;
	}
	
	public List<EmployeeRegistration> doGeneralDraw(Integer generalSlots) {
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> employeeList = employeeRegistrationRepository.findAll();

		if (employeeList == null || employeeList.size() == 0) {
			return winnersList;
		}

		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Open");
		
		// If general Slots are more than applied, no need for random selection
		if (generalSlots > employeeList.size()) {
			int j=0;
			for(EmployeeRegistration employee : employeeList) {
				employee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(0, generalSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(employeeList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = employeeList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				winnersList.add(winner);
				
				employeeList.remove(0);
				log.info("END : Shuffling");
			});
		}

		return winnersList;
	}
	
	public List<EmployeeRegistration> doMedicalEmergencyDraw(Integer medicalEmergencySlots) {
		log.info("************* Doing Medical Emergency Draw *************");
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> employeeList = employeeRegistrationRepository.findByMedicalEmergency();

		if (employeeList == null || employeeList.size() == 0) {
			return winnersList;
		}

		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Medical Emergency");
		
		// If medical Emergency Slots are more than applied, no need for random selection
		if (medicalEmergencySlots > employeeList.size()) {
			int j=0;
			for(EmployeeRegistration employee : employeeList) {
				employee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(0, medicalEmergencySlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(employeeList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = employeeList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				winnersList.add(employeeList.get(0));
				
				employeeList.remove(0);
				log.info("END : Shuffling");
			});
		}
		log.info("************* Medical Emergency Draw Complete *************");
		return winnersList;
	}
	
	
	public List<EmployeeRegistration> doFemaleLateShiftDraw(Integer femaleLateShiftSlots) {
		
		log.info("************* Doing Female Night Shift Draw *************");
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();
		List<EmployeeRegistration> employeeList = employeeRegistrationRepository.findByFemaleNightShift();

		if (employeeList == null || employeeList.size() == 0) {
			return winnersList;
		}
		
		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Medical Emergency");

		// If Female Slots are more than applied, no need for random selection
		if (femaleLateShiftSlots > employeeList.size()) {
			int j=0;
			for(EmployeeRegistration employee : employeeList) {
				employee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(1, femaleLateShiftSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(employeeList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = employeeList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				winnersList.add(employeeList.get(0));
				
				employeeList.remove(0);
				log.info("END : Shuffling");
			});
		}

		log.info("************* Female Night Shift Draw Complete *************");
		return winnersList;
	}

}
