/****************************
 *
 * @Date: Apr 11, 2013
 * @Time: 10:01:24 PM
 * @Author: Junxian Huang
 *
 ****************************/
package com.radioprophet;

import weka.core.DenseInstance;

public class Burst {

  public DenseInstance instance;
  public short actual;

  public Burst(String info, boolean known) {
    String[] parts = info.split(" ");
    double[] vals = new double[19];
    int key;
    for (int x = 7 ; x <= 24 ; x++) {
      key = Integer.parseInt(parts[x]);
      if (BurstManager.map[x - 7].containsKey(key)) {
        vals[x - 7] = BurstManager.map[x - 7].get(key);
      } else {
        vals[x - 7] = 0; //can't find this key
      }
    }

    vals[18] = 0; //randomly set a value
    
    instance = new DenseInstance(1.0, vals);
    //set a dummy dataset so that classifier knows the structure of the instance
    instance.setDataset(BurstManager.getDataSet());
    
    if (known) {
      if (Double.parseDouble(parts[31]) < 3.0)
        instance.setClassValue(0); //"s" isShort
      else
        instance.setClassValue(1);
    } else
      instance.setClassMissing(); 
    
    if (Double.parseDouble(parts[31]) < 3.0)
      actual = 0;
    else
      actual = 1;
  }

}
