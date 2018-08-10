package keycloack_interaction

import (
	"crypto/rsa"
	"encoding/base64"
	"encoding/json"
	"go-app-libs/datamodel"
	"golang.org/x/net/context"
	"google.golang.org/appengine/log"
	"google.golang.org/appengine/urlfetch"
	"io/ioutil"
	"math/big"
)

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
