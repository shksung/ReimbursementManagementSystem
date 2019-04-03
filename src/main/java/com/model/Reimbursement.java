package com.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Reimbursement {
	private float amount;
	private String description;
	private String submitted; 
	private String resolved;
	private String author;
	private String resolver;
	private String status;
	private String type;
	private int id;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reimbursement(float amount, String description,String author,
			String status, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(float amount,String submitted,String author, String description,
			String status, String type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	} //this constructor doesn't have receipt or resolver

	public Reimbursement(float amount, String description, String submitted,
			String resolved,
			String author, String resolver, String status, String type, int id) {
		super();
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.id= id;
	}

	
	public Reimbursement(float amount, String description) { //testing purposes
		super();
		this.amount = amount;
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmitted() {
		return submitted;
		}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}



	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [amount=" + amount + ", description=" + description + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", author=" + author + ", resolver=" + resolver
				+ ", status=" + status + ", type=" + type + "]";
	}
	
	
	
	
	
	
	
}
