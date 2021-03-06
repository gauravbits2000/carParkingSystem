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

  parkingHistoryList: ParkingHistory[];
  p: number = 1;
  dataAvailable: boolean = false;
  toolTipSring: string;
  view: string = 'list';

  constructor(private parkinghistoryService: ParkingHistoryService) { }

  ngOnInit() {

    this.optionSelected = "Q3";
    this.submitDetails();
  }

  options = ["Q1", "Q2", "Q3", "Q4"];
  optionSelected: string;

  //sorting
  key: string = 'empname'; //set default
  reverse: boolean = false;
  sort(key) {
    this.key = key;
    this.reverse = !this.reverse;
  }

  submitDetails() {
    console.log(this.optionSelected);

    this.parkinghistoryService.getParkingHistory("http://localhost:8080/markit-car-parking/car-parking-results/" + this.optionSelected).subscribe((data: any[]) => {
      this.parkingHistoryList = null;
      this.parkingHistoryList = <ParkingHistory[]>data;
      console.log(this.parkingHistoryList.length);
      if (this.parkingHistoryList.length > 0) {
        this.populateFloorPlan(this.parkingHistoryList);
        this.dataAvailable = true;
      } else {
        this.dataAvailable = false;
        this.removeParkingCategories();
        $('.parkingslot').addClass('unallocated');
        $('.parkingslot').children('.parkingID').text('Unallocated');
      }
    }, err => {
      console.log(err);
    })
  }


  populateFloorPlan(parkingHistoryList: ParkingHistory[]) {

    this.removeParkingCategories();

    $('.parkingslot').each(function (key, value) {

      if (parkingHistoryList[key] === undefined) {
        $(value).addClass('unallocated');
        $(value).children('.parkingID').text('Unallocated');
      } else {
        $(value).addClass(parkingHistoryList[key].requestCategory);
        $(value).children('.parkingID').text('MKT-' + parkingHistoryList[key].carParkingId);
        $(value).attr('data-placement', 'top');
        $(value).attr('data-toggle', 'tooltip');
        $(value).attr('title', parkingHistoryList[key].employeeName + " (" + parkingHistoryList[key].vehicleRegistrationNumber + ")");

      }


    });

  }

  handleView(view) {
    this.view = view;
    this.submitDetails();
  }

  downloadPdf(){
    
  }

  removeParkingCategories() {
    $('.parkingslot').removeClass('general_parking');
    $('.parkingslot').removeClass('pool_parking');
    $('.parkingslot').removeClass('female_night_shift');
    $('.parkingslot').removeClass('unallocated');
  }


}
