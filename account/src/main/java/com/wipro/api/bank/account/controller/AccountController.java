package com.wipro.api.bank.account.controller;

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
import com.wipro.api.bank.account.service.AccountService;

/**
 * @author AN20080177
 *
 */

@RestController
@RequestMapping(value="/account")
public class AccountController {
	public static Logger logger = LogManager.getLogger(AccountController.class);
	@Autowired
	AccountService accService;
	
	
	
	/**
	 * 
	 * @param account
	 * @return String
	 */
	
	@RequestMapping(value="/open",method=RequestMethod.POST)
	public String openAccount(@RequestBody Account account) {
		
		logger.info("Opening a new account");
		
		return accService.openAccount(account);
		
	}
	
	/**
	 * 
	 * @param accountNo
	 * @return Account
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value="/get/{accountNo}",method=RequestMethod.GET)
	public Account getAccountDetails(@PathVariable(name="accountNo") long accountNo ) throws UserNotFoundException  {
		logger.info("Fetching the individual account details");
		Account accountDetails = accService.getAccountDetails(accountNo);
		
		return accountDetails;
		
	}
	
	/**
	 * 
	 * @return List<Account>
	 */
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public List<Account> getAllAccountDetails() {
		logger.info("Fetching all the accounts");
		List<Account> accountDetails = accService.getAllAccounts();
		
		if(accountDetails!=null)
		return accountDetails;
		
		return accountDetails;
	}
	
	
	
	/**
	 * 
	 * @param accountNo
	 * @return String
	 * @throws UserNotFoundException
	 */
	
	@RequestMapping(value="/delete/{accountNo}",method=RequestMethod.DELETE)
	public String deleteAccountByAccountNo(@PathVariable (name="accountNo") long accountNo) throws UserNotFoundException {
		logger.info("Deleting the account");
		Account accountDetails = accService.getAccountDetails(accountNo);
		
		accService.deleteAccountByAccountNo(accountDetails);
		
		return "Account has been deleted successfully";
		
	}
		
	/**
	 * 
	 * @param accountType
	 * @param accountNo
	 * @return Account
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value="/change/accountType/{accountNo}",method=RequestMethod.PATCH)
	public Account changeAccountType(@RequestBody String accountType,@PathVariable(name="accountNo") long accountNo) throws UserNotFoundException {
		logger.info("Changing the account type");
		Account accountDetails = accService.getAccountDetails(accountNo);
		
		if(!accountType.equals(accountDetails.getAccountType())) {
			accountDetails.setAccountType(accountType);
			accService.changeAccountType(accountDetails);
		}
	return accountDetails;
	}
		

}
