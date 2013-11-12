package ee.ut.math.tvt.LaheLabor.ui;
import ee.ut.math.tvt.LaheLabor.domain.controller.SalesDomainController;
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