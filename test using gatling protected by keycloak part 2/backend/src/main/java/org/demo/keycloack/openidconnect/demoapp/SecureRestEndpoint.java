package org.demo.keycloack.openidconnect.demoapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api")
public class SecureRestEndpoint {

    @GetMapping(path = "/apud", produces = TEXT_PLAIN_VALUE)
    @ResponseBody
    public String simpleRestRequest(){
        return "APUD";
    }
}
