import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Store } from '@ngrx/store';
import { Router } from '@angular/router';
import { CommunicationService } from '../../../services/communication.service';
import { UserInfoService } from '../../../services/user-info.service';

@Component({
  selector: 'app-smart-login',
  templateUrl: './smart-login.component.html',
  styleUrls: ['./smart-login.component.css']
})
export class SmartLoginComponent implements OnInit {

  constructor(private router: Router, private userService: UserInfoService) {}

  ngOnInit() {}

  login(evant: any): void {
    this.userService.setChatRoomId(evant.chatRoomId);
    this.userService.setUserId(evant.userId);

    this.router.navigate(['/chat']);
  }

}
