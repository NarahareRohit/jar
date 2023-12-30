package com.app.service;

import java.util.List;

import com.app.dto.TransactionDTO;
import com.app.entities.TransactionEntity;

public interface TransactionService {

	void addTransaction(TransactionDTO transaction);

	List<TransactionEntity> getTransactions();
	
	
}
