package com.caner.mcda5510.service;

import java.util.Collection;

import com.caner.mcda5510.dao.MySQLAccess;
import com.caner.mcda5510.helper.GetCurrentDT;
import com.caner.mcda5510.logging.SimpleLogging;
import com.caner.mcda5510.transaction.Transaction;
import com.caner.mcda5510.helper.ValidationMethods;

public class TrxnWebService {

	public String createTransaction(int trxnID, String Name, String CardNumber, double unitPrice, int qty,
			String expDate) {
		String string_return = "";
		boolean query_db = true;
		boolean create_success = false;

		Transaction new_trxn = new Transaction();
		new_trxn.setId(trxnID);
		if (!ValidationMethods.checkSpecialChar(Name)) {
			new_trxn.setNameOnCard(Name); // Only set if validation is passed
		} else {
			query_db = false;
			string_return = string_return.concat("Name field contains forbidden characters ");
		}
		if (!ValidationMethods.checkSpecialChar(CardNumber)) {
			new_trxn.setCardNumber(CardNumber);
		} else {
			query_db = false;
			string_return = string_return.concat("Credit Card field contains forbidden characters ");
		}

		new_trxn.setUnitPrice(unitPrice);
		new_trxn.setQuantity(qty);
		new_trxn.setTotalPrice(unitPrice * qty);
		if (!ValidationMethods.checkSpecialChar(expDate)) {
			new_trxn.setExpDate(expDate);
		} else {
			query_db = false;
			string_return = string_return.concat("Expiration Date field contains forbidden characters ");
		}
		new_trxn.setCreatedOn(GetCurrentDT.getTime());
		new_trxn.setCreatedBy(System.getProperty("user.name"));

		String[] new_card = ValidationMethods.identifyCard(CardNumber);
		new_trxn.setCreditCard(new_card[1]);

		try {
			MySQLAccess dao = new MySQLAccess();
			if (query_db) { // Only run below if fields filled properly
				create_success = dao.createTransaction(new_trxn);
				if (create_success) {
					string_return = string_return.concat("Insertion Successful");
				} else {
					string_return = string_return
							.concat("Insertion Failed. ID already exist in Database. Please try updateTransaction instead");
	
				}
			}


		} catch (Exception e) {

			SimpleLogging.LOGGER.warning("Error occured on creating transaction / TrxnWebService " + e.toString());
		}
		return string_return;

	}

	public String updateTransaction(int trxnID, String Name, String CardNumber, double unitPrice, int qty,
			String expDate) {

		boolean query_db = true;
		String string_return = "";
		boolean update_success = false;

		Transaction update_trxn = new Transaction();
		update_trxn.setId(trxnID);
		if (!ValidationMethods.checkSpecialChar(Name)) {
			update_trxn.setNameOnCard(Name); // Only set if validation is passed
		} else {
			query_db = false;
			string_return = string_return.concat("Name field contains forbidden characters ");
		}
		if (!ValidationMethods.checkSpecialChar(CardNumber)) {
			update_trxn.setCardNumber(CardNumber);
		} else {
			query_db = false;
			string_return = string_return.concat("Credit Card field contains forbidden characters ");
		}
		update_trxn.setUnitPrice(unitPrice);
		update_trxn.setQuantity(qty);
		update_trxn.setTotalPrice(unitPrice * qty);
		if (!ValidationMethods.checkSpecialChar(expDate)) {
			update_trxn.setExpDate(expDate);
		} else {
			query_db = false;
			string_return = string_return.concat("Expiration Date field contains forbidden characters ");
		}
		update_trxn.setCreatedOn(GetCurrentDT.getTime());
		update_trxn.setCreatedBy(System.getProperty("user.name"));

		String[] update_card = ValidationMethods.identifyCard(CardNumber);
		update_trxn.setCreditCard(update_card[1]);

		try {
			MySQLAccess dao = new MySQLAccess();
			if (query_db) {
				update_success = dao.updateTransaction(update_trxn);
				if (update_success) {
					string_return = string_return.concat("Update Successful");
				} else {
					string_return = string_return.concat(
							"Update Failed. ID does not exist in Database. Please use createTransaction instead");
				}
			}

		} catch (Exception e) {

			SimpleLogging.LOGGER.warning("Error occured on updating transaction / TrxnWebService " + e.toString());
		}

		return string_return;

	}

	public Transaction getTransaction(int trxnID) {

		Transaction get_trxn = new Transaction();
		get_trxn.setId(trxnID);

		try {
			MySQLAccess dao = new MySQLAccess();
			get_trxn = dao.getTransaction(get_trxn);

		} catch (Exception e) {

			SimpleLogging.LOGGER.warning("Error occured on getting transaction / TrxnWebService " + e.toString());
		}

		return get_trxn;
	}

	public boolean removeTransaction(int trxnID) {

		boolean remove_success = false;

		Transaction remove_trxn = new Transaction();
		remove_trxn.setId(trxnID);

		try {
			MySQLAccess dao = new MySQLAccess();
			remove_success = dao.removeTransaction(remove_trxn);

		} catch (Exception e) {

			SimpleLogging.LOGGER.warning("Error occured on removing transaction / TrxnWebService " + e.toString());
		}

		return remove_success;

	}

	public String getAllTransactions() {
		String output_string = "";
		try {
			MySQLAccess dao = new MySQLAccess();
			Collection<Transaction> trxns = dao.getAllTransactions();

			for (Transaction trxn : trxns) {
				output_string = output_string.concat(trxn.toString()) + System.lineSeparator();

			}

		} catch (Exception e) {
			SimpleLogging.LOGGER.warning("Error occured on getting transactions. " + e.toString());

		}
		return output_string;

	}

}
