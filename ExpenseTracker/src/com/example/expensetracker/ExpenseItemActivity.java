package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ExpenseItemActivity extends Activity {
	private TextView name;
	private TextView date;
	private TextView category;
	private TextView amount;
	private TextView currency;
	private TextView description;
	private Button editButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_item);
		name = (TextView)findViewById(R.id.textExpenseItemName);
		date = (TextView) findViewById(R.id.textExpenseDate); 
		category = (TextView) findViewById(R.id.textExpenseCategory); 
		amount =(TextView) findViewById(R.id.textExpenseAmount); 
		currency = (TextView) findViewById(R.id.textExpenseCurrency); 
		description = (TextView) findViewById(R.id.textExpenseDescription); 
		editButton = (Button) findViewById(R.id.btnEditExpense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_item, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		ExpenseItem item = ClaimListController.getCurrentExpenseItem();
		name.setText(item.getName());
		date.setText(item.getDate());
		category.setText(item.getCategory());
		currency.setText(item.getCurrency());
		amount.setText(item.getAmountSpent() + "");
		description.setText(item.getDescription());
		 
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExpenseItemActivity.this, EditExpenseItemActivity.class);
				startActivity(intent);
			}
		});
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
}
