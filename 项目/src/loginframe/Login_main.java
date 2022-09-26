package loginframe;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.*;

public class Login_main extends JFrame{
	JLabel you_ , click_;
	JComboBox you_are,click_here_for;
	String you[] = {"Admin","User"};
	String click[] = {"Login","Register"};
	Login_main(){
		you_are = new JComboBox(you);
		you_are.setBounds(500,300,150,30);
		add(you_are);
		
		click_here_for = new JComboBox(click);
		click_here_for.setBounds(500,400,150,30);
		add(click_here_for);
		
		you_ = new JLabel("You Are");
		you_.setBounds(500,250,150,30);
		add(you_);
		
		click_ = new JLabel("Click Here For");
		click_.setBounds(500,350,200,30);
		add(click_);
	}
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(800,700);
		jf.setFont(new Font("System", Font.PLAIN, 14));
		Font f = jf.getFont();
		FontMetrics fm = jf.getFontMetrics(f);
		int x = fm.stringWidth("ADMIN PANEL");
		int y = fm.stringWidth(" ");
		int z = jf.getWidth()/2 - (x/2);
		int w = z/y;
		String pad ="";
		pad = String.format("%"+w+"s", pad);
		jf.setTitle(pad+"ADMIN PANEL");
		jf.setLocation(200, 80);
		jf.setVisible(true);
	}

}
