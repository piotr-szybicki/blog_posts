import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppComponent } from './app.component';

import { MsAdalAngular6Module } from 'microsoft-adal-angular6';
import { MsAdalAngular6Service } from 'microsoft-adal-angular6';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptorService} from './token-interceptor.service';

function initializer(adalService: MsAdalAngular6Service) {
  return () => new Promise((resolve, reject) => {
    if (adalService.isAuthenticated) {
      resolve();
    } else {
      adalService.login();
    }
  });
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MsAdalAngular6Module.forRoot({
      tenant: '689c417e-2596-4b1e-ad56-976712af76a1',
      clientId: '52342c78-c557-48ef-8f09-be40c2093edf',
      redirectUri: window.location.origin,
      navigateToLoginRequestUrl: false,
      cacheLocation: 'localStorage'
  })
  ],
  providers: [
    {
    provide: APP_INITIALIZER,
    useFactory: initializer,
    multi: true,
    deps: [MsAdalAngular6Service]
  },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
