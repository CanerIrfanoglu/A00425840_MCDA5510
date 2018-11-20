package com.caner.mcda5510.transaction;

public class Transaction {

	private int id;
	private String nameOnCard;
	private String cardNumber;
	private double unitPrice;
	private int quantity;
	private double totalPrice;
	private String expDate;
	private String createdOn;
	private String createdBy;
	private String creditCard;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber + ", unitPrice="
				+ unitPrice + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", expDate=" + expDate
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", creditCard=" + creditCard + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

}
