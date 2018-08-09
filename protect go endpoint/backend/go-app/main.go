package main

import (
	"crypto/rsa"
	"encoding/base64"
	"encoding/json"
	"fmt"
	jwt "github.com/dgrijalva/jwt-go"
	"github.com/gorilla/mux"
	"go-app-libs/datamodel"
	"golang.org/x/net/context"
	"google.golang.org/appengine"
	"google.golang.org/appengine/log"
	"google.golang.org/appengine/urlfetch"
	"io/ioutil"
	"math/big"
	"net/http"
	"strings"
)

func main() {

	r := mux.NewRouter()

	r.HandleFunc("/dupa", optionsHandler).
		Methods(http.MethodOptions)

	r.HandleFunc("/dupa", getHandler).
		Methods(http.MethodGet)
	r.Use(simpleMw)

	http.Handle("/", r)

	appengine.Main()
}

func simpleMw(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		ctx := appengine.NewContext(r)
		// Do stuff here
		log.Debugf(ctx, r.RequestURI)
		// Call the next handler, which can be another middleware in the chain, or the final handler.
		next.ServeHTTP(w, r)
	})
}

func optionsHandler(w http.ResponseWriter, r *http.Request) {
	ctx := appengine.NewContext(r)
	addHeders(w, ctx)
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
		pk := FetchPublicKey(ctx)
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

func FetchPublicKey(ctx context.Context) rsa.PublicKey {
	client := urlfetch.Client(ctx)
	resp, _ := client.Get(`http://localhost:9080/auth/realms/poc/protocol/openid-connect/certs`)
	defer resp.Body.Close()
	body, _ := ioutil.ReadAll(resp.Body)

	log.Errorf(ctx, "fetching keys from keycloack: %v", string(body))

	certs := datamodel.Certs{}
	json.Unmarshal(body, &certs)

	log.Debugf(ctx, "json %v", certs)

	modulus, _ := base64.URLEncoding.DecodeString(certs.Keys[0].N + "=")
	exponent, _ := base64.URLEncoding.DecodeString(certs.Keys[0].E)

	exponent_ := int(new(big.Int).SetBytes(exponent).Int64())
	modulus_ := new(big.Int).SetBytes(modulus)

	pk := rsa.PublicKey{
		N: modulus_,       // modulus
		E: int(exponent_), // public exponent}
	}
	return pk
}

//to deploy run gcloud app deploy
// dev_appserver.py app.yaml
