import java.sql.Connection;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment2 {

	public static void main(String[] args) {
		MySQLAccess dao = new MySQLAccess();
		SimpleLogging.main(null);
		OutputLogging.main(null);

		boolean running = true;
		while (running) {
			try {
				// Reading from System.in
				Scanner scanner = new Scanner(System.in);

				System.out.println("Please type the corresponding integer to perform an action " + "\n"
						+ "1 - List all entries " + "\n" + "2 - Get entry by ID" + "\n" + "3 - Create new entry" + "\n"
						+ "4 - Update an entry " + "\n" + "5 - Remove an entry" + "\n" + "6 - Shut down");

				int n = scanner.nextInt(); // Scans the next token of the input as an int.
				// once finished

				switch (n) {
				case 1:
					System.out.println("Listing all transactions...  ");
					try {
						Connection connection = dao.setupConnection();
						Collection<Transaction> trxns = dao.getAllTransactions(connection);
						
						int counter = 0 ;
						for (Transaction trxn : trxns) {
							System.out.println(trxn);
							counter++;
						}
						OutputLogging.LOGGER.fine(counter + " transactions listed.");

						if (connection != null) {
							connection.close();
						}

					} catch (Exception e) {
						SimpleLogging.LOGGER.warning("Error occured on getting transactions. " + e.toString());
						
					}

					String choice_reRun = reRun(scanner);
					if (choice_reRun.equals("Y") | choice_reRun.equals("y")) {
						running = false;
						scanner.close();
					}
					break;

				case 2:
					System.out.println("Please enter ID ");

					try {

						int single_id = scanner.nextInt();
						Transaction get_trxn = new Transaction();
						get_trxn.setId(single_id);

						
						try {
							Connection connection = dao.setupConnection();
							System.out.println(dao.getTransaction(connection, get_trxn));
							
							Transaction single_trxn = dao.getTransaction(connection, get_trxn);
							OutputLogging.LOGGER.fine("Requested entry is " + single_id + "." + '\n' + single_trxn.toString() );
							if (connection != null) {
								connection.close();
							}

						} catch (Exception e) {

							SimpleLogging.LOGGER.warning("Error occured on getting transaction. " + e.toString());
						}

					} catch (Exception e) {
						
						System.out.println("Plese Enter Integer for the ID");
						SimpleLogging.LOGGER.fine("Invalid user input for get entry" + e.toString());
					}

					choice_reRun = reRun(scanner);
					if (choice_reRun.equalsIgnoreCase("y")) {
						running = false;
						scanner.close();
					}
					break;

				case 3:
					System.out.println("Initiating new transaction... ");

					Transaction new_trxn = new Transaction();

					System.out.println("Please Enter ID");
					int new_id = scanner.nextInt();
					new_trxn.setId(new_id);

					System.out.println("Please Enter Name on Card");
					String new_name = checkSpecialChar(scanner);
					new_trxn.setNameOnCard(new_name);

					System.out.println("Please Enter Card Number");
					String []new_card = checkCard(scanner);
					new_trxn.setCardNumber(new_card[0]);

					System.out.println("Please Enter UnitPrice");
					double new_unit = scanner.nextDouble();
					new_trxn.setUnitPrice(new_unit);

					System.out.println("Please Enter Quantity");
					int new_qty = scanner.nextInt();
					new_trxn.setQuantity(new_qty);

					new_trxn.setTotalPrice(new_unit * new_qty);

					System.out.println("Please Enter Expiration Date (MM/YYYY)");
					String new_exp = checkDate(scanner);
					new_trxn.setExpDate(new_exp);

					new_trxn.setCreatedOn(GetCurrentDT.getTime());

					new_trxn.setCreatedBy(System.getProperty("user.name"));

					new_trxn.setCreditCard(new_card[1]); 

					try {
						Connection connection = dao.setupConnection();
						boolean create_success = dao.createTransaction(connection, new_trxn);
						
						if (create_success) {
						OutputLogging.LOGGER.fine("New transaction created successfully.");
						}
						else {
						OutputLogging.LOGGER.fine("Failed to create new transaction. ID already exists.");
						}
						
						
						if (connection != null) {
							connection.close();
						}

					} catch (Exception e) {

						SimpleLogging.LOGGER.warning("Error occured on creating transaction " + e.toString());
					}

					choice_reRun = reRun(scanner);
					if (choice_reRun.equalsIgnoreCase("y")) {
						running = false;
						scanner.close();
					}
					break;

				case 4:

					Transaction update_trxn = new Transaction();

					System.out.println("Please Enter ID");
					int update_id = scanner.nextInt();
					update_trxn.setId(update_id);

					System.out.println("Please Enter Name on Card");
					String update_name = checkSpecialChar(scanner);
					update_trxn.setNameOnCard(update_name);

					System.out.println("Please Enter Card Number");
					String []update_card = checkCard(scanner);
					update_trxn.setCardNumber(update_card[0]);

					System.out.println("Please Enter UnitPrice");
					double update_unit = scanner.nextDouble();
					update_trxn.setUnitPrice(update_unit);

					System.out.println("Please Enter Quantity");
					int update_qty = scanner.nextInt();
					update_trxn.setQuantity(update_qty);

					update_trxn.setTotalPrice(update_unit * update_qty);

					System.out.println("Please Enter Expiration Date (MM/YYYY)");
					String update_exp = checkDate(scanner);
					update_trxn.setExpDate(update_exp);

					update_trxn.setCreatedOn(GetCurrentDT.getTime());

					update_trxn.setCreatedBy(System.getProperty("user.name"));

					update_trxn.setCreditCard(update_card[1]); 

					try {
						Connection connection = dao.setupConnection();
						boolean update_success = dao.updateTransaction(connection, update_trxn);
						
						if (update_success) {
						OutputLogging.LOGGER.fine("Updated transaction ID "+ update_id +  " successfully.");
						}
						else {
						OutputLogging.LOGGER.fine("Failed to update transaction "  +update_id +".ID does not exist. ");
						}
						
						if (connection != null) {
							connection.close();
						}

					} catch (Exception e) {

						SimpleLogging.LOGGER.warning("Error occured on updating transaction " + e.toString());
					}

					choice_reRun = reRun(scanner);
					if (choice_reRun.equalsIgnoreCase("y")) {
						running = false;
						scanner.close();
					}
					break;

				case 5:

					Transaction remove_trxn = new Transaction();
					System.out.println("Please Enter ID to be removed");
					int remove_id = scanner.nextInt();
					remove_trxn.setId(remove_id);

					try {
						Connection connection = dao.setupConnection();
						boolean remove_success = dao.removeTransaction(connection, remove_trxn);
						
						if (remove_success) {
						OutputLogging.LOGGER.fine("Removed transaction ID "+ remove_id +  " successfully.");
						}
						else {
						OutputLogging.LOGGER.fine("Failed to remove transaction "  +remove_id +".ID does not exist. ");
						}

						if (connection != null) {
							connection.close();
						}

					} catch (Exception e) {

						SimpleLogging.LOGGER.warning("Error occured on removing transaction " + e.toString());
					}

					choice_reRun = reRun(scanner);
					if (choice_reRun.equalsIgnoreCase("y")) {
						running = false;
						scanner.close();
					}
					break;

				case 6:
					running = false;
					break;

				default:
					System.out.println("Invalid integer input please try again");
					break;

				}

			} catch (Exception ex) {

				System.out.println("Invalid input please try again ");

			}

		}

	}

	public static String reRun(Scanner scanner) {
		System.out.println("\n" + "Done? " + "\n" + "[Y / N]");
		String bool_y_n = scanner.next();

		if (bool_y_n.equalsIgnoreCase("y") | bool_y_n.equalsIgnoreCase("n")) {
		}

		else {
			System.out.println("Please enter Y or N ");
			return reRun(scanner);
		}
		return bool_y_n;
	}

	public static String checkSpecialChar(Scanner scanner) {

		String captured_string = scanner.next();

		Pattern pattern = Pattern.compile(".*[\\Q;:!@#$%^*+?<>\\E].*");
		Matcher matcher = pattern.matcher(captured_string);
		boolean matches = matcher.matches();

		if (matches) {
			System.out.println("Invalid input please try again");
			return checkSpecialChar(scanner);
		}

		return captured_string;
	}

	public static String checkDate(Scanner scanner) {

		String captured_string = scanner.next();
		if (captured_string.length() == 7) {

			try {
				String[] parts = captured_string.split("/");
				String part1 = parts[0];
				String part2 = parts[1];

				Pattern pattern1 = Pattern.compile("^.*?(01|02|03|04|05|06|07|08|09|10|11|12).*$");

				try {
					int year_int = Integer.parseInt(part2);
					if (year_int >= 2016 && year_int <= 2031 && pattern1.matcher(part1).matches()) {
						return part1 + "/" + part2;
					} else {
						System.out.println("Invalid date. Please enter MM/YYYY ");
						return checkDate(scanner);
					}
				} catch (NumberFormatException nf) {
					System.out.println("Invalid date. Please enter MM/YYYY ");
					return checkDate(scanner);
				}

			} catch (ArrayIndexOutOfBoundsException a) {
				System.out.println("Invalid date. Please enter MM/YYYY ");
				return checkDate(scanner);
			}
		} else {
			System.out.println("Invalid date. Please enter MM/YYYY ");
			return checkDate(scanner);
		}

	}

	public static String[] checkCard(Scanner scanner) {

		String[] cardno_cardtype = new String[2];
		String card_no = scanner.next();
		if (card_no.matches("-?\\d+")) { // Check if all digits

			if (card_no.length() == 15 && card_no.substring(0, 2).equals("34")
					|| card_no.substring(0, 2).equals("37")) {
				cardno_cardtype[0] = card_no;
				cardno_cardtype[1] = "American Express";
				return cardno_cardtype;
			} else if (card_no.length() == 16 && card_no.substring(0, 1).equals("4")) {
				cardno_cardtype[0] = card_no;
				cardno_cardtype[1] = "Visa";
				return cardno_cardtype;
			} else if (card_no.length() == 16 && card_no.substring(0, 2).equals("51")
					|| card_no.substring(0, 2).equals("52") || card_no.substring(0, 2).equals("53")
					|| card_no.substring(0, 2).equals("54") || card_no.substring(0, 2).equals("55")) {
				cardno_cardtype[0] = card_no;
				cardno_cardtype[1] = "MasterCard";
				return cardno_cardtype;
			} else {
				System.out.println("Invalid digits. Please try again.");
				return checkCard(scanner);
			}
		} else {
			System.out.println("Card invalid. Please make sure using digits only");
			return checkCard(scanner);
		}

	}

}