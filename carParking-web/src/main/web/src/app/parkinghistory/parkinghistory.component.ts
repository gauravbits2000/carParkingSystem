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
  p: number = 1;
  dataAvailable : boolean = false;
  
  constructor(private parkinghistoryService: ParkingHistoryService) { }

  ngOnInit() {

    this.optionSelected = "Q3";
    this.submitDetails();
  }

options = ["Q1", "Q2", "Q3","Q4"];
optionSelected: string;

//sorting
key: string = 'empname'; //set default
reverse: boolean = false;
sort(key){
  this.key = key;
  this.reverse = !this.reverse;
}

submitDetails(){
  console.log(this.optionSelected);

  this.parkinghistoryService.getParkingHistory("http://localhost:8080/markit-car-parking/car-parking-results/"+this.optionSelected).subscribe((data: any[]) => {
      this.parkingHistoryList = <ParkingHistory[]>data;
      console.log(this.parkingHistoryList.length);
      if(this.parkingHistoryList.length > 0){
        this.dataAvailable = true;
      }else{
        this.dataAvailable = false;
      }
      
      
    }, err => {
      console.log(err);
    })
}

}
