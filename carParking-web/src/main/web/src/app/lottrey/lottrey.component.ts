import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/employee/employee.service';
import { Employee } from 'src/app/employee/employee';

@Component({
  selector: 'app-lottrey',
  templateUrl: './lottrey.component.html',
  styleUrls: ['./lottrey.component.css']
})
export class LottreyComponent implements OnInit {

  employeeList : Employee[];
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  startDraw(){
    console.log("data");
    this.employeeService.getParkingDraw("http://localhost:8080//markit-car-parking/lucky-draw/").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
    }, err => {
      console.log(err);
    })
    
  }

}
