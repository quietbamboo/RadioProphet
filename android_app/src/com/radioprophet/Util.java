/****************************
 *
 * @Date: Apr 11, 2013
 * @Time: 5:05:25 PM
 * @Author: Junxian Huang
 *
 ****************************/
package com.radioprophet;

import weka.core.Instances;

public class Util {
  
  public static Instances makeTestInstance() {
    Instances data = Burst.getDataSet();

    data.add(new Burst(80, 1, true).instance);
    data.add(new Burst(80, 2, true).instance);
    data.add(new Burst(80, 4, false).instance);

    return data;
  }

}
