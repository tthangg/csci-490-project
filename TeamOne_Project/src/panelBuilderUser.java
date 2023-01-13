

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
   The panelBuilder class builds a panel containing the 
   labels and text fields for inserting data into the user
   table of the testdbgui database.
*/

public class panelBuilderUser extends JPanel
{
   private JTextField nameTextField;      // name
   private JTextField addressTextField;   // address
   private JTextField emailTextField;   
   private JTextField phoneTextField;// email
       //
   private JComboBox<String> adminComboField;
   
   
   public panelBuilderUser(String thePrompt)
   {
      // Create labels and text fields
      // for the user data.
      
      JLabel namePrompt = new JLabel("Name");
      nameTextField = new JTextField(45);
      
      
      
      JLabel emailPrompt = new JLabel("Email");
      emailTextField = new JTextField(45);
      
      JLabel phonePrompt = new JLabel("Phone");
      phoneTextField = new JTextField(45);
      
      JLabel addressPrompt = new JLabel("Address");
      addressTextField = new JTextField(55);

      
      JLabel adminPrompt = new JLabel("Admin");
      String[] options= {"", "No", "Yes"};
      adminComboField = new JComboBox(options);
      
      
      
      

      
      
      // Create a grid layout manager 
      // with 12 rows and 1 column.
      setLayout(new GridLayout(12, 1));   
      setBorder(BorderFactory.createTitledBorder(thePrompt));
      
      // Add the labels and text fields
      // to the panel.
      
      add(namePrompt);
      add(nameTextField);
      
     
      
      add(emailPrompt);
      add(emailTextField);
      
      add(phonePrompt);
      add(phoneTextField);
      add(addressPrompt);
      add(addressTextField);
      add(adminPrompt);
      add(adminComboField);
      

      
      
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
	public void setAddress(String address)
	{
		addressTextField.setText(address);
	}     
	
	   /**
	   The setEmail method sets the 
	   email entered by the user.
	 */
	
	public void setEmail(String email)
	{
		emailTextField.setText(email);
	} 
	
	public void setPhone(String email)
	{
		phoneTextField.setText(email);
	} 
	
 
	   /**
	   The setAdmin method sets the 
	   rate entered by the user.
	 */
	public void setAdmin(String admin)
	{
		adminComboField.getModel().setSelectedItem(admin);
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
   
   public String getAddress()
   {
      return addressTextField.getText();
   }

   /**
      The getEmail method returns the 
      email entered by the email.
      @return The email
    */
   
   public String getEmail()
   {
      return emailTextField.getText();
   }
   
   public String getPhone()
   {
      return phoneTextField.getText();
   }
   
   public String getAdmin()
   {
	   return adminComboField.getSelectedItem().toString();
   }


   

   
   public void clear()
   {
      nameTextField.setText("");
      addressTextField.setText("");
      emailTextField.setText("");
      phoneTextField.setText("");
      adminComboField.setSelectedIndex(0);
 
   }
}

