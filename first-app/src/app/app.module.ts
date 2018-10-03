import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { SmartComponent } from './smart/chat/smart.component';
import { PresentationComponent } from './presentation/chat/presentation.component';
import { PresentationLoginComponent } from './presentation/login/presentation-login/presentation-login.component';
import { SmartLoginComponent } from './smart/login/smart-login/smart-login.component';
import { SmartMenuBarComponent } from './smart/menu/smart-menu-bar/smart-menu-bar.component';
import { PresentationMenuBarnComponent } from './presentation/menu/presentation-menu-barn/presentation-menu-barn.component';

import { CommunicationService } from './services/communication.service';
import { UserInfoService } from './services/user-info.service';

import { environment } from '../environments/environment';

import { MaterialDesignModule } from './material-design.module';
import { AppRoutingModule } from './router.module';

@NgModule({
  declarations: [
    AppComponent,
    SmartComponent,
    PresentationComponent,
    PresentationLoginComponent,
    SmartLoginComponent,
    SmartMenuBarComponent,
    PresentationMenuBarnComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialDesignModule,
    AppRoutingModule
  ],
  providers: [CommunicationService,
              UserInfoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
