import { Component, OnInit } from '@angular/core';
import { ParkingHistoryService } from 'src/app/parkinghistory/parkinghistory.service';
import { ParkingHistory } from 'src/app/parkinghistory/parkinghistory';

@Component({
  selector: 'app-parkinghistory',
  templateUrl: './parkinghistory.component.html',
  styleUrls: ['./parkinghistory.component.css']
})
export class ParkinghistoryComponent implements OnInit {

  parkingHistoryList : ParkingHistory[];
  constructor(private parkinghistoryService: ParkingHistoryService) { }

  ngOnInit() {
  }

options = ["Q1", "Q2", "Q3","Q4"];
optionSelected: string;

submitDetails(){
  console.log(this.optionSelected);
  this.parkinghistoryService.getParkingHistory("http://localhost:8080//markit-car-parking/car-parking-results/"+this.optionSelected).subscribe((data: any[]) => {
      this.parkingHistoryList = <ParkingHistory[]>data;
    }, err => {
      console.log(err);
    })
}

}
