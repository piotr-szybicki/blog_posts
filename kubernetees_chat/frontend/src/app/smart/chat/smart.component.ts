import { Component, OnInit } from '@angular/core';

import { CommunicationService } from '../../services/communication.service';
import { Message } from '../../datamodel/message.datamodel';
import { UserInfoService } from '../../services/user-info.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-smart',
  templateUrl: './smart.component.html',
  styleUrls: ['./smart.component.css']
})
export class SmartComponent implements OnInit {

  messages: Message[] = [];
  user: string;
  chatRoom: string;
  messageForm: FormGroup = new FormGroup({
    'messageText': new FormControl('')
  });

  constructor(private communicationService: CommunicationService, private userService: UserInfoService) {
    this.user = this.userService.getUserLogin();
    this.chatRoom = this.userService.getChatRoomId();
  }

  ngOnInit() {
    this.communicationService.chatMessageObserver()
    .subscribe(message => {
      this.messages.push(message);
    });
  }

  onMessageSend(message: string) {
    const toBeSend: Message = new Message( this.user, this.chatRoom, { text: message}, 'Message', false);
    console.log(toBeSend);
    this.messages.push(toBeSend);
    this.communicationService.sendMessage(toBeSend);
  }
}
