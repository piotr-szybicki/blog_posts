import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService {


  private userLogin: string;
  private chatRoomId = 'xx';

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
