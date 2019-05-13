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
import com.wipro.api.bank.account.repository.AccountRepository;

/**
 * @author AN20080177
 *
 */

@Service
public class AccountServiceImpl implements AccountService {
	
	public static Logger logger = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository repo;
	
	
	@Override
	public String openAccount(Account account) {
		Account save = repo.save(account);
		
		if(save!=null) {
		return "Account is saved successfully";
		}else {
			throw new NullPointerException();
		}
	}

	@Override
	public Account getAccountDetails(long accountNo) throws UserNotFoundException  {
	return repo.findById(accountNo).orElseThrow(()->new UserNotFoundException());
	}
	

	@Override
	public Account updateAccountDetails(long accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void deleteAccountByAccountNo(Account account) {
		repo.delete(account);

	}


	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public Account changeAccountType(Account account)  {
		
		return repo.save(account);
	}

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return repo.save(account);
	}
		
	}


