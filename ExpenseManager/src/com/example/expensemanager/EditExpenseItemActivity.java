package com.example.expensemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditExpenseItemActivity extends Activity {
	private Button edit;
	private EditText name;
	private EditText date;
	private EditText category;
	private EditText amount;
	private EditText currency;
	private EditText description;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		edit = (Button)findViewById(R.id.btnAddExpense);
		edit.setText("Edit");
		
		name = (EditText)findViewById(R.id.EditAddExpenseNameText);
		date = (EditText)findViewById(R.id.EditAddExpenseDate);
		category = (EditText)findViewById(R.id.EditAddExpenseCategory);
		amount = (EditText)findViewById(R.id.EditAddExpenseAmount);
		currency = (EditText)findViewById(R.id.EditAddExpenseCurrency);
		description= (EditText)findViewById(R.id.EditAddExpenseDescription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense_item, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		name.setText(ClaimListController.getCurrentExpenseItem().getName());
		date.setText(ClaimListController.getCurrentExpenseItem().getDate());
		category.setText(ClaimListController.getCurrentExpenseItem().getCategory());
		amount.setText(ClaimListController.getCurrentExpenseItem().getAmountSpent() + "");
		currency.setText(ClaimListController.getCurrentExpenseItem().getCurrency());
		description.setText(ClaimListController.getCurrentExpenseItem().getDescription());
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ExpenseItem currentExpenseItem = ClaimListController.getCurrentExpenseItem();
				currentExpenseItem.setName(name.getText().toString());
				currentExpenseItem.setDate(date.getText().toString());
				currentExpenseItem.setCategory(category.getText().toString());
				currentExpenseItem.setAmountSpent(Float.parseFloat(amount.getText().toString()));
				currentExpenseItem.setCurrency(currency.getText().toString());
				currentExpenseItem.setDescription(description.getText().toString());
				
				finish();
				
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
