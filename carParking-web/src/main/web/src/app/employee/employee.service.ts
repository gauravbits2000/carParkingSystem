import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class EmployeeService {

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

}