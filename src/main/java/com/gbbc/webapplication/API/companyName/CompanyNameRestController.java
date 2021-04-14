package com.gbbc.webapplication.API.companyName;

import com.gbbc.webapplication.services.interfaces.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyNameRestController implements ICompanyNameRestController {

    @Autowired
    BusinessService businessService;

    @Override
    public ResponseEntity<CompanyNameResponse> getCompanyName(@RequestBody CompanyNameRequest request){
        String companyName = businessService.getCompanyNameByAccountNumber(request.getRekeningNr());
        if(!companyName.isEmpty()){
            CompanyNameResponse response = new CompanyNameResponse(companyName);
            return new ResponseEntity<CompanyNameResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<CompanyNameResponse>(HttpStatus.NO_CONTENT);
    }


}
