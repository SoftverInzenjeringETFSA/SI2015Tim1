package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public class IzvjestajUlaziIzlaziVMTest {
	
	public IzvjestajUlaziIzlaziVM vm;
	
	
	@Test
	public void samoTestiranje() throws Exception {
		Sessions s = new Sessions("maida", "p");
		vm = new IzvjestajUlaziIzlaziVM(s);
		assertEquals(5, vm.samoTest(""));
		
	}

}
