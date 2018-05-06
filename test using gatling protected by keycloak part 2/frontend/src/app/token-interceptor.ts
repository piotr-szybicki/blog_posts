import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/observable/fromPromise';

import { KeycloakService, KeycloakAuthGuard, KeycloakAngularModule } from 'keycloak-angular';
import { HttpHeaders } from '@angular/common/http/src/headers';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(protected keycloak: KeycloakService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headers: HttpHeaders = req.headers;
    const hedersWithAuthorization: HttpHeaders = headers.append('Authorization', 'bearer ' + this.keycloak.getKeycloakInstance().token);
    const requestWithAuthorizationHeader = req.clone({ headers: hedersWithAuthorization });
    return next.handle(requestWithAuthorizationHeader);
  }
}
