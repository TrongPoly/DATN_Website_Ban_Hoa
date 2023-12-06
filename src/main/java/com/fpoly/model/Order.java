package com.fpoly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "billing_full_name", nullable = false)
	private String billingFullName;
	
	@NotNull
	@Column(name = "billing_phone_number", nullable = false)
	private String billingPhoneNumber;
	@NotNull
	@Column(name = "shipping_full_name", nullable = false)
	private String shippingFullName;
	
	@NotNull
	@Column(name = "shipping_phone_number", nullable = false)
	private String shippingPhoneNumber;

	@ManyToOne
	@JoinColumn(name = "email", nullable = false)
	private Account email;
	
	@NotNull
	@Column(name = "note", nullable = false)
	private String note;
	@NotNull
	@Column(name = "order_date", nullable = false)
	private Instant orderDate;

	@Column(name = "pick_up_date")
	private Instant pickUpDate;

	@ManyToOne
	@JoinColumn(name = "status")
	private OrderStatus status;

	@NotNull
	@Column(name = "method_payment")
	private int methodPayment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	public Instant getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Instant pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	

	public String getBillingFullName() {
		return billingFullName;
	}

	public void setBillingFullName(String billingFullName) {
		this.billingFullName = billingFullName;
	}

	public String getBillingPhoneNumber() {
		return billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public String getShippingFullName() {
		return shippingFullName;
	}

	public void setShippingFullName(String shippingFullName) {
		this.shippingFullName = shippingFullName;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public int getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(int methodPayment) {
		this.methodPayment = methodPayment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Account getEmail() {
		return email;
	}

	public void setEmail(Account email) {
		this.email = email;
	}

}