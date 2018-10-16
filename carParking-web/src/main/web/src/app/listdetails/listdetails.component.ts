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
  page: number = 1;
  p: number = 1;
  dataAvailable : boolean = false;
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    setTimeout(()=>{

      this.employeeService.getEmployeeDetails("http://localhost:8080/fetch-employees").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
      this.dataAvailable = true;
    }, err => {
      console.log(err);
    })

    }, 500);
  }

  //sorting
key: string = 'empname'; //set default
reverse: boolean = false;
sort(key){
  this.key = key;
  this.reverse = !this.reverse;
}

  viewDetails(){
    console.log("data");
    this.employeeService.getEmployeeDetails("http://localhost:8080/fetch-employees").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
      this.dataAvailable = true;
    }, err => {
      console.log(err);
    })
    
  }

}
