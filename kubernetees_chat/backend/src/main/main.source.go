package main

import (
	"chat-server/src/chat"
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", chat.Ping)
	http.HandleFunc("/chat/api", chat.GetAllChatUsers)
	http.HandleFunc("/chat/ws", chat.WsHandler)

	http.ListenAndServe(":8080", nil)
	fmt.Println("server is running ...")
}
