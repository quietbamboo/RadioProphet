package com.radioprophet;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {

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
      Logger.d("onClick() called - start button");              
      Toast.makeText(Main.this, "The Start button was clicked.", Toast.LENGTH_LONG).show();
      Logger.d("onClick() ended - start button");

      //NaiveBayesUpdateable nb = new NaiveBayesUpdateable();      
      try {
        Instances data = Util.makeTestInstance();
        String[] options = new String[1];
        options[0] = "-U";            // unpruned tree
        J48 tree = new J48();         // new instance of tree
        tree.setUnpruned(true);        // using an unpruned J48
        tree.setOptions(options);
        tree.buildClassifier(data);   // build classifier
        
        Burst burst = new Burst(80, 1, true);
        Logger.e("classification result " + tree.classifyInstance(burst.instance));
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
  };

  // Create an anonymous implementation of OnClickListener
  private OnClickListener stopListener = new OnClickListener() {
    public void onClick(View v) {
      Logger.d("onClick() called - stop button"); 
      Toast.makeText(Main.this, "The Stop button was clicked.", Toast.LENGTH_LONG).show();
      Logger.d("onClick() ended - stop button");
    } 
  };


  @Override
  protected void onStart() {//activity is started and visible to the user
    Logger.d("onStart() called");
    super.onStart();  
  }
  @Override
  protected void onResume() {//activity was resumed and is visible again
    Logger.d("onResume() called");
    super.onResume();

  }
  @Override
  protected void onPause() { //device goes to sleep or another activity appears
    Logger.d("onPause() called");//another activity is currently running (or user has pressed Home)
    super.onPause();

  }
  @Override
  protected void onStop() { //the activity is not visible anymore
    Logger.d("onStop() called");
    super.onStop();

  }
  @Override
  protected void onDestroy() {//android has killed this activity
    Logger.d("onDestroy() called");
    super.onDestroy();
  }
}