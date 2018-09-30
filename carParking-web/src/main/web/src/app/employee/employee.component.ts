import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from './employee.service';
import { Employee } from 'src/app/employee/employee';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  
  employee : Employee;
  employees :Employee[];
  employeeForm;
  
  constructor(private router: Router,private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = this.employeeService.getEmployee();
    this.employeeForm = new FormGroup({
      employeeName: new FormControl(this.employee.employeeName),
      employeeId: new FormControl(this.employee.employeeId),
      email: new FormControl(this.employee.email),
      vehicleRegistrationNumber: new FormControl(this.employee.vehicleRegistrationNumber)
});
    
  }





  onSubmitDetails(){
   
    this.employee = <Employee>this.employeeForm.value;
     this.employeeService.updateEmployeeDetails("http://localhost:8080//markit-car-parking/employee-registration",this.employee).subscribe((data: any[]) => {
       this.employees = data;
     });

    
    this.router.navigate(['login/listdetails']);
  }

}
