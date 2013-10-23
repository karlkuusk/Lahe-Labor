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
		readBuildNumber();
	    this.setSize(800, 500); 
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.getContentPane().setBackground(Color.WHITE);
	    JLabel content=new JLabel(new ImageIcon(getClass().getResource("/lahelabor.jpg")));
	    this.setLayout(new FlowLayout());
	    content.setLayout(new FlowLayout());
	  
	    JPanel panel=new JPanel();
	    panel.setLayout(new GridLayout(4,0));
	    panel.setBackground(Color.WHITE);
		
	    JLabel label1 = new JLabel("Lahe Labor"); 
	    label1.setFont(new Font("Arial", Font.PLAIN, 24));
	    JLabel label2 = new JLabel("<html>Team leader: Rene Roost<br>mail: rene.roost@gmail.com</html>");
	    JLabel label3 = new JLabel("<html>Team members:<br> Liisbet Jyrlau<br> Jonatan Raudsepp<br> Karl Kuusk<br> Rene Roost"); 
	    JLabel label4= new JLabel("build number: "+build_number); 
	    
	    panel.setSize(300,300);
	    
	   
	    panel.add(label1);
	    panel.add(label2);
	    panel.add(label3);
	    panel.add(label4);
	    
	    this.add(panel);
	    this.add(content);
	    
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	    
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
