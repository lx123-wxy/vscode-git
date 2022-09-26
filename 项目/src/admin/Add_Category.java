package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add_Category extends JPanel implements ActionListener{
	JButton Add,Back,Date_picker;
	JLabel name,minimum_limit,maxmum_limit,date;
	JTextField name1,mini,max,date1;
	Add_Category(){
		setLayout(null);
		Add = new JButton("Add");
		Back = new JButton("Back");
		Date_picker = new JButton("Date Picker");
		Add.setBounds(250, 600, 100, 20);
		Back.setBounds(500, 600, 100, 20);
		Date_picker.setBounds(500,400,150,20);
		this.add(Add);
		this.add(Back);
		this.add(Date_picker);
		
		name = new JLabel("Name ");
		name.setBounds(200, 100, 100, 20);
		add(name);
		
		minimum_limit = new JLabel("Minimum Limit ");
		minimum_limit.setBounds(200, 200, 100, 20);
		add(minimum_limit);
		
		maxmum_limit = new JLabel("Maxmum Limit ");
		maxmum_limit.setBounds(200, 300, 100, 20);
		add(maxmum_limit);
		
		date = new JLabel("Date ");
		date.setBounds(200, 400, 100, 20);
		add(date);
		
		name1 = new JTextField();
		name1.setBounds(300,100,150,20);
		add(name1);
		
		mini = new JTextField();
		mini.setBounds(300,200,150,20);
		add(mini);
		
		max = new JTextField();
		max.setBounds(300,300,150,20);
		add(max);
		
		date1 = new JTextField();
		date1.setBounds(300,400,150,20);
		add(date1);
		
		Add.addActionListener(this);
		Back.addActionListener(this);
		Date_picker.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Add") {
			if(name1.getText().equals("") || mini.getText().equals("")|| max.getText().equals("")|| date1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Name ,minimum limit ,maxmum limit or date can't blank !!");
			}
			else {
				insertDate();
			}
		}
		if(e.getActionCommand()=="Back") {
			name1.setText(null);
			mini.setText(null);
			max.setText(null);
			date1.setText(null);
		}
		if(e.getActionCommand()=="Date Picker") {
			Datepicker();
		}
	}
	public void Datepicker() {
		JFrame f = new JFrame();
		date1.setText(new Datepicker(f).setPickedDate());
	}
	public static Connection createConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Expense?serverTimezone=UTC", "root","lixu20020224");
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return con;
	}

	public void insertDate() {
		try {
			Connection con = createConnection();
			String sql = "insert into category (cat_name , min_limit , max_limit , cat_date) values ('"+name1.getText()+"','"+mini.getText()+"','"+max.getText()+"','"+date1.getText()+"')";
			Statement stmt = con.createStatement();	
			int i=stmt.executeUpdate(sql);
			if(i!=0) {
				JOptionPane.showMessageDialog(this, "Date Insert Successful");
			    }
			else {
				JOptionPane.showMessageDialog(this, "Date Insert Failed");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	}
