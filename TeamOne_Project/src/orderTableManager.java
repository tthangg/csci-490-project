

import java.sql.*;

/**
   The userTableManager class provides methods for 
   inserting a record into the user table of the 
   testdbgui database.
*/

public class orderTableManager
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
   
   public orderTableManager() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
   }
   
   //Creates method to select objects based on the search strings entered, hence the need for the
   //parameters that match what options they have to search with
   public static ResultSet selectOrder(String item, String quan, String month, String day, String year, String time, String status, String total) 
                      throws SQLException
   {

	   //Creates the SQL Statement, note the concatenation of the addon variable in case the user
	   //entered a number to search.  Not needed for the strings since the like command finds
	   //all results when the search field is blank.  Note the aliases to make the column names show nicely
	   //Also notice spaces after quotation marks so that the word AND does not run into field names
	   String ourSQLSelect = "SELECT  OrderTable.dborderid as ID, dbmenuname as Item, dborderquan as Quantity, dbordermonth as Month, dborderdate as Day, "
					+ "dborderyear as Year, dbordertime as Time, dborderstatus as Status, dbordertotal as Total from OrderTable, Menutable where "
					+ "OrderTable.dborderitem = MenuTable.dbmenuid AND dbmenuname Like ? AND  dborderquan Like ? AND dbordermonth Like ?"
					+ " AND dborderdate like ? AND dborderyear Like ? AND dbordertime Like ? AND dborderstatus Like ? AND dbordertotal  Like ?";  
                     
	   // Create a Statement object.
	   PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);

	   //Statement to insert our variables into the prepared sql placeholders.  Number is the position
	   //that the question mark is at above, starting at one.  Variable types matter
	   prepStmt.setString(1, "%" + item + "%");
	   prepStmt.setString(2, "%" + quan + "%");
	   prepStmt.setString(3, "%" + month + "%");
	   prepStmt.setString(4, "%" + day + "%");
	   prepStmt.setString(5, "%" + year + "%");
	   prepStmt.setString(6, "%" + time + "%");
	   prepStmt.setString(7, "%" + status + "%");
	   prepStmt.setString(8, "%" + total + "%");
	   //Checks to see if the number value was null, if not then the addon sql command from above will be
	   //added and we will need to add the variable to the prepared placeholder


	   //Executes the query, note that the command is slightly different than select, due to the fact that
	   //no results are being returned
	   ResultSet userResults = prepStmt.executeQuery();
	  

	   return userResults;
   }
   public static ResultSet viewOrder(String status) 
           throws SQLException
{

	//Creates the SQL Statement, note the concatenation of the addon variable in case the user
	//entered a number to search.  Not needed for the strings since the like command finds
	//all results when the search field is blank.  Note the aliases to make the column names show nicely
	//Also notice spaces after quotation marks so that the word AND does not run into field names
	String ourSQLSelect = "SELECT  OrderTable.dborderid as ID, dbmenuname as Item, dborderquan as Quantity, dbordermonth as Month, dborderdate as Day, "
				+ "dborderyear as Year, dbordertime as Time, dborderstatus as Status, dbordertotal as Total from OrderTable, Menutable where "
				+ "OrderTable.dborderitem = MenuTable.dbmenuid AND dborderstatus Like ? ";  
	          
	// Create a Statement object.
	PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
	//Statement to insert our variables into the prepared sql placeholders.  Number is the position
	//that the question mark is at above, starting at one.  Variable types matter

	prepStmt.setString(1, "%" + status + "%");

	//Checks to see if the number value was null, if not then the addon sql command from above will be
	//added and we will need to add the variable to the prepared placeholder
	
	
	//Executes the query, note that the command is slightly different than select, due to the fact that
	//no results are being returned
	ResultSet userResults = prepStmt.executeQuery();
	
	
	return userResults;
}


  	public static ResultSet selectUpdate(String orderID) 
	           throws SQLException
	{
				
		//Creates the SQL Statement.  Note the aliases to make the column names show nicely
		//Also notice spaces after quotation marks so that the word AND does not run into field names
		String ourSQLSelect = "SELECT dborderid as ID, dborderitem as Item, dborderquan as Quantity, dbordermonth as Month, "
					+ "dborderdate as Date, dborderyear as Year,dbordertime as Time, dborderstatus as Status, dbordertotal as Total from OrderTable where dborderid = ?";  
		          
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, orderID);
		
		//Executes the query, 
		ResultSet userResults = prepStmt.executeQuery();
		
		
		return userResults;
	}
  	
  	public void updateRecord (String item, String quan, String month, String date, String year, String time, String status, String total, 
  				double orderID) throws SQLException {
  		//Created variable to hold value id of position from userpositions table
  		
  		int itemID = 0;
  		String ourSQLLoc = "SELECT dbmenuid from MenuTable where dbmenuname = ?";  
        
  		// Create a Statement object.
  		PreparedStatement prepLoc = conn.prepareStatement(ourSQLLoc);
  		prepLoc.setString(1, item);
  		ResultSet itemResults = prepLoc.executeQuery();
  		//Gets the result of what was returned from the database for the name passed to the method
  		while (itemResults.next()) {
  			itemID = itemResults.getInt("dbmenuid");
  		}
  		
  		int intquan = Integer.parseInt(quan);
  		double dtotal = Double.parseDouble(total);
  		
  		String ourSQLUpdate = "update OrderTable set dborderitem = ?, dborderquan= ?, dbordermonth = ?, "
  				+ "dborderdate =  ?, dborderyear = ?, dbordertime = ?, dborderstatus = ?, dbordertotal = ? WHERE dborderid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setInt(1, itemID);
		prepStmt.setInt(2, intquan);
		prepStmt.setString(3, month);
		prepStmt.setString(4, date);
		prepStmt.setString(5, year);
		prepStmt.setString(6, time);
		prepStmt.setString(7, status);
		prepStmt.setDouble(8, dtotal);
		prepStmt.setDouble(9, orderID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	public void deleteOrder (double orderID) throws SQLException {

  		String ourSQLUpdate = "DELETE FROM OrderTable WHERE dborderid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter

		prepStmt.setDouble(1, orderID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	
  	 public void insert(String name, String quan, String month, String day, String year, String time, String status) 
             throws SQLException
		{
		Integer menuID = 0;
   		String ourSQLMenu = "SELECT dbmenuid from MenuTable where dbmenuname = ?";  
         
   		// Create a Statement object.
   		PreparedStatement prepMenu= conn.prepareStatement(ourSQLMenu);
   		prepMenu.setString(1, name);
   		ResultSet menuResults = prepMenu.executeQuery();
   		//Gets the result of what was returned from the database for the name passed to the method
   		while (menuResults.next()) {
   			menuID = menuResults.getInt("dbmenuid");
   		}
   		
   		
   		Integer mprice = 0;
   		String ourSQLPrice = "SELECT dbmenucost from MenuTable where dbmenuname = ?";  
         
   		// Create a Statement object.
   		PreparedStatement prepPrice = conn.prepareStatement(ourSQLPrice);
   		prepPrice.setString(1, name);
   		ResultSet priceResults = prepPrice.executeQuery();
   		//Gets the result of what was returned from the database for the name passed to the method
   		while (priceResults.next()) {
   			mprice = priceResults.getInt("dbmenucost");
   		}
  		double total = mprice * Integer.parseInt(quan);
		String ourSQLInsert = "INSERT INTO OrderTable(dborderitem, dborderquan, dbordermonth, dborderdate, dborderyear, dbordertime, dborderstatus, dbordertotal )"
				+  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  
		            
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setInt(1, menuID);
		prepStmt.setString(2, quan);
		prepStmt.setString(3, month);
		prepStmt.setString(4, day);
		prepStmt.setString(5, year);
		prepStmt.setString(6, time);
		prepStmt.setString(7, status);
		prepStmt.setDouble(8, total);
		//Executes the query, note that the command is slightly different than select, due to the fact that
		//no results are being returned
		prepStmt.executeUpdate();
		prepStmt.close();
		}
}
