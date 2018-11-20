/**
 * TrxnWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.caner.mcda5510.service;

public interface TrxnWebService extends java.rmi.Remote {
    public java.lang.String createTransaction(int trxnID, java.lang.String name, java.lang.String cardNumber, double unitPrice, int qty, java.lang.String expDate) throws java.rmi.RemoteException;
    public java.lang.String updateTransaction(int trxnID, java.lang.String name, java.lang.String cardNumber, double unitPrice, int qty, java.lang.String expDate) throws java.rmi.RemoteException;
    public com.caner.mcda5510.transaction.Transaction getTransaction(int trxnID) throws java.rmi.RemoteException;
    public boolean removeTransaction(int trxnID) throws java.rmi.RemoteException;
    public java.lang.String getAllTransactions() throws java.rmi.RemoteException;
}
