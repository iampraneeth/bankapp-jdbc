package com.capgemini.bankapp.service;

import java.util.List;

import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountService {
	public double checkBalance(long accountId);

	public double withDraw(long accountId, double amount) throws LowBalanceException;

	public double deposit(long accountId, double amount);

	public boolean deleteAccount(long accountId);

	public double fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException;

	public List<BankAccount> findAllBankAccounts();

	public boolean addNewBankAccount(BankAccount account);

	public List<BankAccount> searchAccount(long accountId);

}
