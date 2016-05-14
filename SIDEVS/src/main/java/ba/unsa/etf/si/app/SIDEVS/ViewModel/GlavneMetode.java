//klasa za sve metode koje koriste kontroleri


package ba.unsa.etf.si.app.SIDEVS.ViewModel;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Restrictions;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public final class GlavneMetode {
	
	public static Set<Skladiste> vratiSkladista(Lot lot){
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		Set<Skladiste> skladista = new HashSet<Skladiste>();
		for (Pakovanje p: pakovanja){
			skladista.add(p.getSkladiste());
		}
		return skladista;
	}
	
	public static List<Lot> vratiSveLotove(Sessions sesija){
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).list();
		return lotovi;
	}
	

	public static List<Lot> vratiOtpisaneLotove(Sessions sesija){
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.isNotNull("datum_otpisa")).list();
		return lotovi;
	}
	
	public static List<Lot> vratiNeotpisaneLotove(Sessions sesija){
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.isNull("datum_otpisa")).list();
		return lotovi;
	}
	
	
	public static List<Lot> vratiSveLotoveOdDo(Sessions sesija, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).
				add(Restrictions.between("datum_ulaza", datum_od, datum_do)).list();
		return lotovi;
	}
	
	public static List<Lot> vratiLotoveOdDo(Sessions sesija, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).
				add(Restrictions.between("datum_ulaza", datum_od, datum_do)).
				add(Restrictions.isNull("datum_otpisa")).list();
		return lotovi;
	}
	
	public static List<Lot> vratiOtpisaneLotoveOdDo(Sessions sesija, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).
				add(Restrictions.between("datum_ulaza", datum_od, datum_do)).
				add(Restrictions.isNotNull("datum_otpisa")).list();
		return lotovi;
	}
	
	
	public static Integer vratiKolicinuIzlaza(Lot lot, Skladiste skladiste){
		Set<FakturaLot> fakture = lot.getFaktureLotovi();
		Integer kolicina = 0;
		for (FakturaLot f: fakture){
			kolicina += f.getKolicina();
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuIzlaza(Lot lot){
		Integer kolicina = 0;
		Set<Skladiste> skladista = vratiSkladista(lot);
		for (Skladiste s: skladista){
			kolicina += vratiKolicinuIzlaza(lot, s);
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuUlaza(Lot lot, Skladiste skladiste){
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		Integer kolicina = 0;
		for (Pakovanje p: pakovanja){
			kolicina += p.getKolicina();
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuUlaza(Lot lot){
		Integer kolicina = 0;
		Set<Skladiste> skladista = vratiSkladista(lot);
		for (Skladiste s: skladista){
			kolicina += vratiKolicinuUlaza(lot, s);
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuOtpisanog(Lot lot, Skladiste skladiste){
		Set<FakturaLot> fakture = lot.getFaktureLotovi();
		Integer kolicina = vratiKolicinuUlaza(lot, skladiste);
		for (FakturaLot f: fakture){
			kolicina -= f.getKolicina();
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuOtpisanog(Lot lot){
		Integer kolicina = 0;
		Set<Skladiste> skladista = vratiSkladista(lot);
		for (Skladiste s: skladista){
			kolicina += vratiKolicinuOtpisanog(lot, s);
		}
		return kolicina;
	}
	
	public static Integer vratiTrenutnoStanje(Lot lot, List<Skladiste> skladista){
		Integer stanje = 0; 
		for (Skladiste s: skladista){
			stanje = vratiKolicinuUlaza(lot, s) - vratiKolicinuIzlaza(lot, s) - vratiKolicinuOtpisanog(lot, s);
		}
		return stanje;
	}
	
	public static Integer vratiKolicinuIzlazaOdDo(Lot lot, Skladiste skladiste, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		Set<FakturaLot> fakture = lot.getFaktureLotovi();
		Integer kolicina = 0;
		for (FakturaLot f: fakture){
			if (f.getFaktura().getDatum_kreiranja().after(datum_od) && f.getFaktura().getDatum_kreiranja().before(datum_do)){
				kolicina += f.getKolicina();
			}		
		}
		return kolicina;
	}
	

	
	public static Integer vratiKolicinuUlazaOdDo(Lot lot, Skladiste skladiste, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		Integer kolicina = 0;
		for (Pakovanje p: pakovanja){
			if (lot.getDatum_ulaza().after(datum_od) && lot.getDatum_ulaza().before(datum_do)){
			kolicina += p.getKolicina();
			}
		}
		return kolicina;
	}
	
	public static Integer vratiKolicinuOtpisanogOdDo(Lot lot, Skladiste skladiste, String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		if (lot.getDatum_otpisa()==null) return 0;
		Set<FakturaLot> fakture = lot.getFaktureLotovi();
		Integer kolicina = vratiKolicinuUlaza(lot, skladiste);
		for (FakturaLot f: fakture){
			if (lot.getDatum_otpisa().after(datum_od) && lot.getDatum_ulaza().before(datum_do)){
			kolicina -= f.getKolicina();
			}
		}
		return kolicina;
	}
	

}
