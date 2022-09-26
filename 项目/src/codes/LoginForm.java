package codes;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//Step-1: Implements ActionListener Interface
public class LoginForm extends JFrame implements ActionListener{
	JLabel title, name, password;
	JTextField tname,tpassword;
	JButton login, reset;
	LoginForm(){
		super("Login Form");
		setLayout(null);
		setBounds(850, 100, 400, 300);
		
		title=new JLabel("Login Form");
		title.setBounds(150, 10, 150, 30);
		add(title);
		
		name=new JLabel("Name");
		name.setBounds(50, 50, 100, 20);
		add(name);
		
		tname=new JTextField();
		tname.setBounds(150, 50, 150, 20);
		add(tname);
		
		password=new JLabel("Password");
		password.setBounds(50, 80, 100, 20);
		add(password);
		
		tpassword=new JTextField();
		tpassword.setBounds(150, 80, 150, 20);
		add(tpassword);
		
		login=new JButton("Login");
		login.setBounds(150, 110, 70, 25);
		add(login);
		
		reset=new JButton("Reset");
		reset.setBounds(230, 110, 70, 25);
		add(reset);	
		
		//Step-2: Register Listener
		login.addActionListener(this);
		reset.addActionListener(this);
	}
	public static void main(String[] args) {
		LoginForm obj=new LoginForm();
		obj.setVisible(true);
	}
	//Step-3: Override methods. This is also known as EventHandler
	@Override
	public void actionPerformed(ActionEvent e) {
		String id=tname.getText();
		String password=tpassword.getText();
		if(e.getActionCommand()=="Login") {
			validate(id,password);	
		}
		if(e.getActionCommand()=="Reset") {
			tname.setText("");
			tpassword.setText("");
		}	
	}
	//Connection method
		public static Connection createConnection() {
			Connection con=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/niit?serverTimezone=UTC", "root","lixu20020224");
			}
			catch(Exception e) {
				System.out.print(e);
			}
			return con;
		}
		//Validate id and password
		public void validate(String id, String pwd) {
			try {
				Connection con=createConnection();
				String sql="select * from login where id='"+id+"' and password='"+pwd+"'";
				Statement stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()) {
					this.dispose();
					new SearchInsertUpdateDelete();
				}
				else {
					JOptionPane.showMessageDialog(this, "Login Failed...");
					tname.setText("");tpassword.setText("");
					
				}
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}
}
