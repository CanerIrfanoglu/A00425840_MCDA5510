package com.caner.mcda5510.dao;

import java.util.Collection;

import com.caner.mcda5510.transaction.Transaction;

public interface Dao {
	
	Collection<Transaction> getAllTransactions();
	
	Transaction getTransaction(Transaction trxn);
	
	boolean createTransaction(Transaction trxn);
	
	boolean updateTransaction(Transaction trxn);
	
	boolean removeTransaction(Transaction trxn);

}
