import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpSentEvent, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public title:string = 'app';

  constructor(private httpClient: HttpClient) {}

  ngOnInit() {
  }


  myEvent(event:Event) {
   this.httpClient.get('/dupa')
   .subscribe(resp => {
     console.log(resp)
   })
 }

}
