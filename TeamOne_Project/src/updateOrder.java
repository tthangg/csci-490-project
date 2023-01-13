

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;



public class updateOrder extends JFrame{
	  
	
		panelBuilderOrder2 orderUpdatePanel;   // Panel for order information
		panelBuilderOrder2 orderSearchPanel;   // Panel for order information
		JPanel buttonPanel;                  // Panel for buttons
		JPanel searchButtonPanel;            // Panel for Search button
		JPanel listPanel;          // A panel to hold the scroll pane
		String searchString = "";   
		JScrollPane scrollPane;    // A scroll pane to hold the list
		JList nameList;
		JTable ourTable;
		String selectedValue;
		
		public updateOrder()
		   {
		      // Set the window title.
		      setTitle("Update Order");
		      
		      // Specify an action for the close button.
		      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      
		      // Create a panelBuilder object.
		      orderUpdatePanel = new panelBuilderOrder2("Update Order");
		      orderSearchPanel = new panelBuilderOrder2("Search Order");
		      
		      // Build the buttonPanel object.
		      buildButtonPanel();
		      buildSearchButtonPanel();
		      // Build the listPanel object.
		      buildListPanel();
		      
		      // create a BorderLayout manager.
		      setLayout(new GridBagLayout()); 
		      GridBagConstraints gbc = new GridBagConstraints();
		      
		      gbc.gridx = 0;
		      gbc.gridy = 0;
		      //Allows fill for both horizontal and vertical
		      gbc.fill = GridBagConstraints.BOTH;
		      gbc.weightx = 1.0;
		      gbc.weighty = 0;
		      gbc.gridheight = 3;
		      //Adds search panel with settings for GridBag above
		      add(orderSearchPanel, gbc);
		      
		      gbc.gridheight = 1;
		      gbc.weighty = 0;
		      gbc.gridx = 0;
		      gbc.gridy = 3;
		      //Adds search button panel with settings for GridBag above
		      add(searchButtonPanel, gbc);
		      
		      //Creates padding between columns
		      gbc.insets = new Insets(0,10,0,0);
		      gbc.weighty = .05;
		      gbc.gridx = 1;
		      gbc.gridy = 0;
		      //Adds list panel with settings for GridBag above
		      add(listPanel, gbc);
		      
		      gbc.weighty = .05;
		      gbc.gridx = 1;
		      gbc.gridy = 2;
		      //Adds update panel with settings for GridBag above
		      add(orderUpdatePanel, gbc);
		      
		      gbc.weighty = 0;
		      gbc.gridx = 1;
		      gbc.gridy = 3;
		      //Adds button panel with settings for GridBag above
		      add(buttonPanel, gbc);
		      
		      // Pack and display the window.
		      pack();
		      setSize(1200, 850);
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
		         ResultSet orderInfo = orderTableManager.selectOrder("", "", "", "","", "", "", "");
		         
		         //Uses the rs2XML code to create an object that will fill our jtable with the information 
			     ourTable = new JTable(DbUtils.resultSetToTableModel(orderInfo));
			 
			     
			     Dimension dim = new Dimension(600, 125);
			     
			     
			     //Scrollpane in case we have more records than we want to show up.
			     scrollPane = new JScrollPane(ourTable);
			     
			     //Set the size of the scroll pane
			     scrollPane.setPreferredSize(dim);
			     
			     //Adds scrollPane to our listPanel
			     listPanel.add(scrollPane);
			     
			     //Listens in to what we click on in the table and gets the index value of that item.
			     ourTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		                public void valueChanged(ListSelectionEvent event) {
		                	//Check to make sure table is not being reloaded
		                	if (ourTable.getSelectedRow() >= 0) {
		                	//Gets ID value of row
		                    selectedValue = ourTable.getValueAt(ourTable.getSelectedRow(), 0).toString();
		                    try {
				    		  	ResultSet updateInfo = null;
				            	//Create an object to instantiate the Connection to the table
								orderTableManager getInfo = new orderTableManager();
								//This creates a blank search of the table
								updateInfo = orderTableManager.selectUpdate(selectedValue);
								
								while (updateInfo.next()) {
									orderUpdatePanel.setMenu(updateInfo.getInt("Item"));
									orderUpdatePanel.setQuan(updateInfo.getString("Quantity"));
									orderUpdatePanel.setMonth(updateInfo.getString("Month"));
									orderUpdatePanel.setDay(updateInfo.getString("Date"));
									orderUpdatePanel.setYear(updateInfo.getString("Year"));
									orderUpdatePanel.setTime(updateInfo.getString("Time"));
									orderUpdatePanel.setStatus(updateInfo.getString("Status"));
									orderUpdatePanel.setPrice(updateInfo.getString("Total"));
								} 
		                    }catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	}
		                }
		            });			         
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
		      
		      JButton deleteButton = new JButton("Delete");
		      deleteButton.addActionListener(new DeleteButtonListener());
		      
		      // Add the buttons to the panel.
		      buttonPanel.add(submitButton);
		      buttonPanel.add(clearButton);
		      buttonPanel.add(exitButton);
		      buttonPanel.add(deleteButton);
		   }
		   
		   /**
		      buildSearchButtonPanel method
		    */
		   private class DeleteButtonListener implements ActionListener
		   {
		      public void actionPerformed(ActionEvent e)
		      {
		        // Get the order information from the text fields.

		            Double selValue = Double.parseDouble(selectedValue);
		            
		            orderTableManager updater;
					try {
						//Create object and run update code in database
						updater = new orderTableManager();
						updater.deleteOrder(selValue);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		            
					
					 //The code below is to requery the DB to get the updated info and output to the screen
		            ResultSet searchInfo = null;
		            
		            try {
		            	//Create an object to instantiate the Connection to the table
						orderTableManager getInfo = new orderTableManager();
						//This creates a blank search of the table
						searchInfo = getInfo.selectOrder("", "", "", "","", "", "", "");
		            } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            //Resets the table to the new values
		            ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		           
				     orderUpdatePanel.clear();
		      }
		   }
		   
		   
		   private void buildSearchButtonPanel()
		   {
		      // Create a panel for the buttons.
		      searchButtonPanel = new JPanel();
		      
		      // Create a Search button and add an action listener.
		      JButton searchButton = new JButton("Search ");
		      searchButton.addActionListener(new SearchButtonListener());
		      
		      // Add the buttons to the panel.
		      searchButtonPanel.add(searchButton);
		   }
		   
		   /**
		      Private inner class that handles Submit button events.
		    */
		   
		   private class SubmitButtonListener implements ActionListener
		   {
		      public void actionPerformed(ActionEvent e)
		      {
		        // Get the order information from the text fields.
		    	  	String menu = orderUpdatePanel.getMenu();
		            String quan = orderUpdatePanel.getQuan();
		            String month = orderUpdatePanel.getMonth();
		            String day = orderUpdatePanel.getDay();
		            String year = orderUpdatePanel.getYear();
		            String time = orderUpdatePanel.getTime();
		            String status = orderUpdatePanel.getStatus();
		            String total = orderUpdatePanel.getPrice();;
		            //Parse value to double so it can be used in sql query
		            Double selValue = Double.parseDouble(selectedValue);
		            
		            orderTableManager updater;
					try {
						//Create object and run update code in database
						updater = new orderTableManager();
						updater.updateRecord(menu, quan, month, day, year, time, status, total, selValue);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		            
					
					 //The code below is to requery the DB to get the updated info and output to the screen
		            ResultSet searchInfo = null;
		            
		            try {
		            	//Create an object to instantiate the Connection to the table
						orderTableManager getInfo = new orderTableManager();
						//This creates a blank search of the table
						searchInfo = getInfo.selectOrder("", "", "", "","", "", "", "");
		            } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            //Resets the table to the new values
		            ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		    
		      }
		   }
		   
		   private class SearchButtonListener implements ActionListener
		   {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  	// Clears all the input boxes for update area
		    	  	orderUpdatePanel.clear();
		    	  	// Get the order information from the text fields.
		    	  	String menu = orderUpdatePanel.getMenu();
		            String quan = orderUpdatePanel.getQuan();
		            String month = orderUpdatePanel.getMonth();
		            String day = orderUpdatePanel.getDay();
		            String year = orderUpdatePanel.getYear();
		            String time = orderUpdatePanel.getTime();
		            String status = orderUpdatePanel.getStatus();
		            String total = orderUpdatePanel.getPrice();;
		            
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
		    	  orderUpdatePanel.clear();
		    	  
		    	  //We also want to clear the search criteria for what is showing up, so we create a
		    	  //resultset variable to hold a new blank search
		    	  ResultSet searchInfo = null;
		            
		            try {
		            	//Create an object to instantiate the Connection to the table
						orderTableManager getInfo = new orderTableManager();
						//This creates a blank search of the table
						searchInfo = orderTableManager.selectOrder("", "", "", "","", "", "", "");
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
		         System.exit(0);
		      }
		   }
		
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			updateOrder insertOrder = new updateOrder();
		}

}
