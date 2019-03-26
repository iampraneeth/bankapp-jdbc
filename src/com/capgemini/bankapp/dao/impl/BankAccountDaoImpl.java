package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.util.DbUtil;

public class BankAccountDaoImpl implements BankAccountDao {

	@Override
	public double getBalance(long accountId) {
		double balance = 0;
		String query = "select account_balance from bankaccounts where account_id=" + accountId;
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {
			result.next();
			balance = result.getDouble(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public void updateBalance(long accountId, double newBalance) {
//		String query="update bankaccounts set balance="+newBalance+" where account_id=" + accountId;
		String query = "update bankaccounts set account_balance=? where account_id=?";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			int result = statement.executeUpdate();
			System.out.println("no of rows updated" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		String query = "delete from bankaccounts where account_id=" + accountId;
		int result1;
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			result1 = statement.executeUpdate();
			if (result1 == 1)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account) {
		String query = "INSERT INTO BANKACCOUNTS (CUSTOMER_NAME,ACCOUNT_TYPE,ACCOUNT_BALANCE) VALUES(?,?,?)";
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, account.getAccountHolderName());
			statement.setString(2, account.getAccountType());
			statement.setDouble(3, account.getAccountBalance());
			int result = statement.executeUpdate();
			if (result > 0) {
				System.out.println("New Bank account is created");
				return true;

			} else {
				System.out.println("Something went wrong in creating new account");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		String query = "SELECT * FROM BANKACCOUNTS";

		List<BankAccount> bankAccount = new ArrayList<>();

		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long accountId = resultSet.getLong(1);
				String accountHolderName = resultSet.getString(2);
				String accountType = resultSet.getString(3);
				double accountSalary = resultSet.getDouble(4);
				BankAccount account = new BankAccount(accountId, accountType, accountHolderName, accountSalary);
				bankAccount.add(account);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return bankAccount;
	}

	@Override
	public List<BankAccount> searchAccount(long accountId) {
		String query = "select * from bankaccounts where account_id =" + accountId;
		List<BankAccount> bankAccount = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			long accId = resultSet.getLong(1);
			String accountHolderName = resultSet.getString(2);
			String accountType = resultSet.getString(3);
			double accountSalary = resultSet.getDouble(4);
			BankAccount account = new BankAccount(accId, accountType, accountHolderName, accountSalary);
			bankAccount.add(account);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bankAccount;

	}

}
