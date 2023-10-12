package com.fpoly.DTO;

import java.io.Serializable;

public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int quant;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

}
