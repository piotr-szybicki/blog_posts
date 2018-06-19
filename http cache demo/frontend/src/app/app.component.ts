import {AfterViewInit, Component, Output} from '@angular/core';
import {Subject} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'app';

  constructor(private  http: HttpClient) {}

  private  longString: string;

  getLongString(event: Event) {
    this.http.get('/bookmarks/longString' ).subscribe(t => { console.log(t); });
  }

  getHardToComputeString(event: Event) {
    this.http.get<any>('/bookmarks/hardToComputeString' )
      .subscribe(t => { console.log(t);
                              this.longString = t.field; });
  }

  getNonCachableString(event: Event) {
    this.http.get('/bookmarks/nonCacheableSpring' ).subscribe(t => { console.log(t); });
  }

  cachangeTheEnteryInTheCache(event: Event) {
    this.http.get('/bookmarks/clearCache' ).subscribe(t => { console.log(t); });
  }

  ngAfterViewInit(): void {

  }

}
