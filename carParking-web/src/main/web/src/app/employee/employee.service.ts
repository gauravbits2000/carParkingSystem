import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Employee } from 'src/app/employee/employee';

@Injectable()
export class EmployeeService {

  employee : Employee;
  

    constructor(private httpClient: HttpClient) { }

    getEmployeeDetails(baseURL: string): Observable<any>{
     return this.httpClient.get(baseURL);
      }

    getParkingDraw(baseURL: string): Observable<any>{
    return this.httpClient.get(baseURL);       
    }
     updateEmployeeDetails(baseURL: string,params): Observable<any>{
     return this.httpClient.post(baseURL,params);       
    }
    verifyLoginDetails(baseURL: string,params) {
      return this.httpClient.post(baseURL,params);
    }
    setEmployee(data){
       this.employee = data;
    }
    getEmployee(){
      return this.employee;
   }

}