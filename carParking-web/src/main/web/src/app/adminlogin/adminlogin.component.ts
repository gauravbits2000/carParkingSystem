import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/employee/employee.service';
import { Employee } from 'src/app/employee/employee';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {

  username: string;
  password: string;
  isValidLogin: boolean = true;
  employee: Employee;
  constructor(private router: Router, private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  tryLogin() {

    const params = { username: this.username, password: this.password };

    this.employeeService.verifyLoginDetails("http://localhost:8080/markit-car-parking/login", params).subscribe((data: any) => {
      this.employee = <Employee>data;
      console.log(this.employee.isAdmin);
      if (this.employee.isAuthorize === 'True') {
        this.employeeService.setEmployee(data);
        this.router.navigateByUrl('/login/submitdetails');

      } else {
        this.isValidLogin = false;
      }
    }, err => {
      console.log(err);
    });
  }

}
