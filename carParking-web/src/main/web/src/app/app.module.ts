import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';
import { ListdetailsComponent } from './listdetails/listdetails.component';
import { LottreyComponent } from './lottrey/lottrey.component';
import { AppRoutingModule } from './app-routing.module';
import { EmployeeService } from 'src/app/employee/employee.service';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';







@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    ListdetailsComponent,
    LottreyComponent,
    HomeComponent,
    LoginComponent,
    AdminloginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot()
  ],
  schemas : [NO_ERRORS_SCHEMA],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
