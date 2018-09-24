import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {

   username : String;
   password : String;
   isValidLogin : boolean = true;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  tryLogin(){
   
    if(this.username === 'admin' && this.password === 'admin'){
        this.isValidLogin =true;
        this.router.navigateByUrl('/login/submitdetails');
    }else{
      this.isValidLogin =false;
    }

  }

}
