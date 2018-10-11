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
	
	private List<CarParkingDetails> generalParkingDetails = null;
	
	public void init() {
		List<CarParkingDetails> carParkingDetails = carParkingDetailsRepository.findAll();
		if(carParkingDetails != null && !carParkingDetails.isEmpty()) {
			carParkingDetailsMap = carParkingDetails.stream().collect(Collectors.groupingBy(CarParkingDetails::getParkingType));
			generalParkingDetails = carParkingDetailsMap.get("Open");
		}
	}
	
	public List<EmployeeRegistration> doCarPoolDraw(Integer carPoolSlots, List<EmployeeRegistration> poolParkingRegistrationList) {
		log.info("************* Doing Car Pool Draw *************");
		
		List<EmployeeRegistration> carPoolWinnersList = new ArrayList<EmployeeRegistration>();

		if (poolParkingRegistrationList == null || poolParkingRegistrationList.size() == 0) {
			log.info("No one applied for Car Pool Parking");
			return carPoolWinnersList;
		}

		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Pool Parking");
		
		// If car Pool Slots are more than applied, no need for random selection
		if (carPoolSlots > poolParkingRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration carPoolEmployee : poolParkingRegistrationList) {
				carPoolEmployee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				carPoolWinnersList.add(carPoolEmployee);
				j++;
			}
		} else {
			IntStream.range(0, carPoolSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(poolParkingRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = poolParkingRegistrationList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				carPoolWinnersList.add(winner);
				
				poolParkingRegistrationList.remove(0);
				log.info("END : Shuffling");
			});
		}
		log.info("************* Car Pool Draw Complete *************");
		return carPoolWinnersList;
	}
	
	public List<EmployeeRegistration> doGeneralDraw(Integer generalSlots, List<EmployeeRegistration> generalParkingRegistrationList) {
		
		log.info("************* Doing General Parking Draw *************");
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();

		if (generalParkingRegistrationList == null || generalParkingRegistrationList.size() == 0) {
			return winnersList;
		}
		
		// If general Slots are more than applied, no need for random selection
		if (generalSlots > generalParkingRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : generalParkingRegistrationList) {
				employee.setCarParkingId(generalParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(0, generalSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(generalParkingRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = generalParkingRegistrationList.get(0);
				winner.setCarParkingId(generalParkingDetails.get(i).getCarParkingId());
				winnersList.add(winner);
				
				generalParkingRegistrationList.remove(0);
				log.info("END : Shuffling");
			});
		}
		log.info("************* General Parking Draw Complete *************");

		return winnersList;
	}
	
	public List<EmployeeRegistration> doMedicalEmergencyDraw(Integer medicalEmergencySlots, List<EmployeeRegistration> medicalEmergencyRegistrationList) {
		log.info("************* Doing Medical Emergency Draw *************");
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();

		if (medicalEmergencyRegistrationList == null || medicalEmergencyRegistrationList.size() == 0) {
			return winnersList;
		}

		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Medical Emergency");
		
		// If medical Emergency Slots are more than applied, no need for random selection
		if (medicalEmergencySlots > medicalEmergencyRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : medicalEmergencyRegistrationList) {
				employee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(0, medicalEmergencySlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(medicalEmergencyRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = medicalEmergencyRegistrationList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				winnersList.add(medicalEmergencyRegistrationList.get(0));
				
				medicalEmergencyRegistrationList.remove(0);
				log.info("END : Shuffling");
			});
		}
		log.info("************* Medical Emergency Draw Complete *************");
		return winnersList;
	}
	
	
	public List<EmployeeRegistration> doFemaleLateShiftDraw(Integer femaleLateShiftSlots, List<EmployeeRegistration> femaleLateShiftRegistrationList) {
		
		log.info("************* Doing Female Night Shift Draw *************");
		
		List<EmployeeRegistration> winnersList = new ArrayList<EmployeeRegistration>();

		if (femaleLateShiftRegistrationList == null || femaleLateShiftRegistrationList.size() == 0) {
			return winnersList;
		}
		
		List<CarParkingDetails> carParkingDetails = carParkingDetailsMap.get("Female Night Shift");

		// If Female Slots are more than applied, no need for random selection
		if (femaleLateShiftSlots > femaleLateShiftRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : femaleLateShiftRegistrationList) {
				employee.setCarParkingId(carParkingDetails.get(j).getCarParkingId());
				winnersList.add(employee);
				j++;
			}
		} else {
			IntStream.range(1, femaleLateShiftSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(femaleLateShiftRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = femaleLateShiftRegistrationList.get(0);
				winner.setCarParkingId(carParkingDetails.get(i).getCarParkingId());
				winnersList.add(femaleLateShiftRegistrationList.get(0));
				
				femaleLateShiftRegistrationList.remove(0);
				log.info("END : Shuffling");
			});
		}

		log.info("************* Female Night Shift Draw Complete *************");
		return winnersList;
	}
	
	public Map<String, List<EmployeeRegistration>> getEmployeeRegistrationBasedOnCategory(){
		init();
		List<EmployeeRegistration> allEmployeeRegistration =  employeeRegistrationRepository.findAll();
		if(allEmployeeRegistration == null || allEmployeeRegistration.isEmpty()) {
			return null;
		}
		return allEmployeeRegistration.stream().collect(Collectors.groupingBy(EmployeeRegistration::getRequestCategory));
	}
	
	public void addSlotsToGeneralPool(String category) {
		if(generalParkingDetails == null) {
			generalParkingDetails = carParkingDetailsMap.get("Open");
		}
		generalParkingDetails.addAll(carParkingDetailsMap.get(category));
	}
}
