package com.wipro.api.bank.account.service;

import java.util.List;

import com.wipro.api.bank.account.exception.UserNotFoundException;
import com.wipro.api.bank.account.model.Account;

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
public interface AccountService {

	/**
	 * @param account
	 * @return String
	 */
	public String openAccount(Account account);
	/**
	 * @param account
	 * @return Account
	 * @throws UserNotFoundException
	 */
	public Account getAccountDetails(long accountNo) throws UserNotFoundException ;
	/**
	 * @param account
	 * @return List<Account>
	 */
	
	public List<Account> getAllAccounts();
	/**
	 * @param accountNo
	 * @return Account
	 */
	
	public Account updateAccountDetails(long accountNo);
	/**
	 * @param account
	 * @return Account
	 * @throws UserNotFoundException
	 */
	
	
	public void deleteAccountByAccountNo(Account account);
	
	
	/**
	 * @param account1
	 * @return String
	 * @throws UserNotFoundException 
	 */
	
	public Account changeAccountType(Account account);
	
	public Account saveAccount(Account account);
	
}
