package com.example.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends Activity {
	private EditText recieveEmail;
	private EditText subject;
	private Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		recieveEmail = (EditText)findViewById(R.id.EditAddYourEmail);
		subject = (EditText)findViewById(R.id.EditEmailSubject);
		send = (Button)findViewById(R.id.btnSendEmail);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, recieveEmail.getText().toString());
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
				emailIntent.putExtra(Intent.EXTRA_TEXT, ClaimListController.getCurrentClaim().toEmail());
				emailIntent.setType("message/rfc822");
				startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_email, menu);
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
