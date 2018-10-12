package chat

type msg struct {
	Sender      string
	Reciver     string
	Payload     interface{}
	Type        string
	IsBroadcast bool
}
