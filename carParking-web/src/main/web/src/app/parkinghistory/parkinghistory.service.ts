import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ParkingHistory } from 'src/app/parkinghistory/parkinghistory';

@Injectable()
export class ParkingHistoryService {

    parkingHistory: ParkingHistory;


  constructor(private httpClient: HttpClient) { }

  getParkingHistory(baseURL: string): Observable<any> {
    return this.httpClient.get(baseURL);
  }

}    