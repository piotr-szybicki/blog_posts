import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm } from '@angular/forms'

import { WebSocketSubject, webSocket } from 'rxjs/websocket';
import { Observable, Subject, from } from 'rxjs';
import { map, take, filter } from 'rxjs/operators';

import { UUID } from 'angular2-uuid';
import { Message } from './datamodel/message.datamodel'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy  {

  constructor(){}

  ngOnDestroy() {}

  ngOnInit() {}

}
