import { Component, OnInit, ViewChildren, QueryList, ElementRef } from '@angular/core';
import * as $ from 'jquery';
import { EmployeeService } from 'src/app/employee/employee.service';
import { Employee } from 'src/app/employee/employee';


@Component({
  selector: 'app-lottrey',
  templateUrl: './lottrey.component.html',
  styleUrls: ['./lottrey.component.css']
})
export class LottreyComponent implements OnInit {

  employeeList : Employee[];
  
  @ViewChildren('pages') pages: QueryList<any>;
  itemsPerPage = 5;
  numberOfVisiblePaginators = 10;
  numberOfPaginators: number;
  paginators: Array<any> = [];
  activePage = 1;
  firstVisibleIndex = 1;
  lastVisibleIndex: number = this.itemsPerPage;
  firstVisiblePaginator = 0;
  lastVisiblePaginator = this.numberOfVisiblePaginators;
  startDraw: boolean;
  
  constructor(private employeeService: EmployeeService, private el: ElementRef) { }

changePage(event: any) {
    if (event.target.text >= 1 && event.target.text <= this.numberOfPaginators) {
      this.activePage = +event.target.text;
      this.firstVisibleIndex = this.activePage * this.itemsPerPage - this.itemsPerPage + 1;
      this.lastVisibleIndex = this.activePage * this.itemsPerPage;
    }
  }

  nextPage(event: any) {
    if (this.pages.last.nativeElement.classList.contains('active')) {
      if ((this.numberOfPaginators - this.numberOfVisiblePaginators) >= this.lastVisiblePaginator) {
        this.firstVisiblePaginator += this.numberOfVisiblePaginators;
        this.lastVisiblePaginator += this.numberOfVisiblePaginators;
      } else {
        this.firstVisiblePaginator += this.numberOfVisiblePaginators;
        this.lastVisiblePaginator = this.numberOfPaginators;
      }
    }

    this.activePage += 1;
    this.firstVisibleIndex = this.activePage * this.itemsPerPage - this.itemsPerPage + 1;
    this.lastVisibleIndex = this.activePage * this.itemsPerPage;
  }

  previousPage(event: any) {
    if (this.pages.first.nativeElement.classList.contains('active')) {
      if ((this.lastVisiblePaginator - this.firstVisiblePaginator) === this.numberOfVisiblePaginators) {
        this.firstVisiblePaginator -= this.numberOfVisiblePaginators;
        this.lastVisiblePaginator -= this.numberOfVisiblePaginators;
      } else {
        this.firstVisiblePaginator -= this.numberOfVisiblePaginators;
        this.lastVisiblePaginator -= (this.numberOfPaginators % this.numberOfVisiblePaginators);
      }
    }

    this.activePage -= 1;
    this.firstVisibleIndex = this.activePage * this.itemsPerPage - this.itemsPerPage + 1;
    this.lastVisibleIndex = this.activePage * this.itemsPerPage;
  }

  firstPage() {
    this.activePage = 1;
    this.firstVisibleIndex = this.activePage * this.itemsPerPage - this.itemsPerPage + 1;
    this.lastVisibleIndex = this.activePage * this.itemsPerPage;
    this.firstVisiblePaginator = 0;
    this.lastVisiblePaginator = this.numberOfVisiblePaginators;
  }

  lastPage() {
    this.activePage = this.numberOfPaginators;
    this.firstVisibleIndex = this.activePage * this.itemsPerPage - this.itemsPerPage + 1;
    this.lastVisibleIndex = this.activePage * this.itemsPerPage;

    if (this.numberOfPaginators % this.numberOfVisiblePaginators === 0) {
      this.firstVisiblePaginator = this.numberOfPaginators - this.numberOfVisiblePaginators;
      this.lastVisiblePaginator = this.numberOfPaginators;
    } else {
      this.lastVisiblePaginator = this.numberOfPaginators;
      this.firstVisiblePaginator = this.lastVisiblePaginator - (this.numberOfPaginators % this.numberOfVisiblePaginators);
    }
  }


 	ngOnInit() 
 	{
 	   this.employeeService.getEmployeeDetailsAll("http://localhost:8080//fetch-employees").subscribe((data: Employee[]) => 
	 	   {
	 	   console.log(data);
	      this.employeeList = data;
	        console.log(this.employeeList);
    
	    this.startDraw = false;
	    if (this.employeeList.length % this.itemsPerPage === 0) {
	      this.numberOfPaginators = Math.floor(this.employeeList.length / this.itemsPerPage);
	    } else {
	      this.numberOfPaginators = Math.floor(this.employeeList.length / this.itemsPerPage + 1);
	    }
	
	    console.log(this.employeeList);
	    for (let i = 1; i <= this.numberOfPaginators; i++) {
	      this.paginators.push(i);
	    }
	       }, err => {
	      console.log(err);
	       })
    

  }
  
    Start() 
    {
    this.startDraw = true;
    let emp = this.employeeList;
    var table = $(".card");

    table.addClass('inner-container-animate1').delay(3000).queue(function () {
      table.addClass('inner-container-animate2').delay(3000).queue(function () {
        console.log(this.show);
        $('img').animate({ opacity: '1' }, 1000, function () {
          for (var i = 1; i <= emp.length; i++) {
            (function (i) {
              console.log(i);
              window.setTimeout(function () {
                $('.table tr:nth-child(' + i + ') td:nth-child(2)').addClass("text-bold1");
              }, i * 1000);
            }(i));
          }
        });
      }).dequeue();
    });

    $('.table td').css({ 'border-bottom': '1px solid grey' });
  }

  startDraw1(){
    console.log("data");
    this.employeeService.getParkingDraw("http://localhost:8080//markit-car-parking/lucky-draw/").subscribe((data: any[]) => {
      this.employeeList = <Employee[]>data;
    }, err => {
      console.log(err);
    })
    
  }

}
