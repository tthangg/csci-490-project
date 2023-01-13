
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class insertOrder extends JFrame{
	  
	
		panelBuilderOrder orderInfoPanel;   // Panel for emp information
		JPanel buttonPanel;                    // Panel for buttons	   
		
		   public insertOrder()
		   {
		      // Set the window title.
		      setTitle("Add Order");
		      
		      // Specify an action for the close button.
		      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      
		      // Create a panelBuilder object.
		      orderInfoPanel = new panelBuilderOrder("Insert Employee");
		      
		      // Build the buttonPanel object.
		      buildButtonPanel();
		      
		      // create a BorderLayout manager.
		      setLayout(new BorderLayout());
		      
		      // Add the panels to the content pane.
		      add(orderInfoPanel, BorderLayout.CENTER);
		      add(buttonPanel, BorderLayout.SOUTH);
		      
		      // Pack and display the window.
		      pack();
		      setVisible(true);
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
		         try
		         {
		            // Get the emp information from the text fields.
		            String menu = orderInfoPanel.getMenu();
		            String quan = orderInfoPanel.getQuan();
		            String month = orderInfoPanel.getMonth();
		            String day = orderInfoPanel.getDay();
		            String year = orderInfoPanel.getYear();
		            String time = orderInfoPanel.getTime();
		            String status = orderInfoPanel.getStatus();
		            
		            // Create a empTableManager object.
		            orderTableManager utManager = new orderTableManager();
		            
		            // Insert the record.
		            utManager.insert(menu, quan, month, day, year, time, status);
		            
		            // Clear the text fields.
		            orderInfoPanel.clear();
		            
		            // Let the emp know the record was added.
		            JOptionPane.showMessageDialog(null, "Record Added");
		         }
		         catch (SQLException ex)
		         {
		            ex.printStackTrace();
		            System.exit(0);
		         }
		      }
		   }
		   
		   /**
		      Private inner class that handles Exit button events.
		    */
		   
		   private class ClearButtonListener implements ActionListener
		   {
		      public void actionPerformed(ActionEvent e)
		      {
		         // Exit the application.
		    	  orderInfoPanel.clear();
		      }
		   }
		   
		   /**
		      Private inner class that handles Exit button events.
		    */
		   
		   private class ExitButtonListener implements ActionListener
		   {
		      public void actionPerformed(ActionEvent e)
		      {
		         // Exit the application.
		    	  dispose();
		      }
		   }
		
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			insertOrder insertOrder = new insertOrder();
		}

}
