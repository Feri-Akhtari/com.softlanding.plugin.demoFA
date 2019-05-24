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

import com.softlanding.plugin.demoFA.products.Colour;

/**
 * 
 * @author FXA
 *
 */
public interface IDemoFADAO {
 

  /**
   * 
   * @return List of Strings
   */
//  ArrayList<String> getRecords() throws ConException;
  ArrayList<String> getRecords();
  
  /**
   * 
   * @return RrayList of Colour Objects
   */
  ArrayList<Colour> getColours();
  
  /**
   * 
   * @param colourCode String code
   */
  void delete(String colourCode);
  
  /**
   *
   * @param colour Colour Object
   */
  void deleteColour(Colour colour);


}
  

  

