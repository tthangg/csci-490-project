

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


public class searchInv extends JFrame {

	panelBuilderInv invInfoPanel;   // Panel for inv information
	JPanel buttonPanel;                    // Panel for buttons
	JPanel listPanel;          // A panel to hold the scroll pane
	String searchString = "";   
	JScrollPane scrollPane;    // A scroll pane to hold the list
	JTable ourTable;
	
	
	public searchInv()
	   {
	      // Set the window title.
	      setTitle("Select Inv");
	      
	      // Specify an action for the close button.
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	      // Create a panelBuilder object.
	      invInfoPanel = new panelBuilderInv("Search Inv");
	      
	      // Build the buttonPanel object.
	      buildButtonPanel();
	      
	      // Build the listPanel object.
	      buildListPanel();
	      
	      // create a BorderLayout manager.
	      setLayout(new BorderLayout());
	      
	      // Add the panels to the content pane.
	      add(invInfoPanel, BorderLayout.NORTH);
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
	         createTitledBorder("Inv Information"));
	         //Create object to access database.
	         invTableManager getInfo = new invTableManager();
	         
	         //Create a resultset to hold the blank search for when the page starts
	         ResultSet invInfo = invTableManager.selectInv("", "", "");
	         
	         //Uses the rs2XML code to create an object that will fill our jtable with the information 
		     ourTable = new JTable(DbUtils.resultSetToTableModel(invInfo));
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
	         // display a message to the inv.
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
	        // Get the inv information from the text fields.
	    	  	String item = invInfoPanel.getItem();
	            String quan = invInfoPanel.getQuan();
	            String type = invInfoPanel.getType();

	            //Create a result set variable to hold the info from the search
	            ResultSet searchInfo = null;
	            
	            try {
	            	//Create an object to instantiate the Connection to the table
					invTableManager getInfo = new invTableManager();
					//This creates a blank search of the table
					searchInfo = invTableManager.selectInv(item, quan, type);
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
	    	  invInfoPanel.clear();
	    	  
	    	  //We also want to clear the search criteria for what is showing up, so we create a
	    	  //resultset variable to hold a new blank search
	    	  ResultSet searchInfo = null;
	            
	            try {
	            	//Create an object to instantiate the Connection to the table
					invTableManager getInfo = new invTableManager();
					//This creates a blank search of the table
					searchInfo = invTableManager.selectInv("", "", "");
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
	         // Exit the invlication.
	    	  dispose();
	      }
	   }
	

}
