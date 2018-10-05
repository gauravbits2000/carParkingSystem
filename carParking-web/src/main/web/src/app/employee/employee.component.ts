import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from './employee.service';
import { Employee } from 'src/app/employee/employee';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { StartupService } from 'src/app/startup.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  
  employee : Employee;
  employees :Employee[];
  employeeForm;
  myControl = new FormControl();
  poolParking : boolean = false;
  //userNames: string[] = ['Hari', 'Parag', 'Nikhil','Piyush','Gaurav'];
  userNames: string[];
  filteredOptions: Observable<string[]>;
  
  constructor(private router: Router,private employeeService: EmployeeService,private startupService: StartupService) { }

  ngOnInit() {
    this.employee = this.employeeService.getEmployee();
    
    this.userNames = this.startupService.getAllMarkitEmployee();
    this.myControl.setValue(this.employee.poolEmployee);
    if(this.employee.isCarPool === 'true'){
      this.poolParking = true;
    }else {
      this.poolParking = false;
    }
    
    
    this.employeeForm = new FormGroup({
      employeeName: new FormControl(this.employee.employeeName),
      employeeId: new FormControl(this.employee.employeeId),
      email: new FormControl(this.employee.email),
      vehicleRegistrationNumber: new FormControl(this.employee.vehicleRegistrationNumber),
      isCarPool: new FormControl(this.employee.isCarPool),
      poolEmployee: this.myControl,//new FormControl(''),
      poolEmployeeVehicle: new FormControl(this.employee.poolEmployeeVehicle),
      poolEmployeeId: new FormControl(this.employee.poolEmployeeId)      
});

  this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    
  }
  
   private _filter(value: string): string[] {

    const filterValue = value.toLowerCase();
    return this.userNames.filter(user => user.toLowerCase().includes(filterValue));
  }





  onSubmitDetails(){
   
    this.employee = <Employee>this.employeeForm.value;
     this.employeeService.updateEmployeeDetails("http://localhost:8080//markit-car-parking/employee-registration",this.employee).subscribe((data: any[]) => {
       this.employees = data;
     });
     this.employeeService.setEmployee(this.employee);
     
    this.router.navigate(['login/listdetails']);
  }
  
  toggleEditable(event) {
     if ( event.target.checked ) {
         this.poolParking = true;
    }else {
        this.poolParking = false;
    }
}

}
