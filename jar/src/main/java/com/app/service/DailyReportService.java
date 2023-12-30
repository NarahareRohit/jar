package com.app.service;

import java.util.List;

import com.app.entities.DailyReportEntity;
import com.app.entities.TransactionEntity;


public interface DailyReportService {
	
	void updateDailyReport(TransactionEntity transaction);
	
	List<DailyReportEntity> getReport();

}
