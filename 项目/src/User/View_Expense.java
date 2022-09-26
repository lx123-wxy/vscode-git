package User;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class View_Expense extends JPanel{
	DefaultTableModel model = new DefaultTableModel();
	JTable view = new JTable(model);
	public View_Expense() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		model.addColumn("ID");
		model.addColumn("Expense Type");
		model.addColumn("Date");
		model.addColumn("Price");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Expense?serverTimezone=UTC","root","lixu20020224");
			PreparedStatement pstm = con.prepareStatement("select record_id,expense_type,expense_date,price from expense");
			ResultSet Rs = pstm.executeQuery();
			while(Rs.next()) {
				model.addRow(new Object[] {Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4)});
			}
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
        }
		JScrollPane pg = new JScrollPane(view);
		add(pg);
		view.setSize(600,500);
//		this.pack();
		view.setLayout(null);
		view.setVisible(true);
}
}
