package com.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.DailyReportEntity;
import com.app.entities.TransactionEntity;
import com.app.repository.DailyReportRepository;

@Service
public class DailyReportServiceImpl implements DailyReportService {
	
	@Autowired
	private DailyReportRepository dailyRepo;
	
	@Override
    public void updateDailyReport(TransactionEntity transaction) {
		
        LocalDate transactionDate = transaction.getPaymentDate();
        DailyReportEntity dailyReport = dailyRepo.findByDate(transactionDate);

        // If a daily report for the date doesn't exist, create one
        if (dailyReport == null) {
            dailyReport = new DailyReportEntity();
        }

        // Update the daily report with the transaction
        dailyReport.update(transaction);

        // Save the daily report
        dailyRepo.save(dailyReport);
    }

	@Override
	public List<DailyReportEntity> getReport() {
		return dailyRepo.findAll();
	}

	
}
