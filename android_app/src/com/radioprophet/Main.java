package com.radioprophet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {

	private static String logtag = "TwoButtonApp";//for use as the tag when logging 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button buttonStart = (Button)findViewById(R.id.buttonStart);        
		buttonStart.setOnClickListener(startListener); // Register the onClick listener with the implementation above

		Button buttonStop = (Button)findViewById(R.id.buttonStop);        
		buttonStop.setOnClickListener(stopListener); // Register the onClick listener with the implementation above
	}

	//Create an anonymous implementation of OnClickListener
	private OnClickListener startListener = new OnClickListener() {
		public void onClick(View v) {
			Log.d(logtag,"onClick() called - start button");              
			Toast.makeText(Main.this, "The Start button was clicked.", Toast.LENGTH_LONG).show();
			Log.d(logtag,"onClick() ended - start button");
		}
	};

	// Create an anonymous implementation of OnClickListener
	private OnClickListener stopListener = new OnClickListener() {
		public void onClick(View v) {
			Log.d(logtag,"onClick() called - stop button"); 
			Toast.makeText(Main.this, "The Stop button was clicked.", Toast.LENGTH_LONG).show();
			Log.d(logtag,"onClick() ended - stop button");
		} 
	};


	@Override
	protected void onStart() {//activity is started and visible to the user
		Log.d(logtag,"onStart() called");
		super.onStart();  
	}
	@Override
	protected void onResume() {//activity was resumed and is visible again
		Log.d(logtag,"onResume() called");
		super.onResume();

	}
	@Override
	protected void onPause() { //device goes to sleep or another activity appears
		Log.d(logtag,"onPause() called");//another activity is currently running (or user has pressed Home)
		super.onPause();

	}
	@Override
	protected void onStop() { //the activity is not visible anymore
		Log.d(logtag,"onStop() called");
		super.onStop();

	}
	@Override
	protected void onDestroy() {//android has killed this activity
		Log.d(logtag,"onDestroy() called");
		super.onDestroy();
	}
}