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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

}