package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TransactionDTO;
import com.app.entities.TransactionEntity;
import com.app.service.TransactionService;

@RestController
@RequestMapping("/tran")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/all")
	public List<TransactionEntity> getAllTransaction(){
		return transactionService.getTransactions();
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transactiondto){
        try {
        	transactiondto.setTransactionType(transactiondto.getTransactionType());
        	System.out.println("in tran controller "+transactiondto);
            transactionService.addTransaction(transactiondto);
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding transaction");
        }
	}
	
}
