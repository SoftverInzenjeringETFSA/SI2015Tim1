package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public class IzvjestajZaOdredjeniPeriodVM {
	
	private Sessions sesija;
	
	public IzvjestajZaOdredjeniPeriodVM(Sessions sesija){
		this.sesija=sesija;
	}
	
	public List<Lot> vratiUlazneLotove(String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		Lot l;
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).
				add(Restrictions.between("datum_ulaza", datum_od, datum_do)).list();
		
		return lotovi;
	}
	
	public List<ObrisanLot> vratiOtpisaneLotove(List<Lot> sviLotovi, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		
		List<ObrisanLot> obrisaniLotovi = sesija.getSession().createCriteria(ObrisanLot.class).list();
		
		List<ObrisanLot> otpisaniLotovi = new ArrayList<ObrisanLot>();
		
		for (Lot lot: sviLotovi){
			for (ObrisanLot l: obrisaniLotovi)
				if (lot.getBroj_lota() == l.getBroj_lota() && l.getDatum_otpisa().after(datum_od) && l.getDatum_otpisa().before(datum_do))
					otpisaniLotovi.add(l);		
		}
				
		return otpisaniLotovi;
	}
	
	
	public Set<Skladiste> vratiSkladista(Lot lot){
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		Set<Skladiste> skladista = new HashSet<Skladiste>();
		for (Pakovanje p: pakovanja){
			skladista.add(p.getSkladiste());
		}
		return skladista;
	}
	
	public Integer vratiUkupniUlaz(Lot l, Skladiste s){
		int suma = 0;
		Set<Pakovanje> pakovanja = l.getPakovanja();
		for (Pakovanje p: pakovanja){
			suma += p.getKolicina();
		}
		return suma;	
	}
	
		public Integer vratiUkupniIzlaz(Lot l, Skladiste s, String datumOd, String datumDo){
		int suma = 0;
		Set<FakturaLot> fakture = l.getFaktureLotovi();
		for (FakturaLot f: fakture){
			suma += f.getKolicina();
		}
		List<Lot> lot_tmp = new ArrayList<Lot>(); lot_tmp.add(l);
		List<ObrisanLot> obrisaniLotovi = vratiOtpisaneLotove(lot_tmp, datumOd, datumDo);
		for (ObrisanLot otpisani: obrisaniLotovi)
			suma += vratiKolicinuOtpisanog(otpisani);
		return suma;	
	}
		
		public Integer vratiKolicinuOtpisanog(ObrisanLot lot){
			return vratiStanjePomocna((Lot)lot);
		}
		
		public Integer vratiTrenutnoStanje(Lot lot){
			return vratiStanjePomocna(lot);
		}
		
		public Integer vratiStanjePomocna(Lot lot){
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
		
		public List<Integer> vratiKolicine(List<Lot> lotovi, Boolean ulazni){
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
	

}
