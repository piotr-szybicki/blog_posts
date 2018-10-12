import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { takeWhile, skip, debounceTime } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-presentation-login',
  templateUrl: './presentation-login.component.html',
  styleUrls: ['./presentation-login.component.css']
})
export class PresentationLoginComponent implements OnInit {

  @Output() userAttpemptToLogin:EventEmitter<any> = new EventEmitter();
  @Input() screenActive: boolean;
  @Input() availableUsers: Observable<string[]>;

  loginForm:FormGroup  = new FormGroup({
    'username': new FormControl(''),
    'chatroomname' : new FormControl('')
  });;

  constructor() {}

  ngOnInit() {}
 
  login(): void {
    this.userAttpemptToLogin.emit({chatRoomId: this.loginForm.value.chatroomname,userId: this.loginForm.value.username});
  }
}
