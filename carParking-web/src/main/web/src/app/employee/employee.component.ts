import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from './employee.service';
import { Employee } from 'src/app/employee/employee';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { StartupService } from 'src/app/startup.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employee: Employee;
  employees: Employee[];
  employeeForm;
  myControl = new FormControl();
  poolParking: boolean = false;
  options: string[] = ['Hari', 'Parag', 'Nikhil', 'Piyush', 'Gaurav'];
  userNames: string[];
  filteredOptions: Observable<string[]>;

  constructor(private router: Router, private employeeService: EmployeeService, private startupService: StartupService) { }

  ngOnInit() {

    this.employee = this.employeeService.getEmployee();

    this.userNames = this.startupService.getAllMarkitEmployee();
    
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





  submitDetails() {

    if(this.employee.poolEmployee != null && this.employee.poolEmployeeVehicle){
      this.employee.isCarPool = 'true';
    }else{
      this.employee.poolEmployee = null;
      this.employee.employeeId = null;
      this.employee.poolEmployeeVehicle = null;
      this.employee.isCarPool = null;
    }



    this.employeeService.updateEmployeeDetails("http://localhost:8080//markit-car-parking/employee-registration", this.employee).subscribe((data: any[]) => {
      this.employees = data;
    });
    this.employeeService.setEmployee(this.employee);

    this.router.navigate(['login/listdetails']);
  }

  handlePriorityRequest(event) {

    if (event.target.value === 'pool-parking' && this.employee.isCarPool === null) {
      this.poolParking = true;
    } else {      
      this.poolParking = false;     
    }
  }

  
  optPoolOut(){
    alert('Hiii');
    this.employee.isCarPool = null;
    this.employee.poolEmployee = null;
    this.employee.poolEmployeeVehicle = null;
  }    

}
