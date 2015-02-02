package com.example.expensetracker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends Activity {
	EditText ExpenseName;
	EditText ExpenseDescription;
	EditText ExpenseDate;
	EditText ExpenseCategory;
	EditText ExpenseAmount;
	EditText ExpenseCurrency;
	Button btnAddExpense;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		ExpenseName = (EditText)findViewById(R.id.EditAddExpenseNameText);
		ExpenseDescription = (EditText)findViewById(R.id.EditAddExpenseDescription);
		ExpenseDate= (EditText)findViewById(R.id.EditAddExpenseDate);
		ExpenseCategory= (EditText)findViewById(R.id.EditAddExpenseCategory);
		ExpenseAmount = (EditText)findViewById(R.id.EditAddExpenseAmount);
		ExpenseCurrency = (EditText)findViewById(R.id.EditAddExpenseCurrency);
		btnAddExpense = (Button)findViewById(R.id.btnAddExpense);
		
		btnAddExpense.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				ExpenseItem item = new ExpenseItem(ExpenseName.getText().toString(), 
													ExpenseDate.getText().toString(), 
													ExpenseCategory.getText().toString(),
													ExpenseDescription.getText().toString(),
													Float.parseFloat(ExpenseAmount.getText().toString()),
													ExpenseCurrency.getText().toString());
				ClaimListController.getClaim(ClaimListController.getClaimPicked()).addExpense(item);
				
				saveList();
				finish();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_expense, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void saveList(){
		Gson gson = new Gson();
		
		try {
			FileOutputStream fos = openFileOutput(MainActivity.FILENAME, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(ClaimListController.getClaimList(), osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
