package ee.ut.math.tvt.LaheLabor;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class IntroUI extends JFrame {
	 IntroUI(){
	 // raami loomine
	 
	    this.setSize(300, 300); // 
	    this.setLocation(100, 100); // 

	    Container content = this.getContentPane(); // konteineri loomine
	    content.setLayout(new FlowLayout()); // paigutushalduri kasutamine

	    JLabel label1 = new JLabel("Lahe Labor"); //
	    JLabel label2 = new JLabel("Rene Roost"); //
	    JLabel label3 = new JLabel("roost.rene@gmail.com"); //
	    JLabel label4 = new JLabel("Lisbet, Jonatan, Karl, Rene"); //
	    
	    JLabel label5= new JLabel("1.0.0"); //

	          content.add(label1); // loodud elementide lisamine aknale
	          content.add(label2);
	          content.add(label3);
	          content.add(label4);
	          content.add(label5);
	// 
	          this.setVisible(true); 
	 }
}
