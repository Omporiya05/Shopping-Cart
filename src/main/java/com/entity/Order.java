package com.entity;

public class Order extends Product 
{
	private int orderid;
	private int uid;
	private int quantity;
	private String date;
	
	public Order()
	{
		super();
	}

	public Order(int orderid, int uid, int quantity, String date) {
		super();
		this.orderid = orderid;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
}
