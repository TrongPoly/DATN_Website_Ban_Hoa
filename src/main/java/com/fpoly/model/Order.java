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
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	@NotNull
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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