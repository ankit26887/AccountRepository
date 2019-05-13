/**
 * 
 */
package com.wipro.api.bank.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.wipro.api.bank.account.controller.AccountController;
import com.wipro.api.bank.account.exception.UserNotFoundException;
import com.wipro.api.bank.account.model.Account;
import com.wipro.api.bank.account.model.Transaction;
import com.wipro.api.bank.account.repository.AccountRepository;
import com.wipro.api.bank.account.repository.TransactionRepository;

/**
 * @author AN20080177
 *
 */

@Service
public class TransactionServiceImpl implements TransactionService {
	
	public static Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

	@Autowired
	AccountRepository repo;
	
	@Autowired
	TransactionRepository transRepo;
	
	
	
	
	@Override
	public Transaction withdrawBalance(Transaction transaction)  {
		return transRepo.save(transaction);
		
	}

	
	@Override
	public Transaction deposit(Transaction transaction) {
		return transRepo.save(transaction);
		}

	

	@Override
	public List<Transaction> transferAmount(Transaction fromTransaction, Transaction toTransaction)  {
		
		List<Transaction> tranList=new ArrayList<>();
		
		transRepo.save(fromTransaction);
		transRepo.save(toTransaction);
		
		tranList.add(fromTransaction);
		tranList.add(toTransaction);
		
		
	
		return tranList;
		
		
		
	}


	@Override
	public List<Transaction> viewTransactions(long accountNo) throws UserNotFoundException {
		
		return transRepo.findTransasctionByAccount(accountNo);
		// TODO Auto-generated method stub
	}
		
	}


