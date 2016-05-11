package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import org.hibernate.Criteria;
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

public final class IzvjestajZaKupcaVM {
	
	//URADITI PDF
	
	private Sessions sesija;

	public IzvjestajZaKupcaVM(Sessions s){
		sesija = s;
	}
	
	public List<FakturaLot> vratiFaktureKupca(Kupac k, String datumOd, String datumDo){
		
		List<FakturaLot> faktureKupca = new ArrayList<FakturaLot>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date datum_od=new Date();
		try {
			datum_od = sdf.parse(datumOd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date datum_do=new Date();
		try {
			datum_do = sdf.parse(datumDo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//izdvajamo fakture kupca:
		List<Faktura> fakture_tmp = sesija.getSession().createCriteria(Faktura.class).add(Restrictions.eq("kupac", k)).
				add(Restrictions.between("datum_kreiranja", datum_od, datum_do)).list();
		
		for (Faktura f: fakture_tmp){
			Set<FakturaLot> fl = f.getFaktureLotovi();
			for (FakturaLot fl1: fl){
				faktureKupca.add(fl1);
			}
		}		
		return faktureKupca;
	}
	
	public List<Lijek>  vratiLijekoveKupca(List<FakturaLot> fakture){
		
		List<Lijek> lijekovi = new ArrayList<Lijek>();
		
		Set<Lijek> setLijekova = new HashSet<Lijek>();
		
		
		for (FakturaLot f: fakture){
			setLijekova.add(f.getLot().getLijek());
		}	
		
		lijekovi.addAll(setLijekova);
		
		return lijekovi;
	}
	
	public List<Integer> vratiKolicineLijekova(List<FakturaLot> fakture, List<Lijek> lijekovi){
		List<Integer> kolicine=new ArrayList<Integer>();
		
		for (Lijek lijek: lijekovi){
			int suma = 0;
			for (FakturaLot fl: fakture){
				if (fl.getLot().getLijek() == lijek){
					suma += fl.getKolicina();
				}
			}
			kolicine.add(suma);
		}
		
		return kolicine;
	}
	
	public List<Integer> vratiCijene(List<FakturaLot> fakture, List<Integer> kolicine){
		List<Integer> cijene = new ArrayList<Integer>();
		
		cijene.add(0);cijene.add(0);cijene.add(0);
		
		return cijene;
	}
	
	
	
}
