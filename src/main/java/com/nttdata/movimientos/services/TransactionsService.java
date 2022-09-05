package com.nttdata.movimientos.services;

import com.nttdata.movimientos.dto.TransactionsDTO;

import java.text.ParseException;
import java.util.Map;

public interface TransactionsService {

    Map<String, Object> createTransactions(TransactionsDTO.createTransactions createTransactions);

    Map<String, Object> listTransactions(TransactionsDTO.listTransactions listTransactions) throws ParseException;

    Map<String, Object> deleteTransactions(TransactionsDTO.deleteTransactions deleteTransactions);


}
