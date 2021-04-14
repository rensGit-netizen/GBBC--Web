package com.gbbc.webapplication.API.transferRequest;

import com.gbbc.webapplication.services.interfaces.TransferRestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    TransferRestServiceInterface transferRestService;

    @PostMapping(value = "/api/saldo", produces = "application/json")
    public ResponseEntity<TransferResponse> tranferMoney(@RequestBody TransferRequest transferRequest) {
        TransferResponse transferResponse = transferRestService.getTransferResponse(transferRequest);
        return new ResponseEntity<TransferResponse>(transferResponse, HttpStatus.OK);
    }

}
