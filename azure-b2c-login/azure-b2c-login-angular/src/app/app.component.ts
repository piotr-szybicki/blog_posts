import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'azure-b2c-login';

  constructor(private httpClinet: HttpClient) {}

  public call1(): void {
    this.httpClinet.get('/home')
    .subscribe(t => console.log(t));
  }

  public call2(): void {
    this.httpClinet.get('/api/group1')
    .subscribe(t => console.log(t));
  }

  public call3(): void {
    this.httpClinet.get('/api/group2')
    .subscribe(t => console.log(t));
  }

}
