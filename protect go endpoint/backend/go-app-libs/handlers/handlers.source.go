package handlers

import (
	"fmt"
	jwt "github.com/dgrijalva/jwt-go"
	"go-app-libs/keycloack_interaction"
	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
	"net/http"
	"strings"
)

func LogHandler(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		ctx := appengine.NewContext(r)
		// Do stuff here
		log.Debugf(ctx, r.RequestURI)
		// Call the next handler, which can be another middleware in the chain, or the final handler.
		next.ServeHTTP(w, r)
	})
}

func AuthHandler(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		ctx := appengine.NewContext(r)
		// Do stuff here
		log.Debugf(ctx, "auth is going in here")

		authorization := r.Header.Get("Authorization")

		if authorization == "" || len(authorization) == 0 {
			http.Error(w, "Authorization header is missing", 401)
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

		if token != nil && token.Valid {
			next.ServeHTTP(w, r)
		} else {
			http.Error(w, "Token validation error: "+err.Error(), 401)
		}

	})
}
