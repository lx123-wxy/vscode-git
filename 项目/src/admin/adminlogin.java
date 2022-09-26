package admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class adminlogin extends JFrame implements ActionListener{
	JTabbedPane tabs;
	Add_Category add;
	View_Category view1;
	Edit_Category edit;
	JPanel addPanel,viewPanel,editPanel;
	JButton Add,Back,Login_Out,Load,Update;
	
	public adminlogin(){
		Login_Out = new JButton("Login Out");
		Login_Out.setBounds(500,0,120,20);
		this.add(Login_Out);
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		add = new Add_Category();
		view1 = new View_Category();
		edit = new Edit_Category();
		
		addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout());
		viewPanel = new JPanel();
		viewPanel.setLayout(new FlowLayout());
		editPanel = new JPanel();
		editPanel.setLayout(new FlowLayout());
		tabs.addTab("Add Category", add);
		tabs.addTab("View Category", view1);
		tabs.addTab("Edit Category", edit);
		this.add(tabs);
		Login_Out.addActionListener(this);
	}

	public static void main(String[] args) throws Exception{
		adminlogin ad = new adminlogin();
		ad.setTitle("Admin Section");
		ad.setSize(800,700);
		ad.setLocation(100,50);
		ad.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Login Out") {
			System.exit(0);
		}
	}
	
}

