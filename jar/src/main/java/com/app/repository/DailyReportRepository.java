package com.app.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DailyReportEntity;

public interface DailyReportRepository extends JpaRepository<DailyReportEntity, Long>{
	DailyReportEntity findByDate(LocalDate date);

}
