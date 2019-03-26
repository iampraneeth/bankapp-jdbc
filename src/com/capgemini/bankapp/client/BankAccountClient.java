package com.capgemini.bankapp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

public class BankAccountClient {
	public static void main(String[] args) {
		List<BankAccount> accounts;
		long accountId;
		int choice;
		String accountHolderName;
		String accountType;
		double accountBalance;
		BankAccountService bankService = new BankAccountServiceImpl();
		BankAccount account;
		double bal = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				System.out.println("1. Add New BankAccount\n2. Withdraw\n3. Deposit\n4. Fund Transfer");
				System.out.println("5. Delete BankAccount\n6. Display All BankAccount Details");
				System.out.println("7. Search BankAccount\n8. Check Balance\n9 Exit\n");

				System.out.print("Please enter your choice = ");
				choice = Integer.parseInt(reader.readLine());

				switch (choice) {

				case 1:
					System.out.println("Enter account holder name: ");
					accountHolderName = reader.readLine();
					System.out.println("Enter account type: ");
					accountType = reader.readLine();
					System.out.println("Enter account balance: ");
					accountBalance = Double.parseDouble(reader.readLine());
					account = new BankAccount(accountHolderName, accountType, accountBalance);
					if (bankService.addNewBankAccount(account)) {
						System.out.println("Account created succesfully");
					} else {
						System.out.println("failed to create ");
					}
					break;
				case 2:
					System.out.println("Enter account id");
					accountId = Integer.parseInt(reader.readLine());
					System.out.println("Enter the amount to be withdraw");
					accountBalance = Double.parseDouble(reader.readLine());
					bal = bankService.withDraw(accountId, accountBalance);
					System.out.println(bal);
					break;
				case 3:
					System.out.println("Enter account id");
					accountId = Integer.parseInt(reader.readLine());
					System.out.println("Enter the amount to be deposit");
					accountBalance = Double.parseDouble(reader.readLine());
					bal = bankService.deposit(accountId, accountBalance);
					System.out.println(bal);
					break;
				case 4:
					System.out.println("Enter From account id");
					accountId = Integer.parseInt(reader.readLine());
					System.out.println("Enter to account id");
					long accountId2 = Integer.parseInt(reader.readLine());
					System.out.println("Enter the amount to be transfer");
					accountBalance = Double.parseDouble(reader.readLine());
					bal = bankService.fundTransfer(accountId, accountId2, accountBalance);
					System.out.println(bal);
					break;
				case 5:
					System.out.println("Enter the account id of the account for deletion");
					accountId = Integer.parseInt(reader.readLine());
					bankService.deleteAccount(accountId);
					System.out.println("your account is deleted");
					break;

				case 6:

					accounts = bankService.findAllBankAccounts();
					for (BankAccount bankAccount : accounts) {
						System.out.println(bankAccount);
					}
					break;
				case 7:
					System.out.println("Enter the account id for search");
					accountId = Integer.parseInt(reader.readLine());
					System.out.println(bankService.searchAccount(accountId));
					break;

				case 8:
					System.out.println("Enter account id");
					accountId = Integer.parseInt(reader.readLine());
					bal = bankService.checkBalance(accountId);
					System.out.println(bal);
					break;
				
				case 9:
					System.out.println("Thanks for banking with us.");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
