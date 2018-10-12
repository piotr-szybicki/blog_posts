import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Message } from '../../datamodel/message.datamodel';

@Component({

  selector: 'app-presentation',
  templateUrl: './presentation.component.html',
  styleUrls: ['./presentation.component.css']
})
export class PresentationComponent implements OnInit {

  @Input() messageForm: FormGroup;
  @Input() messages: Message[];
  @Input() availableUsers: Observable<string[]>;
  @Input() user: string;
  @Output() sendMessageEvent: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  sendMessage(): void {
    this.sendMessageEvent.emit(this.messageForm.value.messageText);
    this.messageForm.reset();
  }
}
