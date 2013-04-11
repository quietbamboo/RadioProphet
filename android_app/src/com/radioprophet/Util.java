/****************************
 *
 * @Date: Apr 11, 2013
 * @Time: 5:05:25 PM
 * @Author: Junxian Huang
 *
 ****************************/
package com.radioprophet;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import android.util.Log;

public class Util {

  public static void logError() {
    Log.e("RadioProphet", "Called commented functions");
  }
  
  public static void makeInstance() {
    ArrayList<Attribute> atts;
    Instances data;
    double[] vals;

    // 1. set up attributes
    atts = new ArrayList<Attribute>();
    // - numeric
    atts.add(new Attribute("att1"));
    atts.add(new Attribute("att2"));

    // 2. create Instances object
    data = new Instances("MyRelation", atts, 0);

    // 3. fill with data
    // first instance
    vals = new double[data.numAttributes()];
    vals[0] = Math.PI;
    vals[1] = Math.E;
    data.add(new DenseInstance(1.0, vals));

    // second instance
    vals = new double[data.numAttributes()];  // important: needs NEW array!
    vals[0] = Math.PI;
    vals[1] = Math.E;
    data.add(new DenseInstance(1.0, vals));

    // 4. output data
    System.out.println(data);
  }

}
