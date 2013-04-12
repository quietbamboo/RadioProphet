/****************************
*
* @Date: Apr 11, 2013
* @Time: 9:57:38 PM
* @Author: Junxian Huang
*
****************************/
package com.radioprophet;

import android.util.Log;

public class Logger {

  private static String logtag = "RadioProphet";//for use as the tag when logging
  public static void d(String msg) {
    Log.d(logtag, msg);
  }
  
  public static void e(String msg) {
    Log.e(logtag, msg);
  }
}
