import { Component, OnInit, ViewChildren, QueryList, ElementRef } from '@angular/core';
import * as $ from 'jquery';
import { EmployeeService } from 'src/app/employee/employee.service';
//import { Employee } from 'src/app/employee/employee';
import { Employee } from 'src/app/employee/employee';
import { carParkingSlots } from 'src/app/lottrey/carParkingSlots';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-lottrey',
  templateUrl: './lottrey.component.html',
  styleUrls: ['./lottrey.component.css']
})
export class LottreyComponent implements OnInit 
{
  model: any = {}; 
  employeeList : Employee[];
  resultTable: Employee[];
 // resultTempTable: Employee[];
  imgPath:string;
  imgPath1:string;
  validatingForm: FormGroup;
  
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
  
  
  /*employeeList = [
    new Employee("11", "Abc", "abc@gmail.com", "DL 5C 6789"),
    new Employee("22", "Abc1", "abc@gmail.com", "DL 5C 6789"),
    new Employee("34", "Abc2", "abc@gmail.com", "DL 5C 6789"),
    new Employee("41", "Abc3", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
    new Employee("25", "Abc4", "abc@gmail.com", "DL 5C 6789")    
  ];*/

  /*resultTable = [
    new Employee("11", "Abc", "abc@gmail.com", "DL 5C 6789"),
    new Employee("22", "Abc1", "abc@gmail.com", "DL 5C 6789"),
    new Employee("34", "Abc2", "abc@gmail.com", "DL 5C 6789"),
    new Employee("41", "Abc3", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
     new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),
    new Employee("25", "Abc4", "abc@gmail.com", "DL 5C 6789")   

  ] ;*/

    resultTempTable = [
    new Employee("11", "Abc", "abc@gmail.com", "DL 5C 6789"),
    new Employee("22", "Abc1", "abc@gmail.com", "DL 5C 6789"),
    new Employee("34", "Abc2", "abc@gmail.com", "DL 5C 6789"),
    new Employee("41", "Abc3", "abc@gmail.com", "DL 5C 6789"),
    new Employee("15", "Abc4", "abc@gmail.com", "DL 5C 6789"),

  ]  
  
  constructor(private employeeService: EmployeeService, private el: ElementRef,private fb: FormBuilder) { 
            }

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
     this.model.medicalEmergencySlots = 5;
     this.model.femaleLateShiftSlots = 5;
     this.model.carPoolSlots = 5;
     this.model.generalSlots = 10;
     this.model.reservedSlots = 5;
      this.model.totalSlots = 30;

 	   this.employeeService.getEmployeeDetailsAll("http://localhost:8080/fetch-employees").subscribe((data: Employee[]) => 
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

       console.log(this.activePage);
	
	    console.log(this.employeeList);
	    for (let i = 1; i <= this.numberOfPaginators; i++) {
	      this.paginators.push(i);
        console.log(this.paginators);


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
    this.imgPath = "../assets/WrapBox.png";
    this.imgPath1 = "../assets/gift_marvel.gif";


    var table1 = $(".card1");
    var table2 = $(".card2");
    var table3 = $(".card3");

   $('#final').css("display","none");  
  
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
        console.log(this.paginators1);
    }

      
      table1.addClass('inner-container-animate1').delay(3000).queue(function () {
      table1.addClass('inner-container-animate2').delay(3000).queue(function () {
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
              }, j * 1000);
            }(i));
          } 

          setTimeout(function(){        
            table1.css({opacity:'0'},1000);
            //table1.fadeOut(1000);
            $('.inner-container1').fadeIn(2000);

           //$('.inner-container1').css({opacity:'1'});           
            table2.css({opacity:'1'});

            $('.img1').css({opacity:0});
            $('.img2').css({opacity:1});
            
            
            var pos1 = $('.table2 tr:nth-child(1) td:nth-child(2)').offset();
                     var pos2 = $('.table3 tr:nth-child(1) td:nth-child(2)').offset();
                     var final_pos_top = (pos2.top-pos1.top) + pos1.top -75;
                     console.log(pos1.left);
                     console.log(pos2.left);
                      var final_pos_left =  (pos1.left-pos2.left)+ pos1.left;                      
                      console.log(final_pos_left);

            for (var i = 1; i <= result_temp.length; i++) {
              (function (i) {
                console.log(i);
                  window.setTimeout(function () {   
                    //console.log($('.table3 tr:nth-child(' + i + ') td:nth-child(2)').html());
                  $('.table3 tr:nth-child(' + i + ') td:nth-child(2)').css({"position":"relative",'visibility':'visible'})
                  .animate({bottom: '+=' +  final_pos_top + 'px'},1000).animate({left: '750px'},1000);
                }, i * 1000);
              }(i));
            } 

            window.setTimeout(function(){
              $('img').animate({ opacity: '0' },"slow"); 
              this.isFinal = false;

              $('.table3 td:nth-child(2)').css({'visibility':'hidden'});
              

              //$("#temp").css({"visibility":"hidden"});
                 //$("#final").css({"visibility":"visible"});

                 $("#temp").fadeOut(2000,function(){
                   //$("#temp").css({"display":"none"});
                    $('.table2 td:nth-child(1)').css({color:"black"});
                $('.table2 td:nth-child(2)').css({color:"black"});
                $('.table2 td:nth-child(3)').css({color:"black"});
                $('.table2 td:nth-child(4)').css({color:"black"}); 
                   
                 });                 

              $("#final").fadeIn(3000,function(){
                //$("#final").css({"visibility":"visible"});
                $('.table2 td:nth-child(1)').css({color:"black"});
                $('.table2 td:nth-child(2)').css({color:"black"});
                $('.table2 td:nth-child(3)').css({color:"black"});
                $('.table2 td:nth-child(4)').css({color:"black"}); 
                $("#final").addClass('element-animation');
              //$("#final").animate({width: '400px',height:'700px'},1000);
              });
              
            },result_temp.length*2000);
          },j*1500);          
        });
      }).dequeue();
    });         
  }

  valuechange(event){
    console.log(event); 
   this.validatingForm = this.fb.group({
        'max': [null, Validators.max(30)],
        });
    this.model.generalSlots = Number.parseInt(this.model.totalSlots) - (Number.parseInt(this.model.medicalEmergencySlots) + Number.parseInt(this.model.femaleLateShiftSlots) + Number.parseInt(this.model.carPoolSlots) + Number.parseInt(this.model.reservedSlots) );
     if(this.model.generalSlots == "NaN") this.model.generalSlots = 0;
  }

  getLuckyDrawResult()
  {
    this.employeeService.startLuckyDraw("http://localhost:8080/markit-car-parking/start-lucky-draw/", this.model).subscribe((data: any[]) => {
    //  this.employeeService.getParkingDraw("http://localhost:8080/markit-car-parking/lucky-draw/").subscribe((data: any[]) => {
      this.resultTable = <Employee[]>data;
      this.getTempLuckyDrawResult();
      console.log("Result" + this.resultTable);
      this.Start();
      //this.startDraw = false;
    }, err => {
      console.log(err);
    })
    
  }

  sendEmail()
  {
    this.employeeService.sendEmail("http://localhost:8080/markit-car-parking/send-email").subscribe((data: any[]) => 
    {
    
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
