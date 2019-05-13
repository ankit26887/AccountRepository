package com.wipro.api.bank.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.api.bank.account.model.Account;
import com.wipro.api.bank.account.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	@Query(value = "SELECT * FROM TRANSACTION where ACCOUNT_NO=?1", nativeQuery = true)
	public List<Transaction> findTransasctionByAccount(long accountNo);
}
