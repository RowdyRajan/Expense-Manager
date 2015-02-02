package com.example.expensemanager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;

import com.google.gson.Gson;

public class ClaimListController {
	private static ArrayList<Claim> claimList = new ArrayList<Claim>();
	private static int claimPicked;
	private static int expensePicked;
	
	
	public static int getExpensePicked() {
		return expensePicked;
	}

	public static void setExpensePicked(int expensePicked) {
		ClaimListController.expensePicked = expensePicked;
	}

	public static int getClaimPicked() {
		return claimPicked;
	}

	public static void setClaimPicked(int claimPicked) {
		ClaimListController.claimPicked = claimPicked;
	}

	public static void setClaimList(ArrayList<Claim> claimList) {
		ClaimListController.claimList = claimList;
	}

	public static void addClaim(Claim claim){
		claimList.add(claim);
	}
	
	public static void removeClaim(Claim claim){
		claimList.remove(claim);
	}

	public static ArrayList<Claim> getClaimList() {
		return claimList;
	}
	public static Claim getClaim(int index){
		return claimList.get(index);
	}
	
	public static Claim getCurrentClaim(){
		if(claimPicked >= 0){
			return claimList.get(claimPicked);
		}
		return null;
	}
	
	public static ExpenseItem getCurrentExpenseItem(){
		if(expensePicked>=0 && claimPicked >= 0){
			return getCurrentClaim().getExpenseList().get(expensePicked);
		}
		return null;
	}
	
	
	
	
	
}
