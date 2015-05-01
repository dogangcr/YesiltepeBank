package com.example.yesiltepebank;



import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
 
	Cursor cr;
	Users us;
   UserDataHandler udh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		   udh=new UserDataHandler(this);
		   udh.insertUser(new Users(1,"yesiltepe","bank"));		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void LoginController(View v)
	{
		//get username and password information from edittext
		EditText username= (EditText) findViewById(R.id.editText1);
		EditText password= (EditText) findViewById(R.id.editText2);
		boolean result;
		
		//check user on the db
		 result=udh.CheckUser(username.getText().toString(), password.getText().toString());
		
		
		if(result==true)
		 {
			Toast.makeText(getBaseContext(), "login successful",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
		}
		else
		{
			Toast.makeText(getBaseContext(), "wrong username or password",Toast.LENGTH_LONG).show();
		}
	}

}
