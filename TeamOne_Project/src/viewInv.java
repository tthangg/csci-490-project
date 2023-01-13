

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


public class viewInv extends JFrame {

	   // Panel for inv information
                    // Panel for buttons
	JPanel listPanel;          // A panel to hold the scroll pane
	String searchString = "";   
	JScrollPane scrollPane;    // A scroll pane to hold the list
	JTable ourTable;
	
	
	public viewInv()
	   {
	      // Set the window title.
	      setTitle("Select Inv");
	      
	      // Specify an action for the close button.
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	  
	      
	      // Build the buttonPanel object.
	   
	      
	      // Build the listPanel object.
	      buildListPanel();
	      
	      // create a BorderLayout manager.
	      setLayout(new BorderLayout());
	      
	      // Add the panels to the content pane.
	      add(listPanel, BorderLayout.CENTER);
	      
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
	         createTitledBorder("Inv Level Information"));
	         //Create object to access database.
	         invTableManager getInfo = new invTableManager();
	         
	         //Create a resultset to hold the blank search for when the page starts
	         ResultSet invInfo = invTableManager.selectInv2();
	         
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
	  

	   
	   private class ExitButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	         // Exit the invlication.
	    	  dispose();
	      }
	   }
	

}
