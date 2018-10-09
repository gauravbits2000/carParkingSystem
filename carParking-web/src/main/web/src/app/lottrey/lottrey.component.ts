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
  resultTable: Employee[];
  //resultTempTable: Employee[];
  
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
    isFinal:boolean = true;
  
    numberOfPaginators1;itemsPerPage1=5;;
  paginators1: Array<any> = [];
  
  
  /*  resultTable = [
    new Employee("11", "Abc", "abc@gmail.com", "DL 5C 6789"),
    new Employee("22", "Abc1", "abc@gmail.com", "DL 5C 6789"),
    new Employee("34", "Abc2", "abc@gmail.com", "DL 5C 6789"),
    new Employee("41", "Abc3", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
    new Employee("25", "Abc4", "abc@gmail.com", "DL 5C 6789")    
  ];*/

  resultTempTable = [
    new Employee("11", "Abc", "abc@gmail.com", "DL 5C 6789"),
    new Employee("22", "Abc1", "abc@gmail.com", "DL 5C 6789"),
    new Employee("34", "Abc2", "abc@gmail.com", "DL 5C 6789"),
    new Employee("41", "Abc3", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),

  ]
  
    blankTableData = [
    new Employee("", "", "", "a"),
    new Employee("", "", "", "b"),
    new Employee("", "", "", "b"),
    new Employee("", "", "", "b"),
    new Employee("", "", "", "b")    
  ];
  
  
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
 	   this.employeeService.getEmployeeDetailsAll("/fetch-employees").subscribe((data: Employee[]) => 
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
    let empList = this.employeeList;
    let result_temp = this.resultTempTable;
    let result = this.resultTable;


    var table1 = $(".card1");
    var table2 = $(".card2");
    var table3 = $(".card3");

    $('.table2 td:nth-child(4)').css({color:"white"});
    $('#final').css("visibility","hidden");
  
  
    let firstVisibleIndex = this.firstVisibleIndex;
    let lastVisibleIndex = this.lastVisibleIndex;
    let firstVisiblePaginator = this.firstVisiblePaginator;
    let pages = this.pages;

if (this.resultTable.length % this.itemsPerPage1 === 0) {
       
        this.numberOfPaginators1 = Math.floor(this.resultTable.length / this.itemsPerPage1);
    } else {
        this.numberOfPaginators1 = Math.floor(this.resultTable.length / this.itemsPerPage1 + 1);
    }

    console.log(this.resultTable);
    for (let i = 1; i <= this.numberOfPaginators1; i++) {
        this.paginators1.push(i);
    }




      table1.addClass('inner-container-animate1').delay(1000).queue(function () {
      table1.addClass('inner-container-animate2').delay(1000).queue(function () {
      table2.addClass('inner-container-animate1');
      table2.addClass('inner-container-animate3');

      table3.addClass('inner-container-animate1');
      table3.addClass('inner-container-animate4');
        
        $('.img1').animate({ opacity: '1' }, 1000, function () {          
          for (var i = firstVisibleIndex,j=1; i <= lastVisibleIndex; i++,j++) {
            (function (i) {
              console.log(i);
                window.setTimeout(function () {
                $('.table1 tr:nth-child(' + i + ') td:nth-child(2)').addClass("text-bold1");                 
              }, j * 500);
            }(i));
          } 

          setTimeout(()=>{        
            table1.css({opacity:'0'});
           $('.inner-container1').css({opacity:'1'});           
            table2.css({opacity:'1'});

            for (var i = 1; i <= result_temp.length; i++) {
              (function (i) {
                console.log(i);
                  window.setTimeout(function () {   
                    //console.log($('.table3 tr:nth-child(' + i + ') td:nth-child(2)').html());       
                  $('.table3 tr:nth-child(' + i + ') td:nth-child(2)').css({"position":"relative",'visibility':'visible'})
                  .animate({bottom:'+=780px'},1000).animate({left:'+=550px'},1000);
                }, i * 1000);
              }(i));
            } 

            window.setTimeout(function(){
              $('.img1').animate({ opacity: '0' },"slow"); 
              this.isFinal = false;
              $('.table3 td:nth-child(2)').css({'visibility':'hidden'});


              $("#temp").css("visibility","hidden");
              $('#final').css("visibility","visible");
              $('.table2 td:nth-child(4)').css({color:"black"});
              
            },result_temp.length*2000);
          },j*1000);          
        });
      }).dequeue();
    });  
    
    
        
  }

  getLuckyDrawResult(){
    console.log("data");
    this.employeeService.getParkingDraw("/markit-car-parking/lucky-draw/").subscribe((data: any[]) => {
      this.resultTable = <Employee[]>data;
      this.getTempLuckyDrawResult();
      this.Start();
    }, err => {
      console.log(err);
    })
    
  }
  
  getTempLuckyDrawResult(){
  
  for(var i=0;i<5;i++){
  	this.resultTempTable[i] = this.resultTable[i];	
  
  }
  
  }

}
