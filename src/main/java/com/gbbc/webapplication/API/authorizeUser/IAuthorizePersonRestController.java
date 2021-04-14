package com.gbbc.webapplication.API.authorizeUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IAuthorizePersonRestController {
    @GetMapping("/customers")
    List<String> getAllCustomers();

    @PostMapping(value = "/check-username", produces = "application/json")
    ResponseEntity<AuthorizePersonResponse> getUsernameAuthorization(@RequestBody AuthorizePersonRequest request);

    @PostMapping("/get-emily")
    String getAuthorizedPerson(@RequestBody String naam);
}
