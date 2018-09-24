import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from  '@angular/forms';
import { EmployeeService } from './employee.service';
import { Employee } from 'src/app/employee/employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  
  employee : Employee;
  employees :Employee[];
  
  constructor(private router: Router,private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  onSubmitDetails(form : NgForm){
    console.log(<Employee>form.value);
    this.employee = <Employee>form.value;
    console.log(this.employee);
     this.employeeService.updateEmployeeDetails("http://localhost:8080//markit-car-parking/employee-registration",this.employee).subscribe((data: any[]) => {
       this.employees = data;
     });

    
    this.router.navigate(['login/listdetails']);
  }

}
