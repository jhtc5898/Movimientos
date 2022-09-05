package com.nttdata.movimientos.controllers;

import com.nttdata.movimientos.dto.AccountDTO;
import com.nttdata.movimientos.exceptions.ErrorRequest;
import com.nttdata.movimientos.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.nttdata.movimientos.Utils.Constants.CODE_ERROR_INTERNAL;

@Slf4j
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountControllers {
    @Autowired
    AccountService accountService;

    @Operation(summary = "Creating new Account")
    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> createAccount(@Valid @RequestBody() AccountDTO.createAccount createAccount) {
        try {
            return ResponseEntity.ok().body(accountService.createAccount(createAccount));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    @Operation(summary = "Edit Account ")
    @PutMapping
    public @ResponseBody
    ResponseEntity<Object> editAccount(@Valid @RequestBody() AccountDTO.editAccount editAccount) {
        try {
            return ResponseEntity.ok().body(accountService.editAccount(editAccount));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }

    }

    @Operation(summary = "Info Account")
    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> infoAccount(@Valid @RequestBody() AccountDTO.infoAccount infoAccount) {
        try {
            return ResponseEntity.ok().body(accountService.infoAccount(infoAccount));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }

    }

    @Operation(summary = "Delete info Acount ")
    @DeleteMapping
    public @ResponseBody
    ResponseEntity<Object> deleteAccount(@Valid @RequestBody() AccountDTO.deleteAccount deleteAccount) {
        try {
            return ResponseEntity.ok().body(accountService.deleteAccount(deleteAccount));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }

    }

}
