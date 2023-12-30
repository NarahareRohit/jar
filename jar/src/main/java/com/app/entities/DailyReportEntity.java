package com.app.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dailyReport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DailyReportEntity extends BaseEntity {

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "totalCreditINR")
	private int totalCreditINR;

	@Column(name = "totalCreditUSD")
	private int totalCreditUSD;

	@Column(name = "totalDebitINR")
	private int totalDebitINR;

	@Column(name = "totalDebitUSD")
	private int totalDebitUSD;

	@Column(name = "totalINR")
	private int totalINR;

	@Column(name = "totalUSD")
	private int totalUSD;

	@OneToMany(mappedBy = "dailyReport", cascade = CascadeType.ALL)
	private List<TransactionEntity> transactions;

	// Constructor to create an instance for the current date
	public DailyReportEntity(LocalDate date) {
		this.date = date;
		this.totalDebitINR = 0;
		this.totalDebitUSD = 0;
		this.totalCreditINR = 0;
		this.totalCreditUSD = 0;
	}

	// Helper method to create a new instance for the current date
	public static DailyReportEntity createForToday() {
		return new DailyReportEntity(LocalDate.now());
	}

    // Helper method to update the daily report based on the transaction
    public void update(TransactionEntity transaction) {
        String currency = transaction.getCurrency();
        int amount = transaction.getAmount();
        String transactionType = transaction.getTransactionType();

        if ("INR".equalsIgnoreCase(currency)) {
            if ("credit".equalsIgnoreCase(transactionType)) {
                totalCreditINR += amount;
            } else if ("debit".equalsIgnoreCase(transactionType)) {
                totalDebitINR += amount;
            }
        } else if ("USD".equalsIgnoreCase(currency)) {
            if ("credit".equalsIgnoreCase(transactionType)) {
                totalCreditUSD += amount;
            } else if ("debit".equalsIgnoreCase(transactionType)) {
                totalDebitUSD += amount;
            }
        }

        this.totalINR = this.totalCreditINR - this.totalDebitINR;
        this.totalUSD = this.totalCreditUSD - this.totalDebitUSD;
    }
	

}
