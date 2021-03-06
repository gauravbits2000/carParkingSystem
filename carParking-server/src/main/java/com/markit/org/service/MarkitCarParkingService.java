package com.markit.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.email.EmailSender;
import com.markit.org.entity.CarParkingSlots;
import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.entity.EmployeesDetails;
import com.markit.org.entity.QuarterParkingResult;
import com.markit.org.repository.EmployeeDetailsRepository;
import com.markit.org.repository.EmployeeRegistrationRepository;

@Service
public class MarkitCarParkingService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRegistrationRepository employeeRegistrationRepository;
	
	@Autowired
	private EmployeeDetailsRepository empDetailsepository;
	
	@Autowired
	private ParkingDrawService parkingDrawService;
	
	@Autowired
	private QuarterResultService quarterResultService;
	
	private List<QuarterParkingResult> quarterParkingResultList;
	
	public List<EmployeeRegistration> registerEmployee(EmployeeRegistration employee){
		if(employee == null) {
			return null;
		}
		
		if(RequestCategory.POOL_PARKING.getCategory().equals(employee.getRequestCategory())) {
			EmployeesDetails empDetails = empDetailsepository.findByEmployeeName(employee.getPoolEmployee());
			employee.setPoolEmployeeId(empDetails.getEmployeeId());
			employee.setPoolEmployeeEmailId(empDetails.getEmployeeEmail());
			
			//Check if Pooled employee has a single entry
			Optional<EmployeeRegistration> primaryEmployeeOptional = employeeRegistrationRepository.findById(empDetails.getEmployeeId());
			if(primaryEmployeeOptional.isPresent()) {
				employeeRegistrationRepository.delete(primaryEmployeeOptional.get());
			}
			
			//check if Primary Employee has changed its Pool Partner
			Optional<EmployeeRegistration> primaryEmployeeChangingPartnerOptional = employeeRegistrationRepository.findById(employee.getEmployeeId());
			if(primaryEmployeeChangingPartnerOptional.isPresent()) {
				EmployeeRegistration er = primaryEmployeeChangingPartnerOptional.get();
				if(er.getPoolEmployeeId() != null && !employee.getPoolEmployeeId().equals(er.getPoolEmployeeId())) {
					Optional<EmployeesDetails> prevPooledEmployeeDetails = empDetailsepository.findById(er.getPoolEmployeeId());
					String emailId = null;
					if(prevPooledEmployeeDetails.isPresent()) {
						emailId = prevPooledEmployeeDetails.get().getEmployeeEmail();
					}
					EmployeeRegistration employeeReg = new EmployeeRegistration();
					employeeReg.setEmployeeId(er.getPoolEmployeeId());
					employeeReg.setEmployeeName(er.getPoolEmployee());
					employeeReg.setEmail(emailId);
					employeeReg.setRequestCategory(RequestCategory.GENERAL_PARKING.getCategory());
					employeeReg.setVehicleRegistrationNumber(er.getPoolEmployeeVehicle());
					employeeRegistrationRepository.save(employeeReg);
				}
			}
		}
		
		if(RequestCategory.GENERAL_PARKING.getCategory().equals(employee.getRequestCategory()) 
				|| RequestCategory.MEDICAL_EMERGENCY.getCategory().equals(employee.getRequestCategory()) 
				|| RequestCategory.FEMALE_NIGHT_SHIFT.getCategory().equals(employee.getRequestCategory())) {
			// Find the employee Details in primary and if exists with car pool then remove that carpool information
			Optional<EmployeeRegistration> primaryEmployeeOptional = employeeRegistrationRepository.findById(employee.getEmployeeId());
			if(primaryEmployeeOptional.isPresent()) {
				employeeRegistrationRepository.delete(primaryEmployeeOptional.get());
			}
			
			// Find the employee Details in Pooled Employee	and remove pool employee info and create a new row for the employee in General Parking		
			Optional<EmployeeRegistration> pooledEmployeeOptional = employeeRegistrationRepository.findByPoolEmployeeId(employee.getEmployeeId());
			if(pooledEmployeeOptional.isPresent()) {
				EmployeeRegistration pooledEmployee = pooledEmployeeOptional.get();
				pooledEmployee.setPoolEmployee(null);
				pooledEmployee.setPoolEmployeeId(null);
				pooledEmployee.setPoolEmployeeVehicle(null);
				pooledEmployee.setIsCarPool(null);
				pooledEmployee.setPoolEmployeeEmailId(null);
				pooledEmployee.setVehicleRegistrationNumber(employee.getVehicleRegistrationNumber());
				pooledEmployee.setRequestCategory(RequestCategory.GENERAL_PARKING.getCategory());
				employeeRegistrationRepository.save(pooledEmployee);
			}
		}
		
		employeeRegistrationRepository.save(employee);
		return employeeRegistrationRepository.findAll();
	}

	public List<EmployeeRegistration> doCarParkingDraw(CarParkingSlots carParkingSlots){	
		log.info("Getting all registered Employees");

		Integer medicalEmergencySlots = carParkingSlots.getMedicalEmergencySlots();	// Default 5
		Integer femaleLateShiftSlots = carParkingSlots.getFemaleLateShiftSlots();	// Default 5
		Integer carPoolSlots = carParkingSlots.getCarPoolSlots();					// Default 5
		Integer generalSlots = carParkingSlots.getGeneralSlots();					// Default 10
		Integer reservedSlots = carParkingSlots.getReservedSlots();					// Default 5
		Integer totalSlots = carParkingSlots.getTotalSlots();						// Total  30 slots
		
		List<EmployeeRegistration> finalWinnersList = new ArrayList<EmployeeRegistration>();
		
		Map<String, List<EmployeeRegistration>> allEmployeeRegistrationMap = parkingDrawService.getEmployeeRegistrationBasedOnCategory();
		List<EmployeeRegistration> generalParkingRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.GENERAL_PARKING.getCategory());
		
		parkingDrawService.init(totalSlots, (totalSlots-reservedSlots));
		
		//Car Pool Draw
		if(carPoolSlots != null && carPoolSlots > 0) {
			List<EmployeeRegistration> poolParkingRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.POOL_PARKING.getCategory());
			List<EmployeeRegistration> poolParkingWinnersList = parkingDrawService.doCarPoolDraw(carPoolSlots, poolParkingRegistrationList);
			finalWinnersList.addAll(poolParkingWinnersList);
		}else {
			List<EmployeeRegistration> poolParkingRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.POOL_PARKING.getCategory());
			if(poolParkingRegistrationList != null && poolParkingRegistrationList.size() > 0){
				generalParkingRegistrationList.addAll(poolParkingRegistrationList);
			}
		}
		
		//Female Draw
		if(femaleLateShiftSlots != null && femaleLateShiftSlots > 0) {
			List<EmployeeRegistration> femaleLateShiftRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.FEMALE_NIGHT_SHIFT.getCategory());
			List<EmployeeRegistration> femaleLateShiftWinnersList = parkingDrawService.doFemaleLateShiftDraw(femaleLateShiftSlots, femaleLateShiftRegistrationList);
			finalWinnersList.addAll(femaleLateShiftWinnersList);
		}else {
			List<EmployeeRegistration> femaleLateShiftRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.FEMALE_NIGHT_SHIFT.getCategory());
			if(femaleLateShiftRegistrationList != null && femaleLateShiftRegistrationList.size() > 0){
				generalParkingRegistrationList.addAll(femaleLateShiftRegistrationList);
			}			
		}
		
		//Medical Emergency
		if(medicalEmergencySlots != null && medicalEmergencySlots > 0) {
			List<EmployeeRegistration> medicalEmergencyRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.MEDICAL_EMERGENCY.getCategory());
			List<EmployeeRegistration> medicalEmergencyWinnersList = parkingDrawService.doMedicalEmergencyDraw(medicalEmergencySlots, medicalEmergencyRegistrationList);
			finalWinnersList.addAll(medicalEmergencyWinnersList);
		}else {
			List<EmployeeRegistration> medicalEmergencyRegistrationList = allEmployeeRegistrationMap.get(RequestCategory.MEDICAL_EMERGENCY.getCategory());
			if(medicalEmergencyRegistrationList != null && medicalEmergencyRegistrationList.size() > 0){
				generalParkingRegistrationList.addAll(medicalEmergencyRegistrationList);
			}			
		}
		
		//General Draw
		List<EmployeeRegistration> winnersList = parkingDrawService.doGeneralDraw(generalSlots, generalParkingRegistrationList);
		finalWinnersList.addAll(winnersList);
		
		quarterParkingResultList = quarterResultService.saveQuarterResults(finalWinnersList, "Q4");
		
		log.info("returning all lucky winners, Total Employee number : " +finalWinnersList.size());
		return finalWinnersList;
	}

	public List<EmployeeRegistration> viewEmployeeList() {
		List<EmployeeRegistration> employeeList = employeeRegistrationRepository.findAll();
		return employeeList;
	}

	public List<String> fetchMarkitEmployees() {
		List<EmployeesDetails> empDetailsList = empDetailsepository.findAll();
		List<String> empNameList = new ArrayList<String>();
		empDetailsList.forEach(empDetail ->  empNameList.add(empDetail.getEmployeeName()));
		return  empNameList;
		
	}
	
	public List<QuarterParkingResult> fetchCarParkingResults(String quarter){
		return quarterResultService.fetchQuarterResults(quarter);
	}
	
	public void sendEmail() {
		if(quarterParkingResultList == null || quarterParkingResultList.isEmpty()) {
			log.info("Draw yet to happen, returning");
			return;
		}
		
		String emailTo = "Hari.Gupta@ihsmarkit.com,nikhil.chitranshi@markit.com,parag.garg@ihsmarkit.com,Gaurav.Agarwal@ihsmarkit.com";
		new EmailSender(emailTo, null, null, "Q4");
		
		/*quarterParkingResultList.forEach(i -> {
			String emailTo = i.getEmail();
			if(RequestCategory.POOL_PARKING.getCategory().equals(i.getRequestCategory())) {
				emailTo = i.getEmail()+","+ i.getPoolEmployeeEmailId();
			}
			
			new EmailSender(emailTo, i.getEmployeeName(), i.getPoolEmployeeName(), i.getIdentity().getQuarter());
		});*/
	}

	enum RequestCategory{
		POOL_PARKING("pool_parking"),
		GENERAL_PARKING("general_parking"),
		MEDICAL_EMERGENCY("medical_emergency"),
		FEMALE_NIGHT_SHIFT("female_night_shift");
		
		private String category;
		
		private RequestCategory(String category) {
			this.category = category;
		}
		
		public String getCategory() {
			return category;
		}
	}
}
