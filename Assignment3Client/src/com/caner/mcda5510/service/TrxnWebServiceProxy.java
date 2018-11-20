package com.caner.mcda5510.service;

public class TrxnWebServiceProxy implements com.caner.mcda5510.service.TrxnWebService {
  private String _endpoint = null;
  private com.caner.mcda5510.service.TrxnWebService trxnWebService = null;
  
  public TrxnWebServiceProxy() {
    _initTrxnWebServiceProxy();
  }
  
  public TrxnWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initTrxnWebServiceProxy();
  }
  
  private void _initTrxnWebServiceProxy() {
    try {
      trxnWebService = (new com.caner.mcda5510.service.TrxnWebServiceServiceLocator()).getTrxnWebService();
      if (trxnWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)trxnWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)trxnWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (trxnWebService != null)
      ((javax.xml.rpc.Stub)trxnWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.caner.mcda5510.service.TrxnWebService getTrxnWebService() {
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService;
  }
  
  public java.lang.String createTransaction(int trxnID, java.lang.String name, java.lang.String cardNumber, double unitPrice, int qty, java.lang.String expDate) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.createTransaction(trxnID, name, cardNumber, unitPrice, qty, expDate);
  }
  
  public java.lang.String updateTransaction(int trxnID, java.lang.String name, java.lang.String cardNumber, double unitPrice, int qty, java.lang.String expDate) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.updateTransaction(trxnID, name, cardNumber, unitPrice, qty, expDate);
  }
  
  public com.caner.mcda5510.transaction.Transaction getTransaction(int trxnID) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.getTransaction(trxnID);
  }
  
  public boolean removeTransaction(int trxnID) throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.removeTransaction(trxnID);
  }
  
  public java.lang.String getAllTransactions() throws java.rmi.RemoteException{
    if (trxnWebService == null)
      _initTrxnWebServiceProxy();
    return trxnWebService.getAllTransactions();
  }
  
  
}