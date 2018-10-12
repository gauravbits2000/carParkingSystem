export class Employee {
    employeeName: string;
    employeeId: string;
    email: string;
    vehicleRegistrationNumber: string;
    isAuthorize: string;
    isCarPool: string;
    poolEmployee: string;
    poolEmployeeVehicle: string;
    poolEmployeeId 	   : string;
    title : string;
    location : string;
    mobile : string;
    telephoneNumber : string;
    imageUrl : string;
    requestCategory : string;
    isAdmin : string;
    
    constructor(employeeName: string,
    employeeId: string,
    email: string,
    vehicleRegistrationNumber: string){
    	this.employeeName = employeeName;
    	this.employeeId = employeeId;
    	this.email = email;
    	this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    	
    
    }
}