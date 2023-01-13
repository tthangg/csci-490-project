

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

public class panelBuilderOrder3 extends JPanel
{
 
   private JComboBox statusComboField;
   
   public panelBuilderOrder3(String thePrompt)
   {

      
      JLabel statusPrompt = new JLabel("Status");
      String[] status = {"", "Active", "Complete"};
      statusComboField = new JComboBox(status);
      
   
      
      
      

      
      
      // Create a grid layout manager 
      // with 12 rows and 1 column.
      setLayout(new GridLayout(10, 1));   
      setBorder(BorderFactory.createTitledBorder(thePrompt));
      
      // Add the labels and text fields

      
      add(statusPrompt);
      add(statusComboField);
     


      
      
   }
   

	public void setStatus(String s){
		statusComboField.setSelectedItem(s);
	}



   public String getStatus()
  	{
	   return statusComboField.getSelectedItem().toString();
  	}

   
   public void clear()
   {
      statusComboField.setSelectedIndex(0);
   }
}

