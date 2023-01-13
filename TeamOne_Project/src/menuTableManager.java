

import java.sql.*;

/**
   The menuTableManager class provides methods for 
   inserting a record into the menu table of the 
   testdbgui database.
*/

public class menuTableManager
{
   // Create a named constant for the URL.
   // NOTE: This value is specific for Java DB
   public final String DB_URL = 
                "jdbc:ucanaccess://TeamOneDB.accdb";

   // Field for the database connection
   private static Connection conn;

   /**
      Constructor
    */
   
   public menuTableManager() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
   }
   
   //Creates method to select objects based on the search strings entered, hence the need for the
   //parameters that match what options they have to search with
   public static ResultSet selectMenu(String name, String item, String quan, String cost )
                      throws SQLException
   {

	   //Creates the SQL Statement, note the concatenation of the addon variable in case the menu
	   //entered a number to search.  Not needed for the strings since the like command finds
	   //all results when the search field is blank.  Note the aliases to make the column names show nicely
	   //Also notice spaces after quotation marks so that the word AND does not run into field names
	   String ourSQLSelect = "SELECT dbmenuid as ID, dbmenuname as Name, dbinvitem as Item_Used, "
			   		+ " dbmenuitemused as Quantitty_Used, dbmenucost as Price from MenuTable, IventoryTable where "
					+ "MenuTable.dbmenuitem = IventoryTable.dbinvid AND dbmenuname Like ? AND"
					+ " dbinvitem Like ? AND dbmenuitemused Like ? AND dbmenucost like ?";  
                     
	   // Create a Statement object.
	   PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);

	   //Statement to insert our variables into the prepared sql placeholders.  Number is the position
	   //that the question mark is at above, starting at one.  Variable types matter
	   prepStmt.setString(1, "%" + name + "%");
	   prepStmt.setString(2, "%" + item + "%");
	   prepStmt.setString(3, "%" + quan + "%");
	   prepStmt.setString(4, "%" + cost + "%");
	   //Checks to see if the number value was null, if not then the addon sql command from above will be
	   //added and we will need to add the variable to the prepared placeholder


	   //Executes the query, note that the command is slightly different than select, due to the fact that
	   //no results are being returned
	   ResultSet menuResults = prepStmt.executeQuery();
	  

	   return menuResults;
   }
   

  	public static ResultSet selectUpdate(String menuID) 
	           throws SQLException
	{
				
		//Creates the SQL Statement.  Note the aliases to make the column names show nicely
		//Also notice spaces after quotation marks so that the word AND does not run into field names
		String ourSQLSelect = "SELECT dbmenuid as ID, dbmenuname as Name, dbmenuitem as Item_Used, "
			   		+ " dbmenuitemused as Quantitty_Used, dbmenucost as Price from MenuTable where dbmenuid = ?";  
		          
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, menuID);
		
		//Executes the query, 
		ResultSet menuResults = prepStmt.executeQuery();
		
		
		return menuResults;
	}
  	
  	public void updateRecord (String name,String iid, String used, String cost, 
  				double menuID) throws SQLException {
  		//Created variable to hold value id of position from menupositions table
		int itemID = 0;
   		String ourSQLLoc = "SELECT dbinvid from IventoryTable where dbinvitem = ?";  
         
   		// Create a Statement object.
   		PreparedStatement prepItem = conn.prepareStatement(ourSQLLoc);
   		prepItem.setString(1, iid);
   		ResultSet itemResults = prepItem.executeQuery();
   		//Gets the result of what was returned from the database for the name passed to the method
   		while (itemResults.next()) {
   			itemID = itemResults.getInt("dbinvid");
   		}
  		
  		int itemused = Integer.parseInt(used);
  		double thecost = Double.parseDouble(cost);
 
  		String ourSQLUpdate = "update MenuTable set dbmenuname = ?, "
  				+ "dbmenuitem =  ?, dbmenuitemused = ?, dbmenucost = ? WHERE dbmenuid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, name);
		prepStmt.setInt(2, itemID);
		prepStmt.setInt(3, itemused);
		prepStmt.setDouble(4, thecost);
		prepStmt.setDouble(7, menuID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	public void deleteMenu (double menuID) throws SQLException {

  		String ourSQLUpdate = "DELETE FROM Menuloyees WHERE dbmenuid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter

		prepStmt.setDouble(1, menuID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	
  	 public void insert(String name, String item, String used, String price) 
             throws SQLException
		{
		int itemID = 0;
   		String ourSQLLoc = "SELECT dbinvid from IventoryTable where dbinvitem = ?";  
         
   		// Create a Statement object.
   		PreparedStatement prepItem = conn.prepareStatement(ourSQLLoc);
   		prepItem.setString(1, item);
   		ResultSet itemResults = prepItem.executeQuery();
   		//Gets the result of what was returned from the database for the name passed to the method
   		while (itemResults.next()) {
   			itemID = itemResults.getInt("dbinvid");
   		}
  		
		String ourSQLInsert = "INSERT INTO MenuTable (dbmenuname, dbmenuitem, dbmenuitemused,  dbmenucost)"
				+  "VALUES (?, ?, ?, ?)";  
		            
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, name);
		prepStmt.setInt(2, itemID);
		prepStmt.setString(3, used);
		prepStmt.setString(4, price);
		
		
		
		//Executes the query, note that the command is slightly different than select, due to the fact that
		//no results are being returned
		prepStmt.executeUpdate();
		prepStmt.close();
		}
}
