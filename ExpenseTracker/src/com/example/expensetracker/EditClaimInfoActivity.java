package com.example.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditClaimInfoActivity extends Activity {
	EditText claimName;
	EditText startDate;
	EditText endDate;
	EditText Description;
	Button finishButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		claimName = (EditText)findViewById(R.id.editClaimName);
		startDate = (EditText)findViewById(R.id.editClaimDate);
		endDate = (EditText)findViewById(R.id.editClaimEndDate);
		finishButton = (Button)findViewById(R.id.btnFinish);
		Description= (EditText)findViewById(R.id.editClaimDescription);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		claimName.setText(ClaimListController.getCurrentClaim().getName());
		startDate.setText(ClaimListController.getCurrentClaim().getStartDate());
		endDate.setText(ClaimListController.getCurrentClaim().getEndDate());
		Description.setText(ClaimListController.getCurrentClaim().getDescription());
		
		finishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Claim currentClaim = ClaimListController.getCurrentClaim();
				currentClaim.setName(claimName.getText().toString());
				currentClaim.setStartDate(startDate.getText().toString());
				currentClaim.setEndDate(endDate.getText().toString());
				currentClaim.setDescription(Description.getText().toString());
				
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim_info, menu);
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
