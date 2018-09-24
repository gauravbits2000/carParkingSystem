import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/employee/employee.service';
import { Employee } from 'src/app/employee/employee';

@Component({
  selector: 'app-listdetails',
  templateUrl: './listdetails.component.html',
  styleUrls: ['./listdetails.component.css']
})
export class ListdetailsComponent implements OnInit {

  employeeList : Employee[];
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    setTimeout(()=>{

      this.employeeService.getEmployeeDetails("http://localhost:8080/fetch-employees").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
    }, err => {
      console.log(err);
    })

    }, 500);
  }

  viewDetails(){
    console.log("data");
    this.employeeService.getEmployeeDetails("http://localhost:8080/fetch-employees").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
    }, err => {
      console.log(err);
    })
    
  }

}
