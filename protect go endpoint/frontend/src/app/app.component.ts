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
  }

  myEvent(event) {
   this.httpClient.get('http://localhost:8080/dupa')
   .subscribe(resp => {
     console.log(resp)
   })
 }

}
