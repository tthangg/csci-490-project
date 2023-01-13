

import java.sql.*;

/**
   The userTableManager class provides methods for 
   inserting a record into the user table of the 
   testdbgui database.
*/

public class userTableManager
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
   
   public userTableManager() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
   }
   
   //Creates method to select objects based on the search strings entered, hence the need for the
   //parameters that match what options they have to search with
   public static ResultSet selectUser(String name, String email,String phone, String address, String admin ) 
                      throws SQLException
   {

	   //Creates the SQL Statement, note the concatenation of the addon variable in case the user
	   //entered a number to search.  Not needed for the strings since the like command finds
	   //all results when the search field is blank.  Note the aliases to make the column names show nicely
	   //Also notice spaces after quotation marks so that the word AND does not run into field names
	   String ourSQLSelect = "SELECT dbuserid as ID, dbusername as Name,  dbuseremail as Email, dbuserphone as Phone, "
					+ "dbuseraddress as Address, dbuseradmin as Admin from UserTable where dbusername Like ?"
					+ " AND dbuseremail Like ? AND dbuserphone like ? AND dbuseraddress Like ? AND dbuseradmin Like ?";  
                     
	   // Create a Statement object.
	   PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);

	   //Statement to insert our variables into the prepared sql placeholders.  Number is the position
	   //that the question mark is at above, starting at one.  Variable types matter
	   prepStmt.setString(1, "%" + name + "%");	  
	   prepStmt.setString(2, "%" + email + "%");
	   prepStmt.setString(3, "%" + phone + "%");
	   prepStmt.setString(4, "%" + address + "%");
	   prepStmt.setString(5, "%" + admin+ "%");
	   //Checks to see if the number value was null, if not then the addon sql command from above will be
	   //added and we will need to add the variable to the prepared placeholder


	   //Executes the query, note that the command is slightly different than select, due to the fact that
	   //no results are being returned
	   ResultSet userResults = prepStmt.executeQuery();
	  

	   return userResults;
   }
   

  	public static ResultSet selectUpdate(String userID) 
	           throws SQLException
	{
				
		//Creates the SQL Statement.  Note the aliases to make the column names show nicely
		//Also notice spaces after quotation marks so that the word AND does not run into field names
		String ourSQLSelect = "SELECT dbuserid as ID, dbusername as Name,  dbuseremail as Email, dbuserphone as Phone, "
					+ "dbuseraddress as Address, dbuseradmin as Admin from UserTable where dbuserid = ?";  
		          
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, userID);
		
		//Executes the query, 
		ResultSet userResults = prepStmt.executeQuery();
		
		
		return userResults;
	}
  	
  	public void updateRecord (String name, String email, String phone, String address,  String admin, 
  				double userID) throws SQLException {

  		String ourSQLUpdate = "update UserTable set dbusername = ?, "
  				+ " dbuseremail = ?, dbuserphone = ?, dbuseraddress =  ?,dbuseradmin = ? WHERE dbuserid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, name);
		prepStmt.setString(2, email);
		prepStmt.setString(3, phone);
		prepStmt.setString(4, address);
		prepStmt.setString(5, admin);
		prepStmt.setDouble(6, userID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	public void deleteUser (double userID) throws SQLException {

  		String ourSQLUpdate = "DELETE FROM UserTable WHERE dbuserid = ?";
  		
  		// Create a Statement object.
  		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter

		prepStmt.setDouble(1, userID);
		
		prepStmt.executeUpdate();
		prepStmt.close();
  	}
  	
  	 public void insert(String name,String email, String phone, String address, String admin) 
             throws SQLException
		{

  		
		String ourSQLInsert = "INSERT INTO UserTable (dbusername, dbuseremail, dbuserphone,  dbuseraddress, dbuseradmin)"
				+  "VALUES (?, ?, ?, ?, ?)";  
		            
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		//Statement to insert our variables into the prepared sql placeholders.  Number is the position
		//that the question mark is at above, starting at one.  Variable types matter
		prepStmt.setString(1, name);
		prepStmt.setString(2, email);
		prepStmt.setString(3, phone);
		prepStmt.setString(4, address);
		prepStmt.setString(5, admin);
		
		
		//Executes the query, note that the command is slightly different than select, due to the fact that
		//no results are being returned
		prepStmt.executeUpdate();
		prepStmt.close();
		}
}
