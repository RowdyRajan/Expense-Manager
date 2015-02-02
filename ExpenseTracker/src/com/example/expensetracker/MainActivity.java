package com.example.expensetracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.Application;
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
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private Button addClaims;
	private Button removeClaims;
	private ListView listClaims;
	public static final String FILENAME = "file.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addClaims = (Button)findViewById(R.id.addClaim);
        removeClaims = (Button)findViewById(R.id.btnRemoveClaims);
        listClaims = (ListView)findViewById(R.id.listClaims);
        
		
	
       
        addClaims.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddClaimActivity.class);
				startActivity(intent);
			}
		});
        
        removeClaims.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, RemoveClaimsActivity.class);
				startActivity(intent);
			}
		});
        
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	ArrayList<Claim> claimList =loadFromFile();
    	
    	ClaimListController.setClaimList(claimList);
    	ArrayAdapter<Claim> adapter = new ArrayAdapter<Claim>(this, R.layout.list_item,ClaimListController.getClaimList());
        listClaims.setAdapter(adapter);
        listClaims.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ClaimListController.setClaimPicked(position);
				Intent intent = new Intent(MainActivity.this, ClaimActivity.class);
				startActivity(intent);
			}
        	
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
  
    
    private ArrayList<Claim> loadFromFile() {
		Gson gson = new Gson();
		ArrayList<Claim> claims = new ArrayList<Claim>();
		
		try {
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			Type typeOfT = new TypeToken<ArrayList<Claim>>(){}.getType();
			claims = gson.fromJson(in, typeOfT);
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claims;
	}
}
