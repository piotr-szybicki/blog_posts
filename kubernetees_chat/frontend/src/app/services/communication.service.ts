import { Injectable } from '@angular/core';
import { WebSocketSubject, webSocket } from 'rxjs/websocket';
import { Observable } from 'rxjs';
import { filter } from 'rxjs/operators';
import { environment } from '../../environments/environment'
import { Message } from '../datamodel/message.datamodel'
import { UserInfoService } from './user-info.service'

@Injectable()
export class CommunicationService {

  private socket$: WebSocketSubject<Message>;

  constructor(private userInfo: UserInfoService ) {
    console.log('calling address: ' + environment.chatServerAddress)
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
