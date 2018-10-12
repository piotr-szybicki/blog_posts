package chat

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"net/url"

	"github.com/gorilla/websocket"
)

var m = make(map[string]*websocket.Conn)

func Ping(w http.ResponseWriter, req *http.Request) {
	fmt.Fprintf(w, "Hi there, I love %s!", req.URL.Path[1:])
	log.Println("Request has just arrived")
}

func GetAllChatUsers(w http.ResponseWriter, req *http.Request) {

	w.Header().Add("Access-Control-Allow-Origin", "*")

	s := make([]string, 0)
	for key, _ := range m {
		s = append(s, key)
	}

	list, _ := json.Marshal(s)
	fmt.Println(string(list))
	fmt.Fprintln(w, string(list))
}

func WsHandler(w http.ResponseWriter, r *http.Request) {

	fragments, err := url.ParseQuery(r.URL.RawQuery)
	if err != nil {
		println(err)
		return
	}

	conn, err := websocket.Upgrade(w, r, w.Header(), 1024, 1024)

	if err != nil {
		http.Error(w, "Could not open websocket connection", http.StatusBadRequest)
	}
	login := fragments["login"][0]

	m[login] = conn
	echo(conn)
}

func echo(conn *websocket.Conn) {
	for {
		message := msg{}

		err := conn.ReadJSON(&message)

		if err != nil {
			removeUserFromChats(conn, err)
			break
		}

		if conn, ok := m[message.Reciver]; ok {
			if err = conn.WriteJSON(message); err != nil {
				fmt.Println(err)
			}
		}

	}
}

func removeUserFromChats(conn *websocket.Conn, err error) {
	fmt.Println("Conection error: ", err)
	for user, value := range m {
		if value == conn {
			fmt.Println("removing user from the pool", user)
			delete(m, user)
		}
	}
}
