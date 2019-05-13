package com.wipro.api.bank.account.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.api.bank.account.exception.InsufficientFundsException;
import com.wipro.api.bank.account.exception.UserNotFoundException;
import com.wipro.api.bank.account.model.Account;
import com.wipro.api.bank.account.model.Transaction;
import com.wipro.api.bank.account.service.AccountService;
import com.wipro.api.bank.account.service.TransactionService;

/**
 * @author AN20080177
 *
 */


@RestController
@RequestMapping(value="/transaction")
public class TransactionController {
	public static Logger logger = LogManager.getLogger(TransactionController.class);
	@Autowired
	TransactionService tranService;
	
	@Autowired
	AccountService accService;
	
	
	/**
	 * 
	 * @param amount
	 * @param accountNo
	 * @return Account
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value="/deposit/{accountNo}",method=RequestMethod.POST)
	public Transaction deposit(@RequestBody double amount,@PathVariable long accountNo) throws UserNotFoundException {
		
		Account accountDetails = accService.getAccountDetails(accountNo);
		double newAmount=accountDetails.getBalance()+amount;
		accountDetails.setBalance(newAmount);
		
		accService.saveAccount(accountDetails);
		Transaction transactionDetails=new Transaction();
		transactionDetails.setAccountNo(accountNo);
		transactionDetails.setStatus("Credited");
		transactionDetails.setAmount(amount);
		transactionDetails.setTransactionDate(new Date());
		logger.info("User has deposited Rs."+amount+"The update balance in the account is Rs."+newAmount);
		return tranService.deposit(transactionDetails);
		
	}
	
	/**
	 * 
	 * @param amount
	 * @param accountNo
	 * @return Account
	 * @throws UserNotFoundException
	 * @throws InsufficientFundsException
	 */
	@RequestMapping(value="/withdraw/{accountNo}",method=RequestMethod.POST)
	public Transaction withdrwaw(@RequestBody double amount,@PathVariable long accountNo) throws UserNotFoundException,InsufficientFundsException {
		
		Account accountDetails = accService.getAccountDetails(accountNo);
		double newAmount=accountDetails.getBalance()-amount;
		if(newAmount>=0) {
			accountDetails.setBalance(newAmount);
			accService.saveAccount(accountDetails);
			logger.info("User has withdrawn Rs. "+amount+"The update balance in the account is Rs. "+newAmount);
			Transaction transactionDetails=new Transaction();
			transactionDetails.setAccountNo(accountNo);
			transactionDetails.setStatus("Debited");
			transactionDetails.setAmount(amount);
			transactionDetails.setTransactionDate(new Date());
			return tranService.withdrawBalance(transactionDetails);
		}
		
		logger.warn("Insufficient Funds");
		throw new InsufficientFundsException();
	}
	
	/**
	 * 
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @return List<Account>
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value="/transfer/{fromAccount}/{toAccount}",method=RequestMethod.POST)
	public List<Transaction> transferAmount(@PathVariable (name="fromAccount") long fromAccount,@PathVariable (name="toAccount") long toAccount,@RequestBody double amount) throws UserNotFoundException{
		logger.info("Transferring amount"+amount+"from Acccount"+fromAccount+"to Account"+toAccount);
		Account fromAccountDetails = accService.getAccountDetails(fromAccount);
		Account toAccountDetails = accService.getAccountDetails(toAccount);
		
		Transaction fromTransactionDetails=new Transaction();
		Transaction toTransactionDetails=new Transaction();
		
		fromTransactionDetails.setAccountNo(fromAccount);
		fromTransactionDetails.setStatus("Debited");
		fromTransactionDetails.setAmount(amount);
		fromTransactionDetails.setTransactionDate(new Date());
		
		toTransactionDetails.setAccountNo(toAccount);
		toTransactionDetails.setStatus("Credited");
		toTransactionDetails.setAmount(amount);
		toTransactionDetails.setTransactionDate(new Date());
		
		fromAccountDetails.setBalance(fromAccountDetails.getBalance()-amount);
		accService.saveAccount(fromAccountDetails);
		toAccountDetails.setBalance(toAccountDetails.getBalance()+amount);
		accService.saveAccount(toAccountDetails);
		return tranService.transferAmount(fromTransactionDetails, toTransactionDetails);
		
	}
	/**
	 * 
	 * @param accountNo
	 * @return List<Transaction>
	 * @throws UserNotFoundException
	 */
	
	@RequestMapping(value="/get/{accountNo}",method=RequestMethod.GET)
	public List<Transaction> getTransactionList(@PathVariable(name="accountNo") long accountNo) throws UserNotFoundException{
		logger.info("Getting all the transactions of the user");
		return tranService.viewTransactions(accountNo);
	}
}
