import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpSentEvent, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';

  constructor(private httpClient: HttpClient) {}

  ngOnInit() {
    this.httpClient
      .get('OpenIDConnectDemo/api/apud', {responseType: 'text'})
      .subscribe( response => {alert(response); })
    ;
  }
}
