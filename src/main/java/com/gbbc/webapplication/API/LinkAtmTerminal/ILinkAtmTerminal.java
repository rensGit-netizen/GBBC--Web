package com.gbbc.webapplication.API.LinkAtmTerminal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author E. Koo
 */

public interface ILinkAtmTerminal {
    @GetMapping("/all-codes")
    List<String> getAllCodes();

    @PostMapping(value = "/validate-code", produces = "application/json")
    ResponseEntity<LinkAtmTerminalResponse> getCodeValidation(@RequestBody LinkAtmTerminalRequest requestIban, LinkAtmTerminalRequest requestCode);

    @PostMapping(value = "/check-for-request", produces = "application/json")
    ResponseEntity<LinkAtmTerminalResponse> getAccountNrValidation(@RequestBody LinkAtmTerminalRequest requestiban);
}
