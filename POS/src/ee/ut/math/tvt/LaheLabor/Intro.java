
package ee.ut.math.tvt.LaheLabor;
import ee.ut.math.tvt.LaheLabor.domain.controller.*;
import ee.ut.math.tvt.LaheLabor.domain.controller.impl.*;
import ee.ut.math.tvt.LaheLabor.ui.*;

import org.apache.log4j.Logger;

public class Intro {

	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";
	
	public static void main(String[] args) {
	
		final SalesDomainController domainController = new SalesDomainControllerImpl();
	
		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);
	
			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {
	
			IntroUI introUI = new IntroUI();
			introUI.setVisible(true);
			introUI.setAlwaysOnTop(true);
	
			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);
	
			introUI.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.setVisible(false);
		}
	}
}
