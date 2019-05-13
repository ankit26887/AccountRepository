package com.wipro.api.bank.account.service;

import java.util.List;

import com.wipro.api.bank.account.exception.UserNotFoundException;
import com.wipro.api.bank.account.model.Account;
import com.wipro.api.bank.account.model.Transaction;

/**
 * @author AN20080177
 *
 */
/**
 * @author AN20080177
 *
 */
/**
 * @author AN20080177
 *
 */
/**
 * @author AN20080177
 *
 */
/**
 * @author AN20080177
 *
 */
public interface TransactionService {

	
	public Transaction withdrawBalance(Transaction transaction) throws UserNotFoundException;
	/**
	 * @param account
	 * @return Account
	 */
	
	public Transaction deposit(Transaction transaction);
	/**
	 * 
	 * @param account1
	 * @param account2
	 * @return List<Account>
	 * @throws UserNotFoundException
	 */
	
	public List<Transaction> transferAmount(Transaction fromTransaction,Transaction toTransactiont) throws UserNotFoundException;
	
	/**
	 * 
	 * @param accountNo
	 * @return List<Transaction>
	 * @throws UserNotFoundException
	 */
	public List<Transaction> viewTransactions(long accountNo) throws UserNotFoundException;
}
