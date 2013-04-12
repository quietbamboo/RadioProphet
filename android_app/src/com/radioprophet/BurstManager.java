/****************************
 *
 * @Date: Apr 12, 2013
 * @Time: 12:00:39 AM
 * @Author: Junxian Huang
 *
 ****************************/
package com.radioprophet;

import java.util.ArrayList;
import java.util.HashMap;

import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;

public class BurstManager {

  private static ArrayList<Attribute> attributes = null;
  public static HashMap<Integer, Integer> map[];
  private static int index[];
  public static J48 tree;
  public static final int ALPHA = 1000;

  /**
   * 
   * @param store
   * @param si
   * return the running time for training the model
   */
  public static long trainModel(String store[], int si) {
    map = new HashMap[18];
    index = new int[18];
    for (int i = 0 ; i < 18 ; i++) {
      map[i] = new HashMap<Integer, Integer>();
      index[i] = 0;
    }

    int x, key;
    for (int i = 0 ; i < ALPHA ; i++) {
      String[] parts = store[si].split(" ");
      si = (si + 1) % ALPHA;
      for (x = 7 ; x <= 24 ; x++) {
        key = Integer.parseInt(parts[x]);
        if (!map[x - 7].containsKey(key)) {
          map[x - 7].put(key, index[x - 7]++);
        }
      }
    }

    attributes = new ArrayList<Attribute>();
    for (int i = 0 ; i < 18 ; i++) {
      ArrayList<String> attVals = new ArrayList<String>();
      for (int c = 0 ; c < index[i] ; c++) {
        attVals.add("" + c);
      }
      attributes.add(new Attribute("att" + i, attVals));
    }
    //isShorts
    ArrayList<String> attVals = new ArrayList<String>();
    attVals.add("s");
    attVals.add("l");
    attributes.add(new Attribute("is", attVals));
    
    try {
      String[] options = new String[1];
      options[0] = "-U";            // unpruned tree
      tree = new J48();         // new instance of tree
      tree.setUnpruned(true);        // using an unpruned J48
      tree.setOptions(options);
      
      Instances data = BurstManager.getDataSet();
      for (int i = 0 ; i < ALPHA ; i++) {
        data.add(new Burst(store[si], true).instance);
        si = (si + 1) % ALPHA;
      }
      long start = System.currentTimeMillis();
      tree.buildClassifier(data);   // build classifier
      return System.currentTimeMillis() - start;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static ArrayList<Attribute> getAttributes() {
    return attributes;
  }

  public static Instances getDataSet() {
    Instances dataset = new Instances("TrainSet", getAttributes(), 0);
    dataset.setClassIndex(dataset.numAttributes() - 1);
    return dataset;
  }

}
