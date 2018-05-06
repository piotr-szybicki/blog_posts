import {AfterViewChecked, Component, OnInit} from '@angular/core';
import {HttpClient, HttpSentEvent, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private httpClient: HttpClient, private keycloak: KeycloakService) {}

  dupa(): void {
    try {
      this.httpClient
        .get('OpenIDConnectDemo/api/apud', {responseType: 'text'})
        .subscribe( response => {alert(response); },
          error1 => {console.log(error1); },
          () => {console.log('complete'); })
      ;

    } catch (e) {
      console.log(e);
    }
  }

}
