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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.softlanding.plugin.demoFA.products.Colour;
import com.softlanding.plugin.demoFA.util.IDemoFAConstants;

/**
 * 
 * @author FXA
 *
 */
public class DemoFADAO implements IDemoFADAO {

  //private String server = "jdbc:as400://10.0.28.154";
  private String server = IDemoFAConstants.IBMI_IP_ADDRESS;
  private String userId = IDemoFAConstants.USER_ID;
  private String password = IDemoFAConstants.PASSWORD;
  private Connection con = null;

  /**
   * 
   */
  public DemoFADAO() {

  }

  /**
   * Builds a list of colour codes.
   * 
   * @return An array of strings.
   */
  public ArrayList<String> getRecords() {
    // Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<String> stringArray = new ArrayList<String>();
    String element = "";

    con = getConnection(this.server, this.userId, this.password);
    try {
      stmt = this.con.createStatement();
    } catch (SQLException e) {
    }
    try {
      rs = stmt.executeQuery("select * from fadevlib.mstclr order by clrcde");

      while (rs.next()) {
        element = rs.getString("CLRSTS") + " " + rs.getString("CLRCDE") + " "
            + rs.getString("CLRSDSC") + " " + rs.getString("CLRLDSC");
        stringArray.add(element);
      }
    } catch (SQLException e) {
    }

    return stringArray;

  }
  
  /**
   * @return ArrayList of Colour ObjectsS
   */
  public ArrayList<Colour> getColours() {
   
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<Colour> colourArray = new ArrayList<Colour>();
    Colour colour;

    con = getConnection(this.server, this.userId, this.password);
    try {
      stmt = this.con.createStatement();
    } catch (SQLException e) {
    }
    try {
      rs = stmt.executeQuery("select * from fadevlib.mstclr order by clrcde");

      while (rs.next()) {
        colour = new Colour(rs.getString("CLRSTS"), rs.getString("CLRCDE"),
            rs.getString("CLRSDSC"), rs.getString("CLRLDSC"));
        colourArray.add(colour);
      }
    } catch (SQLException e) {
    }

    return colourArray;

  }

  
  /**
   * Delete a record from FADEVLIB/MSTCLR.
   * 
   * @param colourCode colour
   */
  public void delete(String colourCode) {

    Statement stmt = null;
    String deleteStatement = "delete from fadevlib.mstclr where CLRCDE = '" + colourCode.trim()
        + "'";

    try {
      this.con = getConnection(this.server, this.userId, this.password);
      stmt = this.con.createStatement();
      stmt.executeUpdate(deleteStatement);
      // this.con.close();
    } catch (SQLException e) {
    }
  
  }
  
  /**
   * @param colour Colour Object
   */
  public void deleteColour(Colour colour) {

    Statement stmt = null;
    String deleteStatement = "delete from fadevlib.mstclr where CLRCDE = '" + colour.getCode().trim()
        + "'";

    try {
      this.con = getConnection(this.server, this.userId, this.password);
      stmt = this.con.createStatement();
      stmt.executeUpdate(deleteStatement);
      // this.con.close();
    } catch (SQLException e) {
    }

  }

  /**
   * Establishes a connects to IBM i server.
   * 
   * @param server1   name of server.
   * @param userId1   user id.
   * @param password1 password.
   * @return con.
   */
  private Connection getConnection(String server1, String userId1, String password1) {

    if (this.con == null) {
      try {
        this.con = DriverManager.getConnection(server1, userId1, password1);
      } catch (SQLException e) {
      }
    }
    return this.con;
  }

}
