package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Restrictions;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public class IzvjestajUlaziIzlaziVM {

	//URADITI PDF
	
	private Sessions sesija;

	public IzvjestajUlaziIzlaziVM(Sessions s){
		sesija = s;
	}
	
	//funkcija vraca sve evidentirane lotove za odabrani lijek i skladiste
	public List<Lot> vratiSveLotove(Lijek odabraniLijek, Skladiste odabranoSkladiste){
		
		List<Lot> lotovi_tmp = sesija.getSession().createCriteria(Lot.class).add(Restrictions.eq("lijek", odabraniLijek)).list();		
		List<Lot> lotovi = new ArrayList<Lot>();
		for (Lot lot: lotovi_tmp){
			Set<Pakovanje> pakovanja = lot.getPakovanja();
			for (Pakovanje p: pakovanja){
				if (p.getSkladiste() == odabranoSkladiste){
					lotovi.add(lot);
				}
			}
		}
		
		return lotovi;
	}
	
	
	public List<Lot>vratiUlazneLotove(List<Lot> sviLotovi, String datumOd, String datumDo){	
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		List<Lot> ulazniLotovi = new ArrayList<Lot>();
		for (Lot lot: sviLotovi){
			if (lot.getDatum_ulaza().after(datum_od) && lot.getDatum_ulaza().before(datum_do))
				ulazniLotovi.add(lot);
		}
		return ulazniLotovi;
	}
	
	public List<Lot> vratiIzlazneLotove(List<Lot> sviLotovi, String datumOd, String datumDo){
			
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		
		List<Lot> izlazniLotovi = new ArrayList<Lot>();
		for (Lot lot: sviLotovi){
			Set<FakturaLot> fakture = lot.getFaktureLotovi();
			for (FakturaLot l: fakture){
				if (l.getFaktura().getDatum_kreiranja().after(datum_od) && 
						l.getFaktura().getDatum_kreiranja().before(datum_do)
						){
					izlazniLotovi.add(lot);
				}
			}
		}
		return izlazniLotovi;
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
	
	//funkcija koja vraca odgovarajuci datum za lot 
	public List<String> vratiDatum(Lot lot, Boolean ulazni){
		
		List<String> datumi = new ArrayList<String>();	
		if (ulazni){
			datumi.add(Conversions.dateToString((lot.getDatum_ulaza())));
		}
		else{
			Set<FakturaLot> fakture = lot.getFaktureLotovi();
			for (FakturaLot f: fakture){
				datumi.add(Conversions.dateToString (f.getFaktura().getDatum_kreiranja()) );
			}
		}
		
		return datumi;
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

	
	
}
