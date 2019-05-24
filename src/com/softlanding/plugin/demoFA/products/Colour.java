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
package com.softlanding.plugin.demoFA.products;

/**
 * 
 * @author FXA
 *
 */
public class Colour {
  
  private String status;
  private String code;
  private String shortDescription;
  private String longDescription;
  
  /**
   * Constructor to create a Colour object.
   */
  public Colour() {
   
  }
  
  /**
   * Constructor to create a Colour object.
   * @param status StringStatus
   * @param code String Code
   * @param shortDescription String Long Description
   * @param longDescription String Long Description
   */
  public Colour(String status, String code, String shortDescription, String longDescription) {
    this.status = status;
    this.code = code;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
  }
  
  /**
   * 
   * @return String Status
   */
  public String getStatus() {
    return this.status;
  }
  
  /**
   * 
   * @return String Code
   */
  public String getCode() {
    return this.code;
  }
  
  /**
   * 
   * @return String Short Description
   */
  public String getShortDescription() {
    return this.shortDescription;
  }
  
  /**
   * 
   * @return String Long Description
   */
  public String getLongDescription() {
    return this.longDescription;
  }
 
  /**
   * 
   * @param status String Status
   */
  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * 
   * @param code String Code
   */
  public void setCode(String code) {
    this.code = code;
  }
  
  /**
   * 
   * @param shortDescription String Short Description
   */
  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }
  
  /**
   * 
   * @param longDescription String Long Description
   */
  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }
  
  /**
   * @return String 
   */
  public String toString() {
    return this.status + " " + this.code + " " + this.shortDescription + " " + this.longDescription;
  }

}
