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
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class User extends BaseEntity {
	
	@Column(name = "name")
	private String Name;
			
	@Column(name = "date")
	private LocalDate date;
							
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TransactionEntity> transactions;
	
	public User() {
		this.Name=null;
		this.date = LocalDate.now();
	}

	
}	
