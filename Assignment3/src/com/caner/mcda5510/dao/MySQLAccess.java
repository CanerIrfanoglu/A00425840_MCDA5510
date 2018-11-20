package com.caner.mcda5510.dao;

/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
 **/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.caner.mcda5510.connect.MySQLConnect;
import com.caner.mcda5510.logging.SimpleLogging;
import com.caner.mcda5510.transaction.Transaction;

import java.sql.PreparedStatement;

public class MySQLAccess implements Dao {

	Connection connection = MySQLConnect.setupConnection();

	@Override
	public Collection<Transaction> getAllTransactions() {

		SimpleLogging.main(null);
		Statement statement = null;
		ResultSet resultSet = null;
		Collection<Transaction> results = null;
		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transactoins.transaction");
			results = createTrxns(resultSet);

			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			SimpleLogging.LOGGER.fine("All transactions returned successfully.");

		} catch (SQLException e) {

			SimpleLogging.LOGGER.warning("Error occured on querying while getting transactions " + e.toString());

		} finally {
			statement = null;
			resultSet = null;
		}

		return results;
	}

	private Collection<Transaction> createTrxns(ResultSet resultSet) {
		Collection<Transaction> results = new ArrayList<Transaction>();

		try {
			// ResultSet is initially before the first data set
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				Transaction trxn = new Transaction();
				trxn.setId(resultSet.getInt("ID"));
				trxn.setNameOnCard(resultSet.getString("NameOnCard"));
				trxn.setCardNumber(resultSet.getString("CardNumber"));
				trxn.setUnitPrice(resultSet.getDouble("UnitPrice"));
				trxn.setQuantity(resultSet.getInt("Quantity"));
				trxn.setTotalPrice(resultSet.getDouble("TotalPrice"));
				trxn.setExpDate(resultSet.getString("ExpDate"));
				trxn.setCreatedOn(resultSet.getString("CreatedOn"));
				trxn.setCreatedBy(resultSet.getString("CreatedBy"));
				trxn.setCreditCard(resultSet.getString("CreditCard"));

				results.add(trxn);
			}
		} catch (SQLException se) {

			SimpleLogging.LOGGER.warning("Error occured on extracting data from transaction object " + se.toString());
		}

		return results;
	}

	@Override
	public Transaction getTransaction(Transaction get_trxn) {

		SimpleLogging.main(null);
		Connection connection = MySQLConnect.setupConnection();
		Transaction trxn = new Transaction();
		PreparedStatement preparedStatement = null;
		ResultSet getResult = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM transactoins.transaction WHERE ID =?");

			preparedStatement.setInt(1, get_trxn.getId());

			getResult = preparedStatement.executeQuery();

			while (getResult.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);

				trxn.setId(getResult.getInt("ID"));
				trxn.setNameOnCard(getResult.getString("NameOnCard"));
				trxn.setCardNumber(getResult.getString("CardNumber"));
				trxn.setUnitPrice(getResult.getDouble("UnitPrice"));
				trxn.setQuantity(getResult.getInt("Quantity"));
				trxn.setTotalPrice(getResult.getDouble("TotalPrice"));
				trxn.setExpDate(getResult.getString("ExpDate"));
				trxn.setCreatedOn(getResult.getString("CreatedOn"));
				trxn.setCreatedBy(getResult.getString("CreatedBy"));
				trxn.setCreditCard(getResult.getString("CreditCard"));

				SimpleLogging.LOGGER.fine("Transaction " + getResult.getInt("ID") + " retrieved successfully");

			}
		} catch (SQLException e) {

			SimpleLogging.LOGGER.warning("Error occured on getTransaction " + e.toString());
		}

		return (trxn);
	}

	@Override
	public boolean createTransaction(Transaction new_trxn) {

		SimpleLogging.main(null);
		boolean create_status = false;
		Connection connection = MySQLConnect.setupConnection();

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.prepareStatement("insert into transactoins.transaction values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
			preparedStatement.setInt(1, new_trxn.getId());
			preparedStatement.setString(2, new_trxn.getNameOnCard());
			preparedStatement.setString(3, new_trxn.getCardNumber());
			preparedStatement.setDouble(4, new_trxn.getUnitPrice());
			preparedStatement.setInt(5, new_trxn.getQuantity());
			preparedStatement.setDouble(6, new_trxn.getTotalPrice());
			preparedStatement.setString(7, new_trxn.getExpDate());
			preparedStatement.setString(8, new_trxn.getCreatedOn());
			preparedStatement.setString(9, new_trxn.getCreatedBy());
			preparedStatement.setString(10, new_trxn.getCreditCard());
			preparedStatement.executeUpdate();

			SimpleLogging.LOGGER.fine("New transaction created successfully.");

			create_status = true;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("ID already exists. Please try update instead");
			SimpleLogging.LOGGER.warning("createTransaction ID already exists in the DB" + e.toString());

		} catch (SQLException se) {
			SimpleLogging.LOGGER.warning("Error occured on createTransaction " + se.toString());

		}

		return create_status;
	}

	@Override
	public boolean updateTransaction(Transaction update_trxn) {

		SimpleLogging.main(null);
		boolean update_status = false;
		Connection connection = MySQLConnect.setupConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"update transactoins.transaction set NameOnCard =?, CardNumber =?, UnitPrice =?, Quantity =?,"
							+ "TotalPrice =?, ExpDate =?, CreatedOn =?, CreatedBy =?, CreditCard =?  where ID =?");

			preparedStatement.setString(1, update_trxn.getNameOnCard());
			preparedStatement.setString(2, update_trxn.getCardNumber());
			preparedStatement.setDouble(3, update_trxn.getUnitPrice());
			preparedStatement.setInt(4, update_trxn.getQuantity());
			preparedStatement.setDouble(5, update_trxn.getTotalPrice());
			preparedStatement.setString(6, update_trxn.getExpDate());
			preparedStatement.setString(7, update_trxn.getCreatedOn());
			preparedStatement.setString(8, update_trxn.getCreatedBy());
			preparedStatement.setString(9, update_trxn.getCreditCard());
			preparedStatement.setInt(10, update_trxn.getId());
			int rows_affected = preparedStatement.executeUpdate();
			if (rows_affected == 1) {
				SimpleLogging.LOGGER.fine("Update successful!");
				update_status = true;
			} else {
				System.out.println("ID not found. Please use create entry or provide a valid ID ");
				SimpleLogging.LOGGER.warning("ID not found in DB");

			}

		} catch (SQLException se) {
			SimpleLogging.LOGGER.warning("Error occured on updateTransaction " + se.toString());

		}

		return update_status;

	}

	@Override
	public boolean removeTransaction(Transaction remove_trxn) {

		SimpleLogging.main(null);
		boolean remove_status = false;

		Connection connection = MySQLConnect.setupConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM transactoins.transaction WHERE ID =?");

			preparedStatement.setInt(1, remove_trxn.getId());

			int rows_affected = preparedStatement.executeUpdate();
			if (rows_affected == 1) {
				SimpleLogging.LOGGER.fine("ID no " + remove_trxn.getId() + " successfully removed");
				remove_status = true;
			} else {
				SimpleLogging.LOGGER.warning("Remove ID not found in DB" + remove_trxn.getId());

			}

		} catch (SQLException se) {

			SimpleLogging.LOGGER.warning("Error occured on removeTransaction " + se.toString());

		}

		return remove_status;

	}
	
//	try {
//	connection.close();
//} catch (SQLException e2) {
//
//	SimpleLogging.LOGGER.warning("Error occured on closing connection " + e2.toString());
//}

}
