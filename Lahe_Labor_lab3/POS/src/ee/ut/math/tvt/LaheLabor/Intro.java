
package ee.ut.math.tvt.LaheLabor;


import java.awt.*;
import javax.swing.*;

public class Intro {
	  public static void main(String[] args) {
		    JFrame raam = new JFrame("JFrame"); // raami loomine
		    raam.setSize(300, 300); // 
		    raam.setLocation(100, 100); // 

		    Container content = raam.getContentPane(); // konteineri loomine
		    content.setLayout(new FlowLayout()); // paigutushalduri kasutamine

		    JLabel label1 = new JLabel("Lahe Labor"); //
		    JLabel label2 = new JLabel("Rene Roost"); //
		    JLabel label3 = new JLabel("roost.rene@gmail.com"); //
		    JLabel label4 = new JLabel("Liisbet Jürlau, Karl Kuusk, Jonatan Raudsepp,  Rene Roost"); //
		    
		    JLabel label5= new JLabel("1.0.0"); //

		          content.add(label1); // loodud elementide lisamine aknale
		          content.add(label2);
		          content.add(label3);
		          content.add(label4);
		          content.add(label5);
		// 
		         raam.setVisible(true); 
		  }
}
