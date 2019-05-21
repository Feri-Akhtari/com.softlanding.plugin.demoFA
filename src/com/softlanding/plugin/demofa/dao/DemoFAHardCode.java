/*
* © 2010-2019 COPYRIGHT UNICOM SYSTEMS, INC.
*
* ALL RIGHTS RESERVED
*
* THE INFORMATION CONTAINED HEREIN CONSTITUTES AN UNPUBLISHED
* WORK OF UNICOM SYSTEMS, INC. ALL RIGHTS RESERVED.
* NO MATERIAL FROM THIS WORK MAY BE REPRINTED,
* COPIED, OR EXTRACTED WITHOUT WRITTEN PERMISSION OF
* UNICOM SYSTEMS, INC.
*
*/

package com.softlanding.plugin.demofa.dao;


import java.util.ArrayList;

/**
 * 
 * @author FXA
 *
 */
public class DemoFAHardCode {

  /**
   * 
   */
  public DemoFAHardCode() {

  }

  /**
   * 
   * @return An array of strings.
   */ 
  public ArrayList<String> getRecords() {
    
    ArrayList<String> stringArray = new ArrayList<String>();
    
        stringArray.add("A 0001 Blue");
        stringArray.add("A 0002 Green");
        stringArray.add("A 0003 Red");
        stringArray.add("A 0004 Yellow");
        stringArray.add("A 0005 White");
        stringArray.add("A 0006 Black");
    
    return stringArray;

  } 

  /**
   * 
   * @param colourCode colour
   */
  public void delete(String colourCode) {
   
    String d = "";
  }

  

}
