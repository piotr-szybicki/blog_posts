package main

import (
	"fmt"
	jwt "github.com/dgrijalva/jwt-go"
	"github.com/gorilla/mux"
	"go-app-libs/handlers"
	"go-app-libs/keycloack_interaction"
	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
	"net/http"
	"strings"
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

//to deploy run gcloud app deploy
// dev_appserver.py app.yaml
