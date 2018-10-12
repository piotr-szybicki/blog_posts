export class Message {
    constructor(
        public Sender: string,
        public Reciver: string,
        public Payload: any,
        public Type: string, // two type key exange or message or list of users
        public IsBroadcast = false,
    ) { }
}
