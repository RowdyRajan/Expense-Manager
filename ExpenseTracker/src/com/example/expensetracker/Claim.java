package com.example.expensetracker;

import java.util.ArrayList;

import android.widget.Toast;

public class Claim {
	private String startDate;
	private String endDate;
	private String description;
	private String name;
	private ArrayList<ExpenseItem> expenseList = new ArrayList<ExpenseItem>();
	private boolean submitted = false;
	
	
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setClaimSubmitted(){
		submitted = true;
	}
	
	public boolean getSubmitted (){
		return submitted;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Claim(String startDate, String endDate, String description,
			String name) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " Date: " + startDate;
	}

	public ArrayList<ExpenseItem> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(ArrayList<ExpenseItem> expenseList) {
		this.expenseList = expenseList;
	}
	
	public void addExpense(ExpenseItem item){
		expenseList.add(item);
	}
	
	public void removeExpense(int id){
		//Toast.makeText(this, expenseList.get((int)id).toString(),Toast.LENGTH_SHORT).show();

		expenseList.remove(id);
		//notifyAll();
	}
	
}
