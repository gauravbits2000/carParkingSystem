import { Component, OnInit } from '@angular/core';
import { ParkingHistoryService } from 'src/app/parkinghistory/parkinghistory.service';
import { ParkingHistory } from 'src/app/parkinghistory/parkinghistory';
import * as $ from 'jquery';

@Component({
  selector: 'app-parkinghistory',
  templateUrl: './parkinghistory.component.html',
  styleUrls: ['./parkinghistory.component.css']
})
export class ParkinghistoryComponent implements OnInit {

  parkingHistoryList : ParkingHistory[];
  p: number = 1;
  dataAvailable : boolean = false;
  toolTipSring : string;
  view : string = 'list';
  
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
    this.parkingHistoryList = null;
      this.parkingHistoryList = <ParkingHistory[]>data;
      console.log(this.parkingHistoryList.length);
      if(this.parkingHistoryList.length > 0){
        this.dataAvailable = true;
        this.populateFloorPlan(this.parkingHistoryList);
      }else{
        this.dataAvailable = false;
      }      
    }, err => {
      console.log(err);
    })
}


populateFloorPlan(parkingHistoryList : ParkingHistory[]){

  $('.parkingslot').each(function(key,value){

     if(parkingHistoryList[key] === undefined){
      $(value).addClass('nonmarkit_parking');
      $(value).children().text('Accenture');
      $(value).children('img').attr('src','./assets/no-parking-sign.svg')
    }else{
      $(value).addClass(parkingHistoryList[key].requestCategory);
      $(value).children('.parkingID').text('MKT-'+parkingHistoryList[key].carParkingId);
      $(value).attr('data-placement','top');
      $(value).attr('data-toggle','tooltip');
      $(value).attr('title',parkingHistoryList[key].employeeName + " ("+parkingHistoryList[key].vehicleRegistrationNumber+")");
      
    }
    
    
  });
  
}

handleView(view){
  this.view = view;
  this.submitDetails();
}


}
