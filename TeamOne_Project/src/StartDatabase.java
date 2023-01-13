

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartDatabase extends JFrame {

	JPanel buttonPanel;                    // Panel for buttons
	
	
	public StartDatabase()
	   {
	      // Set the window title.
	      setTitle("Select Option");
	      
	      // Specify an action for the close button.
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      buildButtonPanel();
	      
	      // create a BorderLayout manager.
	      setLayout(new BorderLayout());
	      
	      // Add the panels to the content pane.
	      add(buttonPanel, BorderLayout.NORTH);
	      
	      // Pack and display the window.
	     
	      setSize(700, 700);
	      setVisible(true);
	   }
	   
	   
	
	   private void buildButtonPanel()
	   {
	      // Create a panel for the buttons.
	      buttonPanel = new JPanel();
	      
	      // Create a Search Users button and add an action listener.
	      JButton searchUserButton = new JButton("Search Users");
	      searchUserButton.addActionListener(new SearchUserButtonListener());
	      
	      // Create an Insert Users button and add an action listener.
	      JButton insertUserButton = new JButton("Insert User");
	      insertUserButton.addActionListener(new InsertUserButtonListener());
	      
	      // Create an Update Users button and add an action listener.
	      JButton updateUserButton = new JButton("Update Users");
	      updateUserButton.addActionListener(new UpdateUserButtonListener());
	      
	      

		  // Create an Search Positions button.
	      JButton searchOrderButton = new JButton("Search Orders");
	      searchOrderButton.addActionListener(new SearchOrderButtonListener());
	      
	      // Create an Insert Positions button.
	      JButton insertOrderButton = new JButton("Insert Orders");
	      insertOrderButton.addActionListener(new InsertOrderButtonListener());
	      
	      // Create an Update Positions button.
	      JButton updateOrderButton = new JButton("Update Orders");
	      updateOrderButton.addActionListener(new UpdateOrderButtonListener());
	      /*
	      JButton searchMenuButton = new JButton("Search Menus");
	      searchMenuButton.addActionListener(new SearchMenuButtonListener());
	      
	      // Create an Insert Positions button.
	      JButton insertMenuButton = new JButton("Insert Menus");
	      insertMenuButton.addActionListener(new InsertMenuButtonListener());
	      
	      // Create an Update Positions button.
	      JButton updateMenuButton = new JButton("Update Menus");
	      updateMenuButton.addActionListener(new UpdateMenuButtonListener());
	      */
	      JButton searchInvButton = new JButton("Search Inventory ");
	      searchInvButton.addActionListener(new SearchInvButtonListener());
	      
	      JButton insertInvButton = new JButton("Insert Inventory");
	      insertInvButton.addActionListener(new InsertInvButtonListener());
	      
	      JButton updateInvButton = new JButton("Update Inventory");
	      updateInvButton.addActionListener(new UpdateInvButtonListener());
	      
	      JButton viewStatusButton = new JButton("View Order Status");
	      viewStatusButton.addActionListener(new ViewStatusButtonListener());
	      
	      JButton reportInvButton = new JButton("Report Inventory Level");
	      reportInvButton.addActionListener(new ReportInvButtonListener());
	     
	      buttonPanel.setLayout(new GridLayout(6, 3)); 
	      // Add the buttons to the panel.
	      buttonPanel.add(searchUserButton);
	      buttonPanel.add(insertUserButton);
	      buttonPanel.add(updateUserButton);
	      
	      buttonPanel.add(searchOrderButton);
	      buttonPanel.add(insertOrderButton);
	      buttonPanel.add(updateOrderButton);
	      /*
	      buttonPanel.add(searchMenuButton);
	      buttonPanel.add(insertMenuButton);
	      buttonPanel.add(updateMenuButton);
	      */
	      buttonPanel.add(searchInvButton);
	      buttonPanel.add(insertInvButton);
	      buttonPanel.add(updateInvButton);
	      buttonPanel.add(viewStatusButton);
	      buttonPanel.add(reportInvButton);
	   }
	   
	   /**
	    Private inner class that handles Search User button events.
	   */

	   private class SearchUserButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	          searchUser SU = new searchUser();
	      }
	   }
	   
	     /**
	      Private inner class that handles Insert User button events.
	    */
	   
	   private class InsertUserButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 insertUser IU = new insertUser();
	      }
	   }
	   
	   /**
	      Private inner class that handles Update User button events.
	    */
	   
	   private class UpdateUserButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 updateUser UU = new updateUser();
	      }
	   }
	   private class SearchPermissionButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	          
	      }
	   }
	   
	     /**
	      Private inner class that handles Insert User button events.
	    */
	   
	   private class InsertPermissionButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 
	      }
	   }
	   
	   /**
	      Private inner class that handles Update User button events.
	    */
	   
	   private class UpdatePermissionButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 
	      }
	   }
	   
	   /**
	      Private inner class that handles Search Position button events.
	    */
	   
	   private class SearchOrderButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 searchOrder SO = new searchOrder();
	      }
	   }
	
	  /**
	      Private inner class that handles Insert Position button events.
	    */
	   
	   private class InsertOrderButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 insertOrder IO = new insertOrder();
	      }
	   }
	
	  /**
	      Private inner class that handles Update Position button events.
	    */
	   
	   private class UpdateOrderButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	updateOrder UO = new updateOrder();
	      }
	   }
	   
	   private class SearchMenuButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 searchMenu SM = new searchMenu();
	      }
	   }
	
	  /**
	      Private inner class that handles Insert Position button events.
	    */
	   
	   private class InsertMenuButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 insertMenu IM = new insertMenu();
	      }
	   }
	
	  /**
	      Private inner class that handles Update Position button events.
	    */
	   
	   private class UpdateMenuButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	updateMenu UM = new updateMenu();
	      }
	   }
	   
	   
	   private class SearchInvButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	          searchInv SI = new searchInv();
	      }
	   }
	   
	   private class InsertInvButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  insertInv II = new insertInv();
	      }
	   }
	   
	   private class UpdateInvButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 updateInv UI = new updateInv();
	      }
	   }
	   
	   private class ViewStatusButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 viewOrder VO = new viewOrder();
	      }
	   }
	   
	   private class ReportInvButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 viewInv VI = new viewInv();
	      }
	   }
	
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		StartDatabase createMenu = new StartDatabase();
	}

}
