package com.wipro.api.bank.account.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author AN20080177
 *
 */
@Component
@Entity
@Table(name="ACCOUNT")
public class Account {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNo;
	private String firstName;
	private String lastName;
	private String dob;
	private String accountCreationDate;
	private double balance;
	private String accountType;
	

	public long getAccountNo() {
		return accountNo;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAccountCreationDate() {
		return accountCreationDate;
	}

	

	public void setAccountCreationDate(String accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", accountCreationDate=" + accountCreationDate + ", balance=" + balance + ", accountType="
				+ accountType + "]";
	}
	
	
}
