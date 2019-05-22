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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author FXA
 *
 */
public class DemoFADAO {

  private String server = "jdbc:as400://10.0.28.154";
  private String userId = "feri";
  private String password = "domria2k";

  /**
   * 
   */
  public DemoFADAO() {

  }

  /**
   * 
   * @return An array of strings.
   */
  public ArrayList<String> getRecords() {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<String> stringArray = new ArrayList<String>();
    String element = "";

    con = getConnection(this.server, this.userId, this.password);
    try {
      stmt = con.createStatement();
    } catch (SQLException e1) {

    }
    try {
      rs = stmt.executeQuery("select * from fadevlib.mstclr order by clrcde");

      while (rs.next()) {
        element = rs.getString("CLRSTS") + " " + rs.getString("CLRCDE") + " "
            + rs.getString("CLRLDSC") + " " + rs.getString("CLRSDSC");
        stringArray.add(element);
      }
      // stringArray.add("It's definitely here");
    } catch (SQLException e) {

    }

    try {
      con.close();
    } catch (SQLException e) {

    }
    return stringArray;

  }

  /**
   * 
   * @param colourCode colour
   */
  public void delete(String colourCode) {
    Connection con = null;
    Statement stmt = null;
    String deleteStatement = "delete from fadevlib.mstclr where CLRCDE = '" + colourCode.trim()
        + "'";
    String error = "";

    con = getConnection(this.server, this.userId, this.password);
    try {
      stmt = con.createStatement();
    } catch (SQLException e) {
      error = e.toString();
    }
    try {
      // stmt.executeQuery(deleteStatement);
      stmt.executeUpdate(deleteStatement);
    } catch (SQLException e) {
      error = e.toString();
    }

    try {
      con.close();
    } catch (SQLException e) {
      error = e.toString();
    }

  }

  /**
   * 
   * @param server1   name of server.
   * @param userId1   user id.
   * @param password1 password.
   * @return con.
   */
  private Connection getConnection(String server1, String userId1, String password1) {

    Connection con = null;
    try {
      con = DriverManager.getConnection(server1, userId1, password1);

    } catch (SQLException e) {

    }
    return con;
  }

}
