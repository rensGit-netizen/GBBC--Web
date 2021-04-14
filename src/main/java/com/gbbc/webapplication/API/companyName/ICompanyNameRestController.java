package com.gbbc.webapplication.API.companyName;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICompanyNameRestController {

    @PostMapping(value = "/check-company", produces = "application/json")
    ResponseEntity<CompanyNameResponse> getCompanyName(@RequestBody CompanyNameRequest request);
}
