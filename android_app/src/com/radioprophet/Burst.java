/****************************
*
* @Date: Apr 11, 2013
* @Time: 10:01:24 PM
* @Author: Junxian Huang
*
****************************/
package com.radioprophet;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class Burst {

  public DenseInstance instance;
  
  private static ArrayList<Attribute> attributes = null;
  
  public static ArrayList<Attribute>  getAttributes() {
    if (attributes != null)
      return attributes;
    ArrayList<Attribute> atts = new ArrayList<Attribute>();
    ArrayList<String> attVals;
    
    attVals = new ArrayList<String>();
    attVals.add("80");
    attVals.add("443");
    attVals.add("8080");
    attVals.add("53");
    atts.add(new Attribute("port1", attVals));
   
    attVals = new ArrayList<String>();
    attVals.add("1");
    attVals.add("2");
    attVals.add("3");
    attVals.add("4");
    atts.add(new Attribute("flags1", attVals));
    
    attVals = new ArrayList<String>();
    attVals.add("0");
    attVals.add("1");
    atts.add(new Attribute("isShort", attVals));
    attributes = atts;
    return attributes;
  }
  
  public static Instances getDataSet() {
    Instances dataset = new Instances("TrainSet", Burst.getAttributes(), 0);
    dataset.setClassIndex(dataset.numAttributes() - 1);
    return dataset;
  }
  
  public Burst(int port, int flags, boolean isShort) {
    double[] vals = new double[3];
    vals[0] = Burst.getAttributes().get(0).indexOfValue("" + port);
    vals[1] = Burst.getAttributes().get(1).indexOfValue("" + flags);
    vals[2] = 0;
    instance = new DenseInstance(1.0, vals);

    //set a dummy dataset so that classifier knows the structure of the instance
    instance.setDataset(Burst.getDataSet());        
    
    if (isShort)
      instance.setClassValue(1);
    else
      instance.setClassValue(0);
  }

}
