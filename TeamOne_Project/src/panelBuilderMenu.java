

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
   The panelBuilder class builds a panel containing the 
   labels and text fields for inserting data into the user
   table of the testdbgui database.
*/

public class panelBuilderMenu extends JPanel
{
   private JTextField nameTextField;     
   private JTextField quanTextField;   
   private JTextField priceTextField;   

       //
   private JComboBox<String> itemComboField;
   
   
   public panelBuilderMenu(String thePrompt)
   {
      // Create labels and text fields
      // for the user data.
      
      JLabel namePrompt = new JLabel("Item Name");
      nameTextField = new JTextField(45);
      
      
      JLabel itemPrompt = new JLabel("Iventory Item");
      itemComboField = new JComboBox<String>();
      itemComboField.addItem("");
      try {
  		invPositionsManager getInv = new invPositionsManager();
  	    
  	    ResultSet invInfo = invPositionsManager.selectPositions();
  	    
  	    while (invInfo.next()) {
  	    	
  	    	String item = invInfo.getString("dbinvitem");
  	    	itemComboField.addItem(item);
  	    }
  	    
        } catch (SQLException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}   
      
      JLabel quanPrompt = new JLabel("Quantity Used");
      quanTextField = new JTextField(45);
      
      JLabel pricePrompt = new JLabel("Item Price");
      priceTextField = new JTextField(45);
      
      
      // Create a grid layout manager 
      // with 12 rows and 1 column.
      setLayout(new GridLayout(12, 1));   
      setBorder(BorderFactory.createTitledBorder(thePrompt));
      
      // Add the labels and text fields
      // to the panel.
      
      add(namePrompt);
      add(nameTextField);
      
      add(itemPrompt);
      add(itemComboField);
      
      add(quanPrompt);
      add(quanTextField);
      
      add(pricePrompt);
      add(priceTextField);
      

      

      
      
   }
   
   /**
   The setName method sets the 
   name entered by the user.
 */

public void setName(String name)
{
	nameTextField.setText(name);
}

   /**
   The setAddress method sets the 
   address entered by the user.
 */
	public void setQuan(String quan)
	{
		quanTextField.setText(quan);
	}     
	
	   /**
	   The setEmail method sets the 
	   email entered by the user.
	 */
	
	public void setPrice(String price)
	{
		priceTextField.setText(price);
	} 
	
	public void setItem(int item)
	{
		itemComboField.setSelectedIndex(item);
	} 

   /**
      The getName method returns the 
      name entered by the user.
      @return The name
    */
   
   public String getName()
   {
      return nameTextField.getText();
   }
   /**
      The getAddress method returns the 
      address entered by the user.
      @return The address
    */
   
   public String getQuan()
   {
      return quanTextField.getText();
   }

   /**
      The getEmail method returns the 
      email entered by the email.
      @return The email
    */
   
   public String getPrice()
   {
      return priceTextField.getText();
   }
   
   
   public String getItem()
   {
	   return itemComboField.getSelectedItem().toString();
   }


   
   /**
      The getHR method returns the 
      hourly rate entered by the user.
      @return the hourly rate
    */
   

   
            
   /**
      The clear method sets each of the 
      text fields to an empty string.
    */
   
   public void clear()
   {
      nameTextField.setText("");
      quanTextField.setText("");
      priceTextField.setText("");
      itemComboField.setSelectedIndex(0);
 
   }
}

