package com.example.expensetracker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.datatype.Duration;

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

public class AddClaimActivity extends Activity {
	Button finishAdding;
	EditText claimName;
	EditText startDate;
	EditText endDate;
	EditText description;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		
		claimName = (EditText) findViewById(R.id.editClaimName);
		startDate = (EditText) findViewById(R.id.editClaimDate);
		endDate = (EditText) findViewById(R.id.editClaimEndDate);
		description = (EditText) findViewById(R.id.editClaimDescription);
		
		
		finishAdding = (Button)findViewById(R.id.btnFinish);
		finishAdding.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClaimListController.addClaim(new Claim(claimName.getText().toString(), startDate.getText().toString(),
														endDate.getText().toString(), description.getText().toString()));
				saveList();
				
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
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
				Toast.makeText(AddClaimActivity.this, "Saved", Toast.LENGTH_SHORT).show();
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
