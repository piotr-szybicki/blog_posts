
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import java.net.URLEncoder
import java.util.UUID

class standalone extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6")
		.doNotTrackHeader("1")
		.disableFollowRedirect
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")

	val headers_0 = Map("Pragma" -> "no-cache")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val json_headers = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6"
		)	
		
	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Origin" -> "http://localhost:8080",
		"Upgrade-Insecure-Requests" -> "1",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6",
		"Cache-Control" -> "max-age=0",
		"Connection" -> "keep-alive"
		)
		
	
	val client_id = "myAngularApplication"
	val realm = "MySecureRealm"
	
	val uri3 = "http://localhost:4200/"

	var backEndService = "http://localhost:4200/OpenIDConnectDemo/api/apud"
	
	val authUri = "http://localhost:8080/auth/realms/"+realm+"/protocol/openid-connect/auth"
	
	val tokenUri = "http://localhost:8080/auth/realms/"+realm+"/protocol/openid-connect/token"
	
	
	
		val scn = scenario("experiment")
			.exec(http("get_login_page")
				.get(authUri)
				.queryParam("client_id", client_id)
				.queryParam("redirect_uri", uri3)
				.queryParam("state",UUID.randomUUID().toString())
				.queryParam("nonce",UUID.randomUUID().toString())
				.queryParam("response_mode", "fragment")
				.queryParam("response_type", "id_token token")
				.queryParam("scope", "openid")
				.headers(headers_3)
				.check(status.is(200))
				.check(css("#kc-form-login")
						.ofType[Node]	
						.transform(variabe => { variabe.getAttribute("action") })
						.saveAs("loginurl"))
				)
			.exec(http("login")
				.post("${loginurl}")
				.headers(headers_3)
				.formParam("username", "user")
				.formParam("password", "user")
				.check(status.is(302))
				.check(header("Location")
						.transform( t=>{ 
							t.substring(t.indexOf("access_token=") + 13 , t.indexOf("&token_type"))
						})
						.saveAs("acces_token"))
				.check(header("Location").saveAs("nextPage"))
				)
			.exec(http("get_iec_page_after_login")
				.get("${nextPage}")
				.headers(headers_3)
				.check(status.is(200))
				)
			.exec(http("fetch_error_codes")
				.get(backEndService)
				.header("Authorization","bearer ${acces_token}")
				.headers(json_headers)
				.check(status.is(200))
				)
			.pause(2)
		
	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}