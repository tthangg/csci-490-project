

import java.sql.*;

/**
   The userTableManager class provides methods for 
   inserting a record into the user table of the 
   testdbgui database.
*/

public class invTableManager
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
   
   public invTableManager() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
   }
   
   //Creates method to select objects based on the search strings entered, hence the need for the
   //parameters that match what options they have to search with
   public static ResultSet selectInv(String item, String quan, String type) 
                      throws SQLException
   {

	   //Creates the SQL Statement, note the concatenation of the addon variable in case the user
	   //entered a number to search.  Not needed for the strings since the like command finds
	   //all results when the search field is blank.  Note the aliases to make the column names show nicely
	   //Also notice spaces after quotation marks so that the word AND does not run into field names
	   String ourSQLSelect = "SELECT dbinvid as ID, dbinvitem as Item, dbinvquantity as Quantity, dbinvtype as Type"
					+ " from IventoryTable where dbinvitem Like ? AND"
					+ " dbinvquantity Like ? AND dbinvtype Like ?";  
                     
	   // Create a Statement object.
	   PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);

	   //Statement to insert our variables into the prepared sql placeholders.  Number is the position
	   //that the question mark is at above, starting at one.  Variable types matter
	   prepStmt.setString(1, "%" + item + "%");
	   prepStmt.setString(2, "%" + quan + "%");
	   prepStmt.setString(3, "%" + type + "%");
	   //Checks to see if the number value was null, if not then the addon sql command from above will be
	   //added and we will need to add the variable to the prepared placeholder


	   //Executes the query, note that the command is slightly different than select, due to the fact that
	   //no results are being returned
	   ResultSet userResults = prepStmt.executeQuery();
	  

	   return userResults;
   }
   public static ResultSet selectInv2() 
           throws SQLException
{
	
	//Creates the SQL Statement, note the concatenation of the addon variable in case the user
	//entered a number to search.  Not needed for the strings since the like command finds
	//all results when the search field is blank.  Note the aliases to make the column names show nicely
	//Also notice spaces after quotation marks so that the word AND does not run into field names
	String ourSQLSelect = "SELECT dbinvid as ID, dbinvitem as Item, dbinvquantity+' /100' as Level, dbinvtype as Type"
				+ " from IventoryTable";  
	          
	// Create a Statement object.
	PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
	//Statement to insert our variables into the prepared sql placeholders.  Number is the position
	//that the question mark is at above, starting at one.  Variable types matter
	
	//Checks to see if the number value was null, if not then the addon sql command from above will be
	//added and we will need to add the variable to the prepared placeholder
	
	
	//Executes the query, note that the command is slightly different than select, due to the fact that
	//no results are being returned
	ResultSet userResults = prepStmt.executeQuery();
	
	
	return userResults;

}
  	public static ResultSet selectUpdate(String invID) 
	           throws SQLException
	{
				
		//Creates the SQL Statement.  Note the aliases to make the column names show nicely
		//Also notice spaces after quotation marks so that the word AND does not run into field names
		String ourSQLSelect = "SELECT dbinvid as ID, dbinvitem as Item, dbinvquantity as Quantity, dbinvtype as Type"
					+ " from IventoryTable where dbinvid = ?";  
		          
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, invID);
		
		//Executes the query, 
		ResultSet userResults = prepStmt.executeQuery();
		
		
		return userResults;
	}
  	
  	public void updateRecord (String item, String quan, String type, 
  				double invID) throws SQLException {
  		//Created variable to hold value id of position from userpositions table
  		int intquan=Integer.parseInt(quan);
  		
  		String ourSQLUpdate = "update IventoryTable set dbinvitem = ?, "
  				+ "dbinvquantity =  ?, dbinvtype = ? WHERE dbinvid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, item);
		prepStmt.setInt(2, intquan);
		prepStmt.setString(3, type);
		prepStmt.setDouble(4, invID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	public void deleteInv (double invID) throws SQLException {

  		String ourSQLUpdate = "DELETE FROM IventoryTable WHERE dbinvid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter

		prepStmt.setDouble(1, invID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	
  	 public void insert(String item, String quan, String type) 
             throws SQLException
		{

  		
		String ourSQLInsert = "INSERT INTO IventoryTable (dbinvitem, dbinvquantity, dbinvtype)"
				+  "VALUES (?, ?, ?)";  
		            
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, item);
		prepStmt.setString(2, quan);
		prepStmt.setString(3, type);

		
		
		//Executes the query, note that the command is slightly different than select, due to the fact that
		//no results are being returned
		prepStmt.executeUpdate();
		prepStmt.close();
		}
}
