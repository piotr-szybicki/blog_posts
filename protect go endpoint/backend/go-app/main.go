package main

import (
	"fmt"
	jwt "github.com/dgrijalva/jwt-go"
	"github.com/gorilla/mux"
	"go-app-libs/handlers"
	"go-app-libs/keycloack_interaction"
	"golang.org/x/net/context"
	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
	"net/http"
	"strings"
)

func main() {

	r := mux.NewRouter()

	r.HandleFunc("/dupa", optionsHandler).
		Methods(http.MethodOptions)

	r.HandleFunc("/dupa", getHandler).
		Methods(http.MethodGet)

	r.Use(handlers.LogHandler)
	r.Use(handlers.AuthHandler)

	http.Handle("/", r)

	appengine.Main()
}

func optionsHandler(w http.ResponseWriter, r *http.Request) {
	ctx := appengine.NewContext(r)
	addHeders(w, ctx)
}

func getHandler(w http.ResponseWriter, r *http.Request) {
	ctx := appengine.NewContext(r)
	addHeders(w, ctx)
	log.Debugf(ctx, "traffic detected ...")

	authorization := r.Header.Get("Authorization")

	if authorization == "" || len(authorization) == 0 {
		return
	}

	tokenString := strings.Replace(authorization, `bearer `, ``, -1)

	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {

		if _, ok := token.Method.(*jwt.SigningMethodRSA); !ok {
			return nil, fmt.Errorf("Unexpected signing method: %v", token.Header["alg"])
		}
		pk := keycloack_interaction.FetchPublicKey(ctx)
		return &pk, nil

	})

	log.Errorf(ctx, "error while creating a token: %v, %v", err, token.Header["alg"])
	log.Errorf(ctx, "token valid: %v", token.Valid)

	fmt.Fprintln(w, `{"property" : 1}`)
}

func addHeders(w http.ResponseWriter, ctx context.Context) {
	log.Debugf(ctx, "adding headers to response ")
	w.Header().Set(`content-type`, `application/json;charset=UTF-8`)
	w.Header().Set(`Access-Control-Allow-Origin`, `*`)
	w.Header().Set(`Access-Control-Allow-Methods`, `GET, POST, PUT, DELETE`)
	w.Header().Set(`Access-Control-Allow-Headers`, `Authorization`)
}

//to deploy run gcloud app deploy
// dev_appserver.py app.yaml
