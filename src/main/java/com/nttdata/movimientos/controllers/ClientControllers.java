package com.nttdata.movimientos.controllers;

import com.nttdata.movimientos.dto.ClientDTO;
import com.nttdata.movimientos.exceptions.ErrorRequest;
import com.nttdata.movimientos.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.nttdata.movimientos.Utils.Constants.CODE_ERROR_INTERNAL;

@Slf4j
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientControllers {

    @Autowired
    ClientService clientService;

    @Operation(summary = "Creating new client")
    @PostMapping
    public @ResponseBody
    ResponseEntity<Object> createClient(@Valid @RequestBody() ClientDTO.createClient clientDTO) {
        try {
            return ResponseEntity.ok().body(clientService.createClient(clientDTO));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }

    }

    @Operation(summary = "Edit new client")
    @PutMapping
    public @ResponseBody
    ResponseEntity<Object> editClient(@Valid @RequestBody() ClientDTO.editClient clientDTO) {
        try {
            return ResponseEntity.ok().body(clientService.editClient(clientDTO));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    @Operation(summary = "List info client")
    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> listClient(@Valid @RequestBody() ClientDTO.clientIdent clientDTO) {
        try {
            return ResponseEntity.ok().body(clientService.listClient(clientDTO));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }

    }

    @Operation(summary = "Delete info client")
    @DeleteMapping
    public @ResponseBody
    ResponseEntity<Object> deleteClient(@Valid @RequestBody() ClientDTO.clientIdent clientDTO) {
        try {
            return ResponseEntity.ok().body(clientService.deleteClient(clientDTO));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }
}
