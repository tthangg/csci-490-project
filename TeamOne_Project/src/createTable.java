/*
 This only meant to run once, I already run it
 before running it again please delete all tables in DoctorDB file
 to ensure data integrity, Thank you
 
 if want to run it again please delete all table in the access file thank you
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class createTable {

		public static void insertSample() throws SQLException{
		
			final String DB_URL = 
                "jdbc:ucanaccess://TeamOneDB.accdb";
		
		
			Connection conn = DriverManager.getConnection(DB_URL);
			 
			
		   String uname = " Tam Thang";	
		   String uemail = "tam@thang.com";	
		   String uphone = "846-469-8261";
		   String uaddress = "895 NeverLand Hwy Myrtle Beach, SC 29575";
		   String uadmin = "Yes";

		   
		   String sql="INSERT INTO UserTable(dbusername, dbuseremail, dbuserphone, dbuseraddress, dbuseradmin) VALUES(?,?,?,?,?)";
		   
		   PreparedStatement prepStmt = conn.prepareStatement(sql);
		   
		   //prepStmt.setInt(1, id);
		   prepStmt.setString(1, uname);
		   prepStmt.setString(2, uemail);
		   prepStmt.setString(3, uphone);
		   prepStmt.setString(4, uaddress);
		   prepStmt.setString(5, uadmin);
		   prepStmt.executeUpdate();
		   
		   
		   
		   
	
		   int oitem = 1;
		   int oquan = 2;
		   String month = "Feb";
		   String date = "25th";
		   String year = "2021";
		   String time="9:30am";
		   String ostatus = "Active";	
		   double ototal = 19.95;
		   

		   
		   String sql1="INSERT INTO OrderTable(dborderitem, dborderquan, dbordermonth, dborderdate, dborderyear, dbordertime, dborderstatus, dbordertotal) VALUES(?,?,?,?,?,?,?,?)";
		   
		   PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		   
		   //prepStmt.setInt(1, id);
		
		   prepStmt1.setInt(1, oitem);
		   prepStmt1.setInt(2, oquan);
		   prepStmt1.setString(3, month);
		   prepStmt1.setString(4, date);
		   prepStmt1.setString(5, year);
		   prepStmt1.setString(6, time);
		   prepStmt1.setString(7, ostatus);
		   prepStmt1.setDouble(8, ototal);
		   prepStmt1.executeUpdate();
   
	   
		   
		   
		   String iitem = "sandbag";
		   int iquan = 13;
		   String itype = "10bls pack";
		   	

		   
		   String sql3="INSERT INTO IventoryTable(dbinvitem, dbinvquantity, dbinvtype) VALUES(?,?,?)";
		   
		   PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		   
		   //prepStmt.setInt(1, id);
		   prepStmt3.setString(1, iitem);
		   prepStmt3.setInt(2, iquan);
		   prepStmt3.setString(3, itype);
		   prepStmt3.executeUpdate();
		   
		   

	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		   final String DB_URL = 
	                "jdbc:ucanaccess://TeamOneDB.accdb";
		   
		   try{
			 //Connect to a the database
			   Connection conn = DriverManager.getConnection(DB_URL);
			   
			   System.out.println("Successfully connects to the database");
			
			  
			  //create User table
			   
			   String sql = "CREATE TABLE UserTable(dbuserid AUTOINCREMENT PRIMARY KEY," +
				
					   "dbusername CHAR(100),"+
					   "dbuseremail CHAR(255),"+
					   "dbuserphone CHAR(15),"+
					   "dbuseraddress CHAR(255),"+
					   "dbuseradmin CHAR(3))";
			   
			   PreparedStatement stmt = conn.prepareStatement(sql);
			   
			   stmt.executeUpdate();
			   
			   //create Order Talbe
			   
			   String sql1 = "CREATE TABLE OrderTable(dborderid AUTOINCREMENT PRIMARY KEY," +
					
					   "dborderitem int,"+		
					   "dborderquan int,"+
					   "dbordermonth CHAR(20),"+
					   "dborderdate CHAR(10),"+
					   "dborderyear CHAR(10),"+
					   "dbordertime CHAR(20),"+
					   "dborderstatus CHAR(10),"+
					   "dbordertotal double)";
			   
			   PreparedStatement stmt1 = conn.prepareStatement(sql1);
			   
			   stmt1.executeUpdate();
			   

			   //create Inventory Table
			   
			   String sql3 = "CREATE TABLE IventoryTable(dbinvid AUTOINCREMENT PRIMARY KEY," +
						
					   "dbinvitem CHAR(50),"+
					   "dbinvquantity int,"+	
					   "dbinvtype CHAR(50))";
			   
			   PreparedStatement stmt3 = conn.prepareStatement(sql3);
			   
			   stmt3.executeUpdate();
			   
			   //create Recipe table
			   insertSample();
			   System.out.println("Inserted Samples Data");

		   }
	
		   catch(Exception e){
			   e.printStackTrace();
			   System.out.println("A Table Already Exists");
			   insertSample();
			   System.out.println("Inserted Samples Data");
		   }
	}

}
