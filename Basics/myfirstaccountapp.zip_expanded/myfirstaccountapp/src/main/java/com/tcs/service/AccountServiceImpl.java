package com.tcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.beans.Account;
import com.tcs.dao.AccountRepository;
import com.tcs.exceptions.AccountNotFoundException;
import com.tcs.exceptions.InsufficientBalanceException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository dao;
	
	@Override
	@Transactional
	public Account store(Account account) {
		return dao.save(account);
	}

	@Override
	public Account fetchAccountById(int accountId) throws AccountNotFoundException {
		Account a = dao.findById(accountId).orElse(null);
		if(a==null)
			throw new AccountNotFoundException("Account No. "+accountId+" does not exist");
		
		return a;
	}

	@Override
	@Transactional
	public Account updateAccountPassword(int accountId, String password) throws AccountNotFoundException {
		Account a = fetchAccountById(accountId);
		a.setPassword(password);
		return a;
	}
	
	
	@Override
	@Transactional
	public void transferBalance(int accountId1, int accountId2, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		
		Account a1 = fetchAccountById(accountId1);
		Account a2 = fetchAccountById(accountId2);
		double bal1 = a1.getBalance();
		if(bal1<amount)
			throw new InsufficientBalanceException("Account No. "+accountId1+" has not sufficient balance");
		double bal2 = a2.getBalance();
		a1.setBalance(bal1 - amount);
		a2.setBalance(bal2 + amount);
	}

	
}