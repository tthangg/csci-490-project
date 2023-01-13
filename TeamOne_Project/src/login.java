/*
 This only meant to run once, I already run it
 before running it again please delete all tables in DoctorDB file
 to ensure data integrity, Thank you
 
 if want to run it again please delete all table in the access file thank you
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login  implements ActionListener{

	private static JLabel password, userName;
	private static JTextField userNameIn;
	private static JButton button;
	private static JPasswordField passwordInput;
	JFrame frame = new JFrame();
    public void Login() {
       
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	
    	
    	frame.setTitle("LOGIN PAGE");
    	frame.setLocation(new Point(500, 300));
    	frame.add(panel);
    	frame.setSize(new Dimension(400, 200));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	userName = new JLabel("Username");
    	userName.setBounds(100, 8, 70, 20);    	
    	
    	userNameIn = new JTextField();
    	userNameIn.setBounds(100, 27, 193, 28);
    	
    	password = new JLabel("Password");
    	password.setBounds(100, 55, 70, 20);
    	
    	passwordInput = new JPasswordField();
    	passwordInput.setBounds(100, 75, 193, 28);
    	
    	button = new JButton("Login");
    	button.setBounds(100, 110, 90, 25);
    	button.setForeground(Color.WHITE);
    	button.setBackground(Color.BLACK);
    	button.addActionListener(this);
    	
    	panel.add(userName);
    	panel.add(userNameIn);
    	panel.add(password);
    	panel.add(passwordInput);
    	panel.add(button);
    	
    	frame.setVisible(true);
    }
	
	public static void main(String[] args){
		login execute = new login();
		execute.Login();
	}
	@Override
	public void actionPerformed(ActionEvent e) {		

		if (userNameIn.getText().equals("admin") && passwordInput.getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "Login Successful");
			StartDatabase start = new StartDatabase();
			frame.dispose();
		}		
		else {
			JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
		}
			
	}

}
