package ee.ut.math.tvt.lahelabor.ui;
import ee.ut.math.tvt.lahelabor.domain.controller.SalesDomainController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
class DbWindowAdapter extends WindowAdapter{
	final SalesDomainController dc;
	DbWindowAdapter(SalesDomainController dc){
		this.dc=dc;
	}
	public void windowClosing(WindowEvent e) {
		dc.endSession();
        System.exit(0);
      }
}