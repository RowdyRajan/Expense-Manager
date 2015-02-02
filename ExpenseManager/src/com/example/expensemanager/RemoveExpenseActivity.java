package com.example.expensemanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.ClipData.Item;
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

public class RemoveExpenseActivity extends Activity {
	ListView removeExpenseList;
	Button deleteItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_expense);
		removeExpenseList = (ListView)findViewById(R.id.EditRemoveExpenseList);
		deleteItems = (Button)findViewById(R.id.btnDeleteExpenseItem);
		ArrayAdapter<ExpenseItem> adapter = new ArrayAdapter<ExpenseItem>(this, android.R.layout.simple_list_item_multiple_choice,ClaimListController.getCurrentClaim().getExpenseList());
		removeExpenseList.setAdapter(adapter);
		removeExpenseList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		
		deleteItems.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				
				SparseBooleanArray thing =removeExpenseList.getCheckedItemPositions();
				int len = removeExpenseList.getCount();
				
				
				for(int i = len; i >= 0; i--){
					if (thing.get(i)){
						ClaimListController.getCurrentClaim().getExpenseList().remove(i);
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
		getMenuInflater().inflate(R.menu.remove_expense, menu);
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
