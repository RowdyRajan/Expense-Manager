package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClaimActivity extends Activity {
	private TextView claimInfo;
	private Button expenseAddButton;
	private Button expenseRemoveButton;
	private Button editClaimButton;
	private ListView expenseList;
	private Button submit;
	private Button email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claim);
		claimInfo = (TextView) findViewById(R.id.claimInfo);
		claimInfo.setText(ClaimListController.getClaimList().get(ClaimListController.getClaimPicked()).toString());
		expenseAddButton = (Button) findViewById(R.id.ExpenseAddBtn);
		expenseRemoveButton = (Button) findViewById(R.id.ExpenseRemoveBtn);
		editClaimButton = (Button) findViewById(R.id.ClaimEditBtn);
		submit = (Button) findViewById(R.id.ExpenseSubmitButton);
		email = (Button) findViewById(R.id.ExpenseEmailBtm);
		
		expenseAddButton.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				if(ClaimListController.getCurrentClaim().getSubmitted()){
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT);
					return;
				}
				Intent intent = new Intent(ClaimActivity.this, AddExpenseActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		expenseRemoveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ClaimListController.getCurrentClaim().getSubmitted()){
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT);
					return;
				}
				Intent intent = new Intent(ClaimActivity.this, RemoveExpenseActivity.class);
				startActivity(intent);
			}
		});
		
		editClaimButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ClaimListController.getCurrentClaim().getSubmitted()){
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT);
					return;
				}
				Intent intent = new Intent(ClaimActivity.this, EditClaimInfoActivity.class);
				startActivity(intent);
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		expenseList = (ListView)findViewById(R.id.ExpenseList);
		ArrayAdapter<ExpenseItem> adapter = new ArrayAdapter<ExpenseItem>(this, R.layout.list_item,ClaimListController.getCurrentClaim().getExpenseList());
		expenseList.setAdapter(adapter);
		
		expenseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ClaimListController.setExpensePicked(position);
				Intent intent = new Intent(ClaimActivity.this, ExpenseItemActivity.class);
				startActivity(intent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claim, menu);
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
}
