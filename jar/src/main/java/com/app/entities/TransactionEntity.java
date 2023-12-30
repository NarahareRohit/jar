package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class TransactionEntity extends BaseEntity {

    @Column(name = "Amount")
    private int amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "transactionType")
    private String transactionType;  // Assuming this field represents whether it's credit or debit

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "daily_report_id")
    private DailyReportEntity dailyReport;

    public TransactionEntity() {
    	this.amount = 0;
    	this.paymentDate = LocalDate.now();
    	this.currency = "INR";
    	this.transactionType = "debit";
    	this.user = null;
    }
    // Constructor
    public TransactionEntity(int amount, String currency, String transactionType) {
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = LocalDate.now();
        this.transactionType = transactionType;

        // Update the associated DailyReportEntity for the current day
        updateDailyReport();
    }

    // Helper method to update the associated DailyReportEntity for the current day
    private void updateDailyReport() {
        if (dailyReport == null) {
            dailyReport = new DailyReportEntity(paymentDate);
        }

        // Update the totalCredit or totalDebit based on the transaction amount and type
        if ("INR".equalsIgnoreCase(currency) && "credit".equalsIgnoreCase(transactionType)) {
            dailyReport.setTotalCreditINR(dailyReport.getTotalCreditINR() + amount);
            this.transactionType = "credit";
        } else if ("INR".equalsIgnoreCase(currency) && "debit".equalsIgnoreCase(transactionType)) {
            dailyReport.setTotalDebitINR(dailyReport.getTotalDebitINR() + amount);
            this.transactionType = "debit";
        } else if ("USD".equalsIgnoreCase(currency) && "credit".equalsIgnoreCase(transactionType)) {
            dailyReport.setTotalCreditUSD(dailyReport.getTotalCreditUSD() + amount);
            this.transactionType = "credit";
        } else if ("USD".equalsIgnoreCase(currency) && "debit".equalsIgnoreCase(transactionType)) {
            dailyReport.setTotalDebitUSD(dailyReport.getTotalDebitUSD() + amount);
            this.transactionType = "debit";
        }

        // Update the totalINR and totalUSD
        dailyReport.setTotalINR(dailyReport.getTotalCreditINR() - dailyReport.getTotalDebitINR());
        dailyReport.setTotalUSD(dailyReport.getTotalCreditUSD() - dailyReport.getTotalDebitUSD());
//        dailyReport.getTransactions().add(this);
    }

}
