package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.app.SIDEVS.Model.FakturaLot;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;

public final class TrenutnoStanjePomocna {

	final static Logger logger = Logger.getLogger(TrenutnoStanjePomocna.class);
	public static List<Integer> vratiKolicine(List<Lot> lotovi, Boolean ulazni){
		List<Integer> kolicine = new ArrayList<Integer>();
		if (ulazni){
			for (Lot l: lotovi){
				Set<Pakovanje> pakovanja = l.getPakovanja();
				for (Pakovanje p: pakovanja)
					kolicine.add(p.getKolicina());
			}
				
		}
		else{
			for (Lot l:lotovi){
				Set<FakturaLot> fakture = l.getFaktureLotovi();
				for (FakturaLot f: fakture)
					kolicine.add(f.getKolicina());
			}
		}
		
		return kolicine;
	}
	
	public static Integer vratiKolicinuOtpisanog(Lot lot){
		return vratiStanjePomocna((Lot)lot);
	}
	
	public static Integer vratiTrenutnoStanje(Lot lot){
		return vratiStanjePomocna(lot);
	}
	
	public static Integer vratiStanjePomocna(Lot lot){
		List<Lot> lotovi= new ArrayList<Lot>();
		lotovi.add(lot);
		int suma = 0;	
		List<Integer> ul = vratiKolicine(lotovi, true);
		for (int i: ul){
			suma+=i;
		}
		List<Integer> izlazi = vratiKolicine(lotovi, false);
		for (int i: izlazi){
			suma-=i;
		}
		return suma;
	}
	
	
}
