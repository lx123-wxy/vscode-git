package admin;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class View_Category extends JPanel{
	Connection con = null;
	DefaultTableModel model = new DefaultTableModel();
	JTable view = new JTable(model);
	public View_Category() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Date");
		model.addColumn("Min value");
		model.addColumn("Max value");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("2");
			Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Expense?serverTimezone=UTC", "root", "lixu20020224");
			System.out.println("3");
			PreparedStatement pstm = con.prepareStatement("select * from category");
			ResultSet Rs = pstm.executeQuery();
			while(Rs.next()) {
				model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4),Rs.getString(5)});
			}
			System.out.println(Rs.getString(2));
			System.out.println("1");
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
        }
		JScrollPane pg = new JScrollPane(view);
		add(pg);
		setSize(700, 500);
//		model.setDataVector(null, getComponentListeners());
//		this.pack();
		setVisible(true);
	}
//	public static Connection createConnection() {
//		Connection con=null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con=DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/Expense?serverTimezone=UTC", "root","lixu20020224");
//		}
//		catch(Exception e) {
//			System.out.print(e);
//		}
//		return con;
//	}
		
}
