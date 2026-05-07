package com.bankAccount;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Spring boot application runner to start local server
 */
@SpringBootApplication
public class BankAccountApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountApplication.class);
	public static void main(String[] args) {
		try{
			ConfigurableApplicationContext appContext = SpringApplication.run(BankAccountApplication.class, args);
			System.out.println("Welcome to Bank Account Application.");
			System.out.println("The rest controller is listening on port 8080");
			System.out.println("You want to launch a local simulation with user1 account?");
			System.out.println("1.\tYes");
			System.out.println("Other.\tNo");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			if(choice == 1){
				presentationLayer(appContext, scanner);
			}
		}
		catch (Exception e){
			LOGGER.error("Error while starting the application");
		}
	}

	/**
	 * example of a flow for presentation layers call
	 */
	public static void presentationLayer(ConfigurableApplicationContext appContext, Scanner scanner){
		try {
			BankAccountPorts dispatcher = appContext.getBean(BankAccountPorts.class);
			String userId = "1";
			int choice = 0;
			BigDecimal amount;

			do {
				System.out.flush();
				System.out.println("Choice one operation to do: ");
				System.out.println("1.\tDeposit money");
				System.out.println("2.\tGet money");
				System.out.println("3.\tGet balance");
				System.out.println("4.\tGet transactions");
				System.out.println("5.\tEnd without closing rest controller");
				System.out.println("Other.\tEnd and close rest controller");
				choice = scanner.nextInt();

				switch (choice) {
					case 1:
						amount = getAmount(scanner);
						if (dispatcher.deposit(userId, amount)) {
							System.out.println("Deposit done");
						} else {
							System.out.println("Error while deposit. Please check the logs for more info");
						}
						break;
					case 2:
						amount = getAmount(scanner);
						if (dispatcher.getMoney(userId, amount)) {
							System.out.println("Money taken done");
						} else {
							System.out.println("Error while get money. Please check the logs for more info");
						}
						break;
					case 3:
						BankAccount BankAccount = dispatcher.getBankAccountBalance(userId);
						if (BankAccount != null) {
							System.out.println(BankAccount);
						} else {
							System.out.println("Error while getting bank account. Please check the logs for more info");
						}
						break;
					case 4:
						List<Transaction> transactions = dispatcher.getTransactions(userId);
						if (transactions != null) {
							if (transactions.size() == 0) {
								System.out.println("No transactions made for now");
							} else {
								System.out.println("List of transactions: ");
								for (Transaction transaction : transactions) {
									System.out.println(transaction);
								}
							}
						} else {
							System.out.println("Error while getting transactions. Please check the logs for more info");
						}
						break;
					case 5:
						System.out.println("Goodbye");
						break;
					default:
						System.out.println("Goodbye");
						appContext.close();
						break;
				}
			} while (choice > 0 && choice < 5);
		}
		catch (Exception e){
			LOGGER.error("Error while running presentation layer");
			appContext.close();
		}
	}

	private static BigDecimal getAmount(Scanner scanner){
		System.out.print("Insert amount: ");
		return scanner.nextBigDecimal();
	}
}
