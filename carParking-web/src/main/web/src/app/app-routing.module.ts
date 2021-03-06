import { NgModule } from '@angular/core';
import { Routes,RouterModule } from "@angular/router";
import { EmployeeComponent } from './employee/employee.component';
import { ListdetailsComponent } from './listdetails/listdetails.component';
import { LottreyComponent } from './lottrey/lottrey.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { ParkinghistoryComponent } from './parkinghistory/parkinghistory.component';




const appRoutes : Routes = [
    { path : '',component: AdminloginComponent},
    { path : 'parking',component: LoginComponent,children: [
        { path : 'submitdetails', component: EmployeeComponent },
        { path : 'listdetails', component: ListdetailsComponent },
        { path : 'parkingdraw', component: LottreyComponent },
        { path : 'parkinghistory', component: ParkinghistoryComponent }
    ]},
    { path : 'adminlogin',component: AdminloginComponent}
   ];

@NgModule({
  imports : [
      RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule {

}