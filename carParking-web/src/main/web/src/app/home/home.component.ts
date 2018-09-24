import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  isLoginPage : boolean = false;
  constructor(private router: Router) { }

  ngOnInit() {
  }

  employeeLogin(){
    this.router.navigateByUrl('/login/submitdetails');
  }
  adminLogin(){
    this.router.navigate(['/adminlogin'])
  }

}
