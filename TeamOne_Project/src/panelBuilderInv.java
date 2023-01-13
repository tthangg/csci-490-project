

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
   The panelBuilder class builds a panel containing the 
   labels and text fields for inserting data into the user
   table of the testdbgui database.
*/

public class panelBuilderInv extends JPanel
{
   private JTextField itemTextField;      // name
   private JTextField quanTextField;   // address
   private JTextField typeTextField;   

   
   public panelBuilderInv(String thePrompt)
   {
      // Create labels and text fields
      // for the user data.
      
      JLabel itemPrompt = new JLabel("Item");
      itemTextField = new JTextField(45);
      
      JLabel quanPrompt = new JLabel("Quantity");
      quanTextField = new JTextField(55);
      
      JLabel typePrompt = new JLabel("type");
      typeTextField = new JTextField(45);
      

      
      
      

      
      
      // Create a grid layout manager 
      // with 12 rows and 1 column.
      setLayout(new GridLayout(12, 1));   
      setBorder(BorderFactory.createTitledBorder(thePrompt));
      
      // Add the labels and text fields
      // to the panel.
      
      add(itemPrompt);
      add(itemTextField);
      
      add(quanPrompt);
      add(quanTextField);
      
      add(typePrompt);
      add(typeTextField);


      
      
   }
   
   /**
   The setName method sets the 
   name entered by the user.
 */

public void setItem(String item)
{
	itemTextField.setText(item);
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
	
	public void setType(String type)
	{
		typeTextField.setText(type);
	} 
	


   /**
      The getName method returns the 
      name entered by the user.
      @return The name
    */
   
   public String getItem()
   {
      return itemTextField.getText();
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
   
   public String getType()
   {
      return typeTextField.getText();
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
      itemTextField.setText("");
      quanTextField.setText("");
      typeTextField.setText("");

   }
}

