/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.client.controller;
 
public class Order {

	private String orderId;
	private String productId;
	private String userId;
	private int quantity;
	private OrderStatus orderStatus;
        
        public Order() {}
	
	public Order(String orderId, String productId, String userId, int quantity, OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public String getUserId() {
		return userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	
	
}
