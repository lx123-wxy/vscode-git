package User;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class userlogin extends JFrame implements ActionListener{
	JTabbedPane tabs1;
	Add_Expense add1;
	View_Expense view1;
	JPanel addPanel1,viewPanel1;
	JButton Add,Back,Login_Out1;
	
	public userlogin(){
		Login_Out1 = new JButton("Login Out");
		Login_Out1.setBounds(500,0,120,20);
		this.add(Login_Out1);
		tabs1 = new JTabbedPane(JTabbedPane.TOP);
		add1 = new Add_Expense();
		view1 = new View_Expense();
		
		addPanel1 = new JPanel();
		addPanel1.setLayout(new FlowLayout());
		viewPanel1 = new JPanel();
		viewPanel1.setLayout(new FlowLayout());
		tabs1.addTab("Add Expense", add1);
		tabs1.addTab("View Expense", view1);
		this.add(tabs1);
		Login_Out1.addActionListener(this);
	}
	public static void main(String[] args) {
		userlogin ul = new userlogin();
		ul.setTitle("Admin Section");
		ul.setSize(800,800);
		ul.setLocation(100,50);
		ul.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Login Out") {
			this.dispose();
		}
	}

}
