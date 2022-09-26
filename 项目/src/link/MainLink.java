package link;
import admin.*;
import User.*;
public class MainLink {
	MainLink(){
		adminlogin ad = new adminlogin();
		ad.setTitle("Admin Section");
		ad.setSize(800,700);
		ad.setLocation(100,50);
		ad.setVisible(true);
		
		userlogin ul = new userlogin();
		ul.setTitle("Admin Section");
		ul.setSize(800,800);
		ul.setLocation(100,50);
		ul.setVisible(true);
	}
	public static void main(String[] args) throws Exception{
		MainLink ml = new MainLink();
	}
}
