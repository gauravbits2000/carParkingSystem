import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Employee } from 'src/app/employee/employee';
import { carParkingSlots } from 'src/app/lottrey/carParkingSlots';


@Injectable()
export class EmployeeService {

  employee: Employee;
  carParkingSlots : carParkingSlots;

  constructor(private httpClient: HttpClient) { }

  getEmployeeDetails(baseURL: string): Observable<any> {
    return this.httpClient.get(baseURL);
  }

  getEmployeeDetailsAll(baseURL: string): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(baseURL);
  }

  getParkingDraw(baseURL: string): Observable<any> {
    return this.httpClient.get(baseURL );
  }

  startLuckyDraw(baseURL: string, params): Observable<any> 
  {
    console.log(params);
    return this.httpClient.post(baseURL,params );
  }

  sendEmail(baseURL: string): Observable<any> 
  {
    return this.httpClient.get(baseURL);
  }

  updateEmployeeDetails(baseURL: string, params): Observable<any> {
    return this.httpClient.post(baseURL, params);
  }
  verifyLoginDetails(baseURL: string, params) {
    return this.httpClient.post(baseURL, params);
  }
  setEmployee(data) {
    this.employee = data;
  }
  getEmployee() {
    return this.employee;
  }

 getCarParkingSlots() 
 {
    return this.carParkingSlots;
  }



}