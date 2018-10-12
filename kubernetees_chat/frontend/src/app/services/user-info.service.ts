import { Injectable } from '@angular/core';
import { AppModule } from '../app.module';

@Injectable()
export class UserInfoService {

  private userLogin: string; 
  private chatRoomId: string = 'xx';

  constructor() {}

  getChatRoomId(): string {
    return this.chatRoomId;
  }

  public getUserLogin(): string {
    return this.userLogin;
  }

  setUserId(userId: string): void {
    this.userLogin = userId;
  }
  setChatRoomId(chatRoomId: string): void {
    this.chatRoomId = chatRoomId;
  }
}
