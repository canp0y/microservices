
package com.eazybytes.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetchcustomer")
    public ResponseEntity<CustomerDto> getCustomer(@Valid @RequestParam

                                                String customerId) {

        CustomerDto customerDto = iAccountsService.getCustomer(customerId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountsService.updateCustomer(customerDto);
        if (isUpdated) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
}
