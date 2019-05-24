
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

package com.softlanding.plugin.demoFA.util;

/**
 * 
 * @author FXA
 *
 */
public interface IDemoFAConstants {

  /**
   * Field Name descriptors.
   */
  // note constants defined in an interface are
  // public static final by default
  String IBMI_IP_ADDRESS = "jdbc:as400://10.0.28.154";
  String USER_ID = " ";
  String PASSWORD = " ";

  /**
   * This method does nothing and it's only there because CheckStyle complains if
   * an interface has no methods.
   * 
   */
  void checkStyleWarningRemover();

}
