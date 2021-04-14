package com.gbbc.webapplication.API.authorizeUser;
/**
 * @author Emily
 * @author Rens
 */
import com.gbbc.webapplication.services.interfaces.AuthorizePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorizePersonRestController implements IAuthorizePersonRestController {

    @Autowired
    private AuthorizePersonService authorizePersonService;

    @Override
    public List<String> getAllCustomers(){
        return this.authorizePersonService.getAllCustomers();
    }

    @Override
    public ResponseEntity<AuthorizePersonResponse> getUsernameAuthorization(@RequestBody AuthorizePersonRequest request){
        boolean check = authorizePersonService.isAlreadyCustomer(request.getUsername());
        if(check){
            AuthorizePersonResponse response = new AuthorizePersonResponse("found");
            response.setMessage("Gebruiker bestaat al! Klik hier om in te loggen.");
            return new ResponseEntity<AuthorizePersonResponse>(response, HttpStatus.OK);
        }
        new AuthorizePersonResponse("not found");
        return new ResponseEntity<AuthorizePersonResponse>(HttpStatus.NO_CONTENT);
    }

    @Override
    public String getAuthorizedPerson(@RequestBody String naam){
        return "emily" + naam;
    }
}
