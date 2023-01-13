

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;


public class searchOrder extends JFrame {

	panelBuilderOrder2 orderInfoPanel;   // Panel for order information
	JPanel buttonPanel;                    // Panel for buttons
	JPanel listPanel;          // A panel to hold the scroll pane
	String searchString = "";   
	JScrollPane scrollPane;    // A scroll pane to hold the list
	JTable ourTable;
	
	
	public searchOrder()
	   {
	      // Set the window title.
	      setTitle("Select Order");
	      
	      // Specify an action for the close button.
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	      // Create a panelBuilder object.
	      orderInfoPanel = new panelBuilderOrder2("Search Order");
	      
	      // Build the buttonPanel object.
	      buildButtonPanel();
	      
	      // Build the listPanel object.
	      buildListPanel();
	      
	      // create a BorderLayout manager.
	      setLayout(new BorderLayout());
	      
	      // Add the panels to the content pane.
	      add(orderInfoPanel, BorderLayout.NORTH);
	      add(listPanel, BorderLayout.CENTER);
	      add(buttonPanel, BorderLayout.SOUTH);
	      
	      // Pack and display the window.
	      pack();
	      setSize(600, 700);
	      setVisible(true);
	   }
	   
	   
	   private void buildListPanel()
	   {
	      try
	      {
	         // Create a panel.
	         listPanel = new JPanel();
	         
	         // Add a titled border to the panel.
	         listPanel.setBorder(BorderFactory.
	         createTitledBorder("Order Information"));
	         //Create object to access database.
	         orderTableManager getInfo = new orderTableManager();
	         
	         //Create a resultset to hold the blank search for when the page starts
	         ResultSet orderInfo = orderTableManager.selectOrder("", "", "", "","", "", "","");
	         
	         //Uses the rs2XML code to create an object that will fill our jtable with the information 
		     ourTable = new JTable(DbUtils.resultSetToTableModel(orderInfo));
		     //Scrollpane in case we have more records than we want to show up. 
		     scrollPane = new JScrollPane(ourTable);
		     //Create dimension to use to size the scroll pane
		     Dimension dim = new Dimension(500,200);
		     //Set the size of the scroll pane
		     scrollPane.setPreferredSize(dim);
		     listPanel.setPreferredSize(dim);
		     //Adds scrollPane to our listPanel
		     listPanel.add(scrollPane);
		     
	          }
	      catch(SQLException ex)
	      {
	         // If something goes wrong with the database, 
	         // display a message to the order.
	         JOptionPane.showMessageDialog(null, ex.toString());
	      }
	   }
	   /**
	      buildButtonPanel method
	    */
	   
	   private void buildButtonPanel()
	   {
	      // Create a panel for the buttons.
	      buttonPanel = new JPanel();
	      
	      // Create a Submit button and add an action listener.
	      JButton submitButton = new JButton("Submit");
	      submitButton.addActionListener(new SubmitButtonListener());
	      
	      // Create a Clear button and add an action listener.
	      JButton clearButton = new JButton("Clear");
	      clearButton.addActionListener(new ClearButtonListener());

	      // Create an Exit button.
	      JButton exitButton = new JButton("Exit");
	      exitButton.addActionListener(new ExitButtonListener());
	      
	      // Add the buttons to the panel.
	      buttonPanel.add(submitButton);
	      buttonPanel.add(clearButton);
	      buttonPanel.add(exitButton);
	   }
	   
	   /**
	      Private inner class that handles Submit button events.
	    */
	   
	   private class SubmitButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	        // Get the order information from the text fields.
	    	  String menu = orderInfoPanel.getMenu();
	            String quan = orderInfoPanel.getQuan();
	            String month = orderInfoPanel.getMonth();
	            String day = orderInfoPanel.getDay();
	            String year = orderInfoPanel.getYear();
	            String time = orderInfoPanel.getTime();
	            String status = orderInfoPanel.getStatus();
	            String total = orderInfoPanel.getPrice();
	            //Create a result set variable to hold the info from the search
	            ResultSet searchInfo = null;
	            
	            try {
	            	//Create an object to instantiate the Connection to the table
					orderTableManager getInfo = new orderTableManager();
					//This creates a blank search of the table
					searchInfo = orderTableManager.selectOrder(menu, quan, month, day, year, time, status, total);
	            } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //Resets the table to the search criteria given above
	            ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
	            
	    
	      }
	   }
	   
	   /**
	      Private inner class that handles Clear button events.
	    */
	   
	   private class ClearButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  // Clears all the input boxes
	    	  orderInfoPanel.clear();
	    	  
	    	  //We also want to clear the search criteria for what is showing up, so we create a
	    	  //resultset variable to hold a new blank search
	    	  ResultSet searchInfo = null;
	            
	            try {
	            	//Create an object to instantiate the Connection to the table
					orderTableManager getInfo = new orderTableManager();
					//This creates a blank search of the table
					searchInfo = orderTableManager.selectOrder("", "", "", "","", "", "","");
	            } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //Resets the table to the search criteria given above
	            ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));

	      }
	   }
	   
	   /**
	      Private inner class that handles Exit button events.
	    */
	   
	   private class ExitButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         // Exit the orderlication.
	    	  dispose();
	      }
	   }
	

}
