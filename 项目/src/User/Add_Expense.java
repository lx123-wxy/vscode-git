package User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admin.Datepicker;
public class Add_Expense extends JPanel implements ActionListener{
	JButton Add,Back,Date_picker1u;
	JLabel Expense_Type,Date,Price,Remarka,Remarkb;
	JTextField Date1,Price1,Remark1,Remark2;
	JComboBox Expense_Type1;
	String type[] = {"China","French","USA","UK"};
	Add_Expense(){
		setLayout(null);
		Add = new JButton("Add");
		Back = new JButton("Back");
		Date_picker1u = new JButton("Date Picker");
		Add.setBounds(250, 650, 100, 20);
		Back.setBounds(500, 650, 100, 20);
		Date_picker1u.setBounds(550,200,150,20);
		this.add(Add);
		this.add(Back);
		this.add(Date_picker1u);
		
		Expense_Type = new JLabel("Expense Type");
		Expense_Type.setBounds(200,100,100,20);
		add(Expense_Type);
		
		Date = new JLabel("Date");
		Date.setBounds(200,200,100,20);
		add(Date);
		
		Price = new JLabel("Price");
		Price.setBounds(200,300,100,20);
		add(Price);
		
		Remarka = new JLabel("Remark category ID");
		Remarka.setBounds(150,400,150,20);
		add(Remarka);
		
		Remarkb = new JLabel("Remark user name");
		Remarkb.setBounds(150,500,150,20);
		add(Remarkb);
		
		Expense_Type1 = new JComboBox(type);
		Expense_Type1.setBounds(300,100,150,20);
		add(Expense_Type1);
		
		Date1 = new JTextField();
		Date1.setBounds(300,200,150,20);
		add(Date1);
		
		Price1 = new JTextField();
		Price1.setBounds(300,300,150,20);
		add(Price1);
		
		Remark1 = new JTextField();
		Remark1.setBounds(300,400,150,20);
		add(Remark1);
		
		Remark2 = new JTextField();
		Remark2.setBounds(300,500,150,20);
		add(Remark2);
		
		Add.addActionListener(this);
		Back.addActionListener(this);
		Expense_Type1.addActionListener(this);
		Date_picker1u.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Add") {
			if( Date1.getText().equals("")|| Price1.getText().equals("")|| Remark1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Date ,price or remark can't blank !!");
			}
			else {
				insertDate();
			}
		}
		if(e.getActionCommand()=="Back") {
			Date1.setText(null);
			Price1.setText(null);
			Remark1.setText(null);
			Remark2.setText(null);
			Expense_Type1.setSelectedItem("China");
		}
		if(e.getActionCommand()=="Date Picker") {
			Datepicker();
		}
		String st = Expense_Type1.getSelectedItem().toString();
		switch(st)
		{
			case ("China"):JLabel china = new JLabel("rmb");china.setLayout(null);china.setBounds(500,300,50,20);add(china);break;
			case ("French"):JLabel french = new JLabel("fl");french.setLayout(null);french.setBounds(500,300,50,20);add(french);break;
			case ("USA"):JLabel usa = new JLabel("my");usa.setLayout(null);usa.setBounds(500,300,50,20);add(usa);break;
			case ("UK"):JLabel uk = new JLabel("yb");uk.setBounds(500,300,50,20);add(uk);break;
			default : break;
		}
	}
	public void Datepicker() {
		JFrame f = new JFrame();
		Date1.setText(new Datepicker(f).setPickedDate());
	}
	public void insertDate() {
		String str1 = Remark1.getText();
		String str2 = Price1.getText();
		String str3 = Date1.getText();
		String str4 = Remark2.getText();
		String str5 = Expense_Type1.getSelectedItem().toString();
		try {
			Connection con = createConnection();
			String sql="insert into user(cat_id, price, expense_date, user_name, expense_type) values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, str1);
            pstmt.setString(2, str2);
            pstmt.setString(3, str3);
            pstmt.setString(4, str4);
            pstmt.setString(5, str5);
            int i=pstmt.executeUpdate();
			if(i!=0) {
				JOptionPane.showMessageDialog(this, "Expense Insert Successful");
			    }
			else {
				JOptionPane.showMessageDialog(this, "Expense Insert Failed");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	public Connection createConnection() {
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
	

}
