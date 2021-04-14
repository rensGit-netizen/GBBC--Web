package com.gbbc.webapplication.API.LinkAtmTerminal;

import com.gbbc.webapplication.services.interfaces.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author E. Koo
 */
@RestController
@RequestMapping("/api")
public class LinkAtmTerminalRestController implements ILinkAtmTerminal {

    @Autowired
    TerminalService terminalService;

    @Override
    public List<String> getAllCodes() {
        return terminalService.getAllGeneratedCodes();
    }


    /**
     * Validate iban + code combination
     *
     * @param requestIban from business Customer
     * @param requestCode from business Customer
     * @return OK or BAD_REQUEST
     */
    @Override
    public ResponseEntity<LinkAtmTerminalResponse> getCodeValidation(LinkAtmTerminalRequest requestIban, LinkAtmTerminalRequest requestCode) {
        boolean check = terminalService.isValidCombination(requestIban.getIban(), requestIban.getCode());
        if (check) {
            LinkAtmTerminalResponse response = new LinkAtmTerminalResponse("valid combination");
            response.setMessage("Geldige combinatie voor terminal koppeling");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        LinkAtmTerminalResponse badResponse = new LinkAtmTerminalResponse("in-valid combination");
        badResponse.setMessage("HACKER");
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<LinkAtmTerminalResponse> getAccountNrValidation(LinkAtmTerminalRequest requestiban) {
        boolean validUser = terminalService.isValidUser(requestiban.getIban());
        if (validUser) {
            LinkAtmTerminalResponse response = new LinkAtmTerminalResponse("valid");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        LinkAtmTerminalResponse badResponse = new LinkAtmTerminalResponse("invalid");
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }
}


