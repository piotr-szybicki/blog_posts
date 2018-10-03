import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WebSocketSubject, webSocket } from 'rxjs/websocket';
import { Observable, Subject, from } from 'rxjs';
import { map, take, filter } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Message } from '../datamodel/message.datamodel';
import { UserInfoService } from './user-info.service';

@Injectable()
export class CommunicationService {

  private socket$: WebSocketSubject<Message>;

  constructor(private userInfo: UserInfoService ) {
    this.socket$ = webSocket(environment.chatServerAddress
                          + '?login=' + this.userInfo.getUserLogin());
  }


  public chatMessageObserver(): Observable<Message> {
    return this.socket$
      .asObservable()
      .pipe(filter((message) => message.Type == 'Message'));
  }

  public sendMessage(message: Message): void {
    this.socket$.next(message);
  }

}
