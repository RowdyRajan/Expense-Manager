package com.example.expensetracker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

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
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(ClaimActivity.this, EditClaimInfoActivity.class);
				startActivity(intent);
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ClaimListController.getCurrentClaim().getSubmitted()){
					Toast.makeText(ClaimActivity.this, "Claim submitted. No further changes allowed.", Toast.LENGTH_SHORT).show();
					return;
				}
				AlertDialog.Builder builder = new Builder(ClaimActivity.this);
				builder.setTitle("Submit");
				builder.setMessage("Do you wish to submit your claim? Warning no further changes can be made to this claim once submitted");
				builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ClaimListController.getCurrentClaim().setClaimSubmitted();
						saveList();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		
		email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ClaimActivity.this, SendEmailActivity.class);
				startActivity(intent);
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
