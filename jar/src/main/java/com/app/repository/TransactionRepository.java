package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.TransactionEntity;
import com.app.entities.User;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


}
