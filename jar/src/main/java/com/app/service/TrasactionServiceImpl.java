package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.TransactionDTO;
import com.app.entities.TransactionEntity;
import com.app.repository.TransactionRepository;


@Service
public class TrasactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transRepo; 
	@Autowired
	private DailyReportService dailyReportService;
	
	@Override
	public void addTransaction(TransactionDTO transactiondto) {
		TransactionEntity transaction = new TransactionEntity(
				transactiondto.getAmount(),transactiondto.getCurrency(),transactiondto.getTransactionType()
				);
		transRepo.save(transaction);
		dailyReportService.updateDailyReport(transaction);
	}

	@Override
	public List<TransactionEntity> getTransactions() {
		return transRepo.findAll();
	}
	
}
