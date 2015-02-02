package com.example.expensemanager;

public class ExpenseItem {
	private String name;
	private String date;
	private String category;
	private String description;
	private float amountSpent;
	private String currency;

	public ExpenseItem(String name, String date, String category,
			String description, float amountSpent, String currency) {
		super();
		this.name = name;
		this.date = date;
		this.category = category;
		this.description = description;
		this.amountSpent = amountSpent;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(float amountSpent) {
		this.amountSpent = amountSpent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + date + " " + amountSpent + " " + currency;
	}
	
	

}
