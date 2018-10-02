import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA, APP_INITIALIZER} from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MatAutocompleteModule, MatInputModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
import { StartupService } from './startup.service';


export function startupServiceFactory(startupService: StartupService): Function {
  return () => startupService.load();
}




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
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    MatAutocompleteModule,
    MatInputModule
  ],
  schemas : [NO_ERRORS_SCHEMA],
  providers: [EmployeeService,
    StartupService,
    {
         provide: APP_INITIALIZER,
         useFactory: startupServiceFactory,
         deps: [StartupService],
         multi: true
       }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
