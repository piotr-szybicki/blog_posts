package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"go-app-libs/handlers"
	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
	"net/http"
)

func main() {

	r := mux.NewRouter()

	r.HandleFunc("/dupa", getHandler).
		Methods(http.MethodGet)

	r.Use(handlers.LogHandler)
	r.Use(handlers.AuthHandler)

	http.Handle("/", r)

	appengine.Main()
}

func getHandler(w http.ResponseWriter, r *http.Request) {
	ctx := appengine.NewContext(r)
	log.Debugf(ctx, "traffic detected ...")

	fmt.Fprintln(w, `{"property" : 1}`)

}

//to deploy run gcloud app deploy
// dev_appserver.py app.yaml
