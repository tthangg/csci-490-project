

import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

/**
   The panelBuilder class builds a panel containing the 
   labels and text fields for inserting data into the user
   table of the testdbgui database.
*/

public class panelBuilderOrder2 extends JPanel
{
   private JTextField quanTextField;      // name
   private JTextField priceTextField;   // address
   private JTextField timeTextField;   
   private JComboBox monthComboField;
   private JComboBox statusComboField;
   private JComboBox<String> dayComboField;
   private JComboBox<String> yearComboField;
   private JComboBox<String> menuComboField; 
   
   public panelBuilderOrder2(String thePrompt)
   {
      // Create labels and text fields
      // for the user data.
      
      JLabel menuPrompt = new JLabel("Order Item");
      menuComboField = new JComboBox<String>();
      menuComboField.addItem("");
      try {
  		menuPositionsManager getEmp = new menuPositionsManager();
  	    
  	    ResultSet menuInfo = menuPositionsManager.selectMenu();
  	    
  	    while (menuInfo.next()) {
  	    	
  	    	String menuName = menuInfo.getString("dbmenuname");
  	    	menuComboField.addItem(menuName);
  	    }
  	    
        } catch (SQLException e) {
  		// TODO Auto-generated catch block
        }
	   
      JLabel quanPrompt = new JLabel("Quantity");
      quanTextField = new JTextField(45);
      
      JLabel monthPrompt = new JLabel("Month");
      String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
      monthComboField = new JComboBox(months);
   
      
      
      
      JLabel dayPrompt = new JLabel("Day");
      dayComboField = new JComboBox<String>();
      dayComboField.addItem("");
      for(int i = 1; i<32;i++){    	  
      dayComboField.addItem(String.valueOf(i));
      }
      
      JLabel yearPrompt = new JLabel("Year");
      yearComboField = new JComboBox<String>();  
      yearComboField.addItem("");
      int year = Year.now().getValue();
      for(int i = 0; i<11;i++){
    	  int next = year + i;
    	  yearComboField.addItem(String.valueOf(next));
      }
      JLabel timePrompt = new JLabel("Time");
      timeTextField = new JTextField(55);  
	   
      
      JLabel statusPrompt = new JLabel("Status");
      String[] status = {"", "Active", "Complete"};
      statusComboField = new JComboBox(status);
      
      JLabel pricePrompt = new JLabel("Price");
      priceTextField = new JTextField(45);
      
 

      
      
      

      
      
      // Create a grid layout manager 
      // with 12 rows and 1 column.
      setLayout(new GridLayout(20, 1));   
      setBorder(BorderFactory.createTitledBorder(thePrompt));
      
      // Add the labels and text fields
      // to the panel.
      add(menuPrompt);
      add(menuComboField);
      
      add(quanPrompt);
      add(quanTextField);
      
      add(monthPrompt);
      add(monthComboField);
      
      add(dayPrompt);
      add(dayComboField);
      
      add(yearPrompt);
      add(yearComboField);
      
      add(timePrompt);
      add(timeTextField);
      
      add(statusPrompt);
      add(statusComboField);
     
      add(pricePrompt);
      add(priceTextField);

      
      
   }
   
   /**
   The setName method sets the 
   name entered by the user.
 */
	public void setMenu(int menu)
	{
		menuComboField.setSelectedIndex(menu);
	} 
	public void setQuan(String quan)
	{
		quanTextField.setText(quan);
	}
	
	public void setMonth(String m)
	{
		monthComboField.getModel().setSelectedItem(m);
	} 
	public void setDay(String d)
	{
		dayComboField.setSelectedItem(d);
	} 
	public void setYear(String y)
	{
		yearComboField.setSelectedItem(y);
	} 
	public void setTime(String t)
	{
		timeTextField.setText(t);
	} 

	public void setStatus(String s){
		statusComboField.getModel().setSelectedItem(s);
	}
	public void setPrice(String p)
	{
		priceTextField.setText(p);
	} 


   /**
      The getName method returns the 
      name entered by the user.
      @return The name
    */
	public String getMenu()
	{
		return menuComboField.getSelectedItem().toString();
	}
   public String getQuan()
   {
      return quanTextField.getText();
   }
   public String getMonth()
   	{
	   	return monthComboField.getSelectedItem().toString();
   	}
 
   public String getDay()
   	{
	   	return dayComboField.getSelectedItem().toString();
   	}
   
   public String getYear()
   	{
	   return yearComboField.getSelectedItem().toString();
   	}
   
   public String getTime()
   {
      return timeTextField.getText();
   }
   public String getStatus()
  	{
	   return statusComboField.getSelectedItem().toString();
  	}
   public String getPrice()
   {
      return priceTextField.getText();
   }

   
   public void clear()
   {
      ;
      menuComboField.setSelectedIndex(0);
      quanTextField.setText("");
      monthComboField.setSelectedIndex(0);
      dayComboField.setSelectedIndex(0);
      yearComboField.setSelectedIndex(0);
      timeTextField.setText("");
      statusComboField.setSelectedIndex(0);
      priceTextField.setText("");
   }
}

