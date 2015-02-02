package com.example.expensetracker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RemoveClaimsActivity extends Activity {
	ListView removeClaimList;
	Button deleteClaims;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_claims);

		removeClaimList = (ListView) findViewById(R.id.EditRemoveClaimList);
		deleteClaims = (Button) findViewById(R.id.btnDeleteClaimItem);
		ArrayAdapter<Claim> adapter = new ArrayAdapter<Claim>(this,
				android.R.layout.simple_list_item_multiple_choice,
				ClaimListController.getClaimList());
		removeClaimList.setAdapter(adapter);
		removeClaimList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		deleteClaims.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SparseBooleanArray clicked = removeClaimList.getCheckedItemPositions();
				int len = removeClaimList.getCount();

				for (int i = len; i >= 0; i--) {
					if (clicked.get(i)) {
						ClaimListController.getClaimList().remove(i);
					}
				}

				saveList();
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_claims, menu);
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
