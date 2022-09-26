package codes;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
//Step-1: Implement ActionListener
public class SearchInsertUpdateDelete extends JFrame implements ActionListener {
    JLabel id, name, gender,country;
    JTextField tid, tname;
    JRadioButton male, female;
    ButtonGroup gengp;
    JComboBox jcountry;
    JButton search, insert, update,delete;
    String cname[]={"India","China","USA","France","UK", "Japan"};
    SearchInsertUpdateDelete(){
        super("Login Form");
        setLayout(null);
        setSize(400,400);
        setLocation(580,100);
         
        id=new JLabel("Enter Id");
        id.setBounds(50, 50, 100, 30);
        add(id);
         
        tid=new JTextField();
        tid.setBounds(150, 50, 50, 25);
        add(tid);
         
        name=new JLabel("Name");
        name.setBounds(50, 100, 100, 30);
        add(name);
         
        tname=new JTextField();
        tname.setBounds(150, 100, 150, 25);
        add(tname);
         
        gender=new JLabel("Gender");
        gender.setBounds(50, 150, 100, 30);
        add(gender);
         
        male = new JRadioButton("Male");
        male.setBounds(150, 150, 60, 30);
        add(male);
         
        female = new JRadioButton("Female");
        female.setBounds(220, 150, 80, 30);
        add(female);
         
        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);
         
        country=new JLabel("Country");
        country.setBounds(50, 200, 100, 30);
        add(country);
         
        jcountry=new JComboBox(cname);
        jcountry.setBounds(150, 200, 150, 25);
        add(jcountry);
         
        search=new JButton("Search");
        search.setBounds(210, 50, 90, 25);
        add(search);
         
        insert=new JButton("Insert");
        insert.setBounds(50, 270, 70, 30);
        add(insert);
         
        update=new JButton("Update");
        update.setBounds(135, 270, 80, 30);
        add(update);
         
        delete=new JButton("Delete");
        delete.setBounds(230, 270, 70, 30);
        add(delete);
         
        //Step-2: Register ActionListener with buttons
        search.addActionListener(this);
        insert.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
         
        setVisible(true);
    }
    public static void main(String[] args) {
        new SearchInsertUpdateDelete();
    }
    //Step-3: Override ActionListener Method
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Search") {
            searchData();
        }
        if(e.getActionCommand()=="Insert") {
            if(tname.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Name can't blank !!");
            }
            else {
                insertData();
            }
        }
        if(e.getActionCommand()=="Update") {
            updateData();
        }
        if(e.getActionCommand()=="Delete") {
            deleteData();
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
    //Search Method
    public void searchData() {
        int id=Integer.parseInt(tid.getText());
        try {
            Connection con=createConnection();
            String sql="select * from user where id='"+id+"'";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) {
                tname.setText(rs.getString("Name"));
                if(rs.getString("Gender").equals("Male"))
                    male.setSelected(true);
                else
                    female.setSelected(true);
                jcountry.setSelectedItem(rs.getString("Country"));  
            }
            else {
                JOptionPane.showMessageDialog(this, " Record not exists.");
                tname.setText("");gengp.clearSelection();
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
         
    }
    //Insert Method
    public void insertData() {
        String name=tname.getText();
        String gender=male.isSelected()?"Male":"Female";
        String country=jcountry.getSelectedItem().toString();
        try {
            Connection con=createConnection();
            String sql="insert into user(Name, Gender, Country) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, country);
            int i=pstmt.executeUpdate();
            if(i!=0) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
                }
            else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        }
        catch(Exception e) {    
            JOptionPane.showMessageDialog(this, e);
        }
    }
    //Update data
    public void updateData() {
        String name=tname.getText();
        String gender=male.isSelected()?"Male":"Female";
        String country=jcountry.getSelectedItem().toString();
        try {
            Connection con=createConnection();
            String query="update user set name=?, gender=?, country=? where name=?";
            PreparedStatement pstmt = con.prepareStatement(query);  
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, country);
            pstmt.setString(4, name);
            int i=pstmt.executeUpdate();
            if(i!=0) {
                JOptionPane.showMessageDialog(this, "Record Updated");
                }
            else {
                JOptionPane.showMessageDialog(this, "Record not found");
            }
        }
        catch(Exception e) {    
            System.out.println(e);
        }
    }
    //Delete data Method
    public void deleteData(){
        try{
            Connection con=createConnection();
            String sql="delete from user where name=? ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tname.getText());
            int i=pstmt.executeUpdate();
            if(i!=0){
                JOptionPane.showMessageDialog(this,"Record deleted successfully");  
                tname.setText("");gengp.clearSelection();
            }
            else {
                JOptionPane.showMessageDialog(this,"Record not exists"); 
            }
        }
        catch (Exception e){
            System.out.println(e);
        }   
    }
}
