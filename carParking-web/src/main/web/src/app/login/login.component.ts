import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/employee/employee.service';
import { Employee } from 'src/app/employee/employee';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  employee: Employee;
  isAdmin: boolean = false;
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = this.employeeService.getEmployee();
    if(this.employee.isAdmin === 'Y'){
      this.isAdmin = true;
    }
  }


  handleNavigation(event){
    $('.nav-link').removeClass('active-nav');
    $(event.target).addClass('active-nav');
  }

}
