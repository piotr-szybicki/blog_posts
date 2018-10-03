import {NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SmartLoginComponent } from './smart/login/smart-login/smart-login.component';
import { SmartComponent } from './smart/chat/smart.component';

const routes: Routes = [
    { path: '', component: SmartLoginComponent },
    { path: 'chat', component: SmartComponent },
    { path: 'login', component: SmartLoginComponent },
    { path: '*', component : SmartLoginComponent}
  ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
