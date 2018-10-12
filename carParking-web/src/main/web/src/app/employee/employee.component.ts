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
  validationFailed : boolean = false;
  errorText : string;
  terms : boolean = false;

  constructor(private router: Router, private employeeService: EmployeeService, private startupService: StartupService) { }

  ngOnInit() {

    this.employee = this.employeeService.getEmployee();

    this.userNames = this.startupService.getAllMarkitEmployee();

    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );

    if (this.employee.requestCategory === null) {
      this.employee.requestCategory = 'general_parking';
    }
  }

  private _filter(value: string): string[] {

    const filterValue = value.toLowerCase();
    return this.userNames.filter(user => user.toLowerCase().includes(filterValue));
  }





  submitDetails() {

    if(this.employee.vehicleRegistrationNumber == '' || this.employee.vehicleRegistrationNumber === null){
      this.validationFailed = true;
      this.errorText = '* Please provide Vehicle Registration Number';
      return;
    
    }else if(this.terms === false){
      this.validationFailed = true;
      this.errorText = '* Please accept terms and conditions';
      return;
    }







    if(this.employee.poolEmployee !==null && this.employee.poolEmployeeVehicle !==null){
      this.employee.isCarPool = "true";
    }

    if(this.employee.requestCategory !== 'pool_parking'){
      {
        this.employee.isCarPool = null;
        this.employee.poolEmployee =null;
        this.employee.poolEmployeeVehicle =null
      }
    }

    this.employeeService.updateEmployeeDetails("http://localhost:8080//markit-car-parking/employee-registration", this.employee).subscribe((data: any[]) => {
      this.employees = data;
    });
    this.employeeService.setEmployee(this.employee);

    this.router.navigate(['login/listdetails']);
  }

  optOutPool(){
    this.employee.isCarPool = null;
  }

}
