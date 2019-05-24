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

package com.softlanding.plugin.demoFA.dao;


import java.util.ArrayList;

import com.softlanding.plugin.demoFA.products.*;

/**
 * 
 * @author FXA
 *
 */
public class DemoFAHardCode implements IDemoFAHardCode {

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
   * @return colourArray ArrayList
   */
  public ArrayList<Colour> getColours() {
    
    ArrayList<Colour> colourArray = new ArrayList<Colour>();
    
    colourArray.add(new Colour("A", "0001", "Blue", "Blue"));
    colourArray.add(new Colour("A", "0002", "Green", "Green"));
    colourArray.add(new Colour("A", "0003", "Red", "Red"));
    colourArray.add(new Colour("A", "0004", "Yellow", "Yellow"));
    colourArray.add(new Colour("A", "0005", "White", "White"));
    colourArray.add(new Colour("A", "0006", "Black", "Black"));
    
    return colourArray;
  }

  /**
   * 
   * @param colourCode colour
   */
  public void delete(String colourCode) {
   
  }
  
  /**
   * 
   * @param colour Colour
   */
  public void deleteColour(Colour colour) {
    
  }

  

}
