import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MsAdalAngular6Service} from 'microsoft-adal-angular6';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'azure-b2c-login';

  constructor(private httpClient: HttpClient, private adalService: MsAdalAngular6Service) {}

  public call1(): void {
    console.log(this.adalService.userInfo);

    this.httpClient.get('/home')
    .subscribe(t => console.log(t));
  }

  public call2(): void {
    this.httpClient.get('/api/group1')
    .subscribe(t => console.log(t));
  }

  public call3(): void {
    this.httpClient.get('/api/group2')
    .subscribe(t => console.log(t));
  }

  public logout(): void {
    this.adalService.logout();
  }

}
