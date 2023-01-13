

import java.sql.*;



public class invPositionsManager {

   // Create a named constant for the URL.
   // NOTE: This value is specific for Java DB
   public final String DB_URL = 
                "jdbc:ucanaccess://TeamOneDB.accdb";

   // Field for the database connection
   private static Connection conn;

   /**
      Constructor
    */
   
   public invPositionsManager() throws SQLException
   {
      // Create a connection to the database.
      conn = DriverManager.getConnection(DB_URL);
   }
   
   //Creates method to select objects based on the search strings entered, hence the need for the
   //parameters that match what options they have to search with
   public static ResultSet selectPositions() throws SQLException
   {
	   	   
	   //Creates the SQL Statement
	   String ourSQLSelect = "SELECT * from IventoryTable";  
                     
	   // Create a Statement object.
	   PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);

	   ResultSet positionResults = prepStmt.executeQuery();
	  

	   return positionResults;
   }
}