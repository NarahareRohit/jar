package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.DailyReportEntity;
import com.app.service.DailyReportService;

@RestController
@RequestMapping("report")
public class DailyReportController {
	
	@Autowired
	private DailyReportService reportService;
	
	@GetMapping("/all")
	public List<DailyReportEntity> getReports() {
		return reportService.getReport();
	}

}
