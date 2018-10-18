package com.markit.org.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.repository.EmployeeRegistrationRepository;

@Service
public class ParkingDrawService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRegistrationRepository employeeRegistrationRepository;
	
	private List<Integer> carParkingId = new ArrayList<Integer>();
	
	public void init(int totalSlots, int availableSlots) {
		totalSlots = totalSlots + 100;
		ThreadLocalRandom.current().ints(100, totalSlots).distinct().limit(availableSlots).forEach(i -> carParkingId.add(i));
	}
	
	public List<EmployeeRegistration> doCarPoolDraw(Integer carPoolSlots, List<EmployeeRegistration> poolParkingRegistrationList) {
		log.info("************* Doing Car Pool Draw *************");
		
		List<EmployeeRegistration> carPoolWinnersList = new ArrayList<EmployeeRegistration>();

		if (poolParkingRegistrationList == null || poolParkingRegistrationList.size() == 0) {
			log.info("No one applied for Car Pool Parking");
			return carPoolWinnersList;
		}

		// If car Pool Slots are more than applied, no need for random selection
		if (carPoolSlots >= poolParkingRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration carPoolEmployee : poolParkingRegistrationList) {
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				carPoolEmployee.setCarParkingId(parkingId);
				carPoolWinnersList.add(carPoolEmployee);
				j++;
			}
		} else {
			Collections.shuffle(poolParkingRegistrationList, new SecureRandom());
			
			IntStream.range(0, carPoolSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(poolParkingRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = poolParkingRegistrationList.get(0);
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				winner.setCarParkingId(parkingId);
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
		if (generalSlots >= generalParkingRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : generalParkingRegistrationList) {
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				employee.setCarParkingId(parkingId);
				winnersList.add(employee);
				j++;
			}
		} else {
			
			Collections.shuffle(generalParkingRegistrationList, new SecureRandom());
			
			IntStream.range(0, carParkingId.size()).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(generalParkingRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = generalParkingRegistrationList.get(0);
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				winner.setCarParkingId(parkingId);
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

		// If medical Emergency Slots are more than applied, no need for random selection
		if (medicalEmergencySlots >= medicalEmergencyRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : medicalEmergencyRegistrationList) {
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				employee.setCarParkingId(parkingId);
				winnersList.add(employee);
				j++;
			}
		} else {
			Collections.shuffle(medicalEmergencyRegistrationList, new SecureRandom());
			
			IntStream.range(0, medicalEmergencySlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(medicalEmergencyRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = medicalEmergencyRegistrationList.get(0);
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				winner.setCarParkingId(parkingId);
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
		
		// If Female Slots are more than applied, no need for random selection
		if (femaleLateShiftSlots >= femaleLateShiftRegistrationList.size()) {
			int j=0;
			for(EmployeeRegistration employee : femaleLateShiftRegistrationList) {
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				employee.setCarParkingId(parkingId);
				winnersList.add(employee);
				j++;
			}
		} else {
			Collections.shuffle(femaleLateShiftRegistrationList, new SecureRandom());
			
			IntStream.range(1, femaleLateShiftSlots).forEach(i -> {
				log.info("START : Shuffling " + i + " Times");
				Collections.shuffle(femaleLateShiftRegistrationList, new SecureRandom());
				
				log.info("Choosing a lucky winner !!");
				EmployeeRegistration winner = femaleLateShiftRegistrationList.get(0);
				String parkingId = ""+carParkingId.get(0);
				carParkingId.remove(0);
				winner.setCarParkingId(parkingId);
				winnersList.add(femaleLateShiftRegistrationList.get(0));
				
				femaleLateShiftRegistrationList.remove(0);
				log.info("END : Shuffling");
			});
		}

		log.info("************* Female Night Shift Draw Complete *************");
		return winnersList;
	}
	
	public Map<String, List<EmployeeRegistration>> getEmployeeRegistrationBasedOnCategory(){
		List<EmployeeRegistration> allEmployeeRegistration =  employeeRegistrationRepository.findAll();
		if(allEmployeeRegistration == null || allEmployeeRegistration.isEmpty()) {
			return null;
		}
		return allEmployeeRegistration.stream().collect(Collectors.groupingBy(EmployeeRegistration::getRequestCategory));
	}
	
	/*public void addSlotsToGeneralPool(String category) {
		if(generalParkingDetails == null) {
			generalParkingDetails = carParkingDetailsMap.get("Open");
		}
		generalParkingDetails.addAll(carParkingDetailsMap.get(category));
	}*/
}
