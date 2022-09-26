package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Edit_Category extends JPanel implements ActionListener{
	JButton Add1,Back1,Load,Date_picker1;
	JLabel name,minimum_limit,maxmum_limit,date,ID1;
	JTextField ID,name1,mini,max,date1e;
	Edit_Category(){
		setLayout(null);
		Add1 = new JButton("Add");
		Back1 = new JButton("Back");
		Load = new JButton("Load");
		Date_picker1 = new JButton("Date Picker");
		Add1.setBounds(250, 600, 100, 20);
		Back1.setBounds(500, 600, 100, 20);
		Load.setBounds(520,100,100,20);
		Date_picker1.setBounds(520,500,150,20);
		this.add(Load);
		this.add(Add1);
		this.add(Back1);
		this.add(Date_picker1);
		
		ID1 = new JLabel("ID ");
		ID1.setBounds(200, 100, 100, 20);
		add(ID1);
		
		name = new JLabel("Name ");
		name.setBounds(200, 200, 100, 20);
		add(name);
		
		minimum_limit = new JLabel("Minimum Limit ");
		minimum_limit.setBounds(200, 300, 100, 20);
		add(minimum_limit);
		
		maxmum_limit = new JLabel("Name ");
		maxmum_limit.setBounds(200, 400, 100, 20);
		add(maxmum_limit);
		
		date = new JLabel("Name ");
		date.setBounds(200, 500, 100, 20);
		add(date);
		
		name1 = new JTextField();
		name1.setBounds(300,200,150,20);
		add(name1);
		
		mini = new JTextField();
		mini.setBounds(300,300,150,20);
		add(mini);
		
		max = new JTextField();
		max.setBounds(300,400,150,20);
		add(max);
		
		date1e = new JTextField();
		date1e.setBounds(300,500,150,20);
		add(date1e);
		
		ID = new JTextField();
		ID.setBounds(300,100,150,20);
		add(ID);
		
		Add1.addActionListener(this);
		Back1.addActionListener(this);
		Load.addActionListener(this);
		Date_picker1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Load") {
			int id=Integer.parseInt(ID.getText());
	        try {
	            Connection con=createConnection();
	            String sql="select * from category where cat_id='"+id+"'";
	            Statement stmt = con.createStatement();
	            ResultSet rs=stmt.executeQuery(sql);
	            if(rs.next()) {
	                name1.setText(rs.getString("cat_name")); 
	                mini.setText(rs.getString("min_limit"));
	                max.setText(rs.getString("max_limit"));
	                date1e.setText(rs.getString("cat_date"));
	            }
	            else {
	                JOptionPane.showMessageDialog(this, " Record not exists.");
	            }
	        }
	        catch(Exception a) {
	            JOptionPane.showMessageDialog(this, e);
	        }
		}
		if(e.getActionCommand()=="Add") {
			if(name1.getText().equals("") || mini.getText().equals("")|| max.getText().equals("")|| date1e.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Name ,minimum limit ,maxmum limit or date can't blank !!");
			}
			else {
				UpDate();
			}
		}
		if(e.getActionCommand()=="Back") {
			name1.setText(null);
			mini.setText(null);
			max.setText(null);
			date1e.setText(null);
		}
		if(e.getActionCommand()=="Date Picker") {
			Datepicker();
		}
	}
	public void Datepicker() {
		JFrame f1 = new JFrame();
		date1e.setText(new Datepicker(f1).setPickedDate());
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

	public void UpDate() {
		String ID1 = ID.getText();
		String name2 = name1.getText();
		String mini1 = mini.getText();
		String max1 = max.getText();
		String date2 = date1e.getText();
		try {
			Connection con = createConnection();
			String query="update category set cat_name=?, min_limit=?, max_limit=?, cat_date=? where cat_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);  
            pstmt.setString(1, name2);
            pstmt.setString(2, mini1);
            pstmt.setString(3, max1);
            pstmt.setString(4, date2);
            pstmt.setString(5, ID1);
			int i=pstmt.executeUpdate();
			if(i!=0) {
				JOptionPane.showMessageDialog(this, "Date Update Successful");
			    }
			else {
				JOptionPane.showMessageDialog(this, "Date Update Failed");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

}
