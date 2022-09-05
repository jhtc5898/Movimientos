package com.nttdata.movimientos.controllers;

import com.nttdata.movimientos.dto.TransactionsDTO;
import com.nttdata.movimientos.exceptions.ErrorRequest;
import com.nttdata.movimientos.services.TransactionsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

import static com.nttdata.movimientos.Utils.Constants.CODE_ERROR_INTERNAL;

@Slf4j
@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionsControllers {

    @Autowired
    TransactionsService transactionsService;

    @Operation(summary = "Creating new transactions")
    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> createClient(@Valid @RequestBody() TransactionsDTO.createTransactions createTransactions) {
        try {
            return ResponseEntity.ok().body(transactionsService.createTransactions(createTransactions));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    @Operation(summary = "List info transactions")
    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> listtransactions(@Valid @RequestBody() TransactionsDTO.listTransactions listTransactions) throws ParseException {
        try {
            return ResponseEntity.ok().body(transactionsService.listTransactions(listTransactions));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    @Operation(summary = "Delete info client")
    @DeleteMapping
    public @ResponseBody
    ResponseEntity<Object> deleteTransactions(@Valid @RequestBody() TransactionsDTO.deleteTransactions deleteTransactions) {
        try {
            return ResponseEntity.ok().body(transactionsService.deleteTransactions(deleteTransactions));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }
}
