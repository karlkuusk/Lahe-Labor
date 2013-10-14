package ee.ut.math.tvt.LaheLabor;


import java.awt.FlowLayout;
import javax.swing.*;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import org.apache.log4j.Logger;
import java.awt.*;
import java.io.*;

public class IntroUI extends JFrame {
	
	private static final Logger log = Logger.getLogger(IntroUI.class.getName());
	private String build_number;
	private BufferedReader br;
	
	IntroUI(){
	 
	    this.setSize(434, 500); 
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	    JLabel content=new JLabel(new ImageIcon(getClass().getResource("/lahelabor.jpg")));
	    
	    content.setLayout(new FlowLayout());
	    
	    GridBagConstraints c = new GridBagConstraints();

	    
	    add(content);

		
		readBuildNumber();
		
	    JLabel label1 = new JLabel("Lahe Labor"); 
	    JLabel label2 = new JLabel("Rene Roost"); 
	    JLabel label3 = new JLabel("roost.rene@gmail.com"); 
	    JLabel label4 = new JLabel("Team members: Liisbet Jyrlau, Jonatan Raudsepp, Karl Kuusk, Rene Roost"); 
	    JLabel label5= new JLabel(build_number); 

	    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
	    content.add(label1);
	    content.add(label2);
	    content.add(label3);
	    content.add(label4);
	    content.add(label5);
	
		try {
	        UIManager.setLookAndFeel(new WindowsLookAndFeel());

	    } catch (UnsupportedLookAndFeelException e1) {
	        log.warn(e1.getMessage());
	    }
	    
	    this.setVisible(true);
	    
	 }
	

	 private void readBuildNumber(){
		try{
			br=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/version.properties")));
			String tmp;
			while((tmp=br.readLine())!=null){
				if(tmp.startsWith("build.number=")){
					build_number=tmp.split("build.number=")[1];
					break;
				}
			}	
			br.close();
		}
		catch(IOException e2){
			log.warn(e2.getMessage());
		}
	}
}
