package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public class IzvjestajUlaziIzlaziVM {
	final static Logger logger = Logger.getLogger(IzvjestajUlaziIzlaziVM.class);

	
	private Sessions sesija;
	private Document document;
	private PdfPTable tableData;
	private List<String[]> lista;
	private Lijek odabraniLijek;
	private Skladiste odabranoSkladiste;

	public IzvjestajUlaziIzlaziVM(Sessions s){
		sesija = s;
		document = new Document();
		lista=new ArrayList<String[]>();
		tableData = new PdfPTable(5);
		tableData.setWidthPercentage(100);
		tableData.setSpacingBefore(10f);
		tableData.setSpacingAfter(10f);
	}
	
	//funkcija vraca sve evidentirane lotove za odabrani lijek i skladiste
	public List<Lot> vratiSveLotove(Lijek odabraniLijek, Skladiste odabranoSkladiste){
		this.odabraniLijek = odabraniLijek;
		this.odabranoSkladiste = odabranoSkladiste;
		
		List<Lot> lotovi_tmp = sesija.getSession().createCriteria(Lot.class).list();		
		List<Lot> lotovi = new ArrayList<Lot>();
		for (Lot lot: lotovi_tmp){
			Set<Pakovanje> pakovanja = lot.getPakovanja();
			for (Pakovanje p: pakovanja){
				if (p.getSkladiste() == odabranoSkladiste && lot.getLijek()==odabraniLijek){
					System.out.println(lot.getBroj_lota());
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
	
	//obrisan lot
	public List<Lot> vratiOtpisaneLotove(List<Lot> sviLotovi, String datumOd, String datumDo){
		
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
		
		List<Lot> obrisaniLotovi = GlavneMetode.vratiOtpisaneLotoveOdDo(sesija, datumOd, datumDo);
		
		List<Lot> otpisaniLotovi = new ArrayList<Lot>();
		
		for (Lot lot: sviLotovi){
			for (Lot l: obrisaniLotovi)
				if (lot.getBroj_lota() == l.getBroj_lota() && l.getDatum_otpisa().after(datum_od) && l.getDatum_otpisa().before(datum_do))
					otpisaniLotovi.add(l);		
		}
				
		return otpisaniLotovi;
	}
	
	//funkcija koja vraca odgovarajuci datum za lot 
	public List<String> vratiDatum(Lot lot, Boolean ulazni){
		
		List<String> datumi = new ArrayList<String>();	
		if (ulazni){
			datumi.add(Conversions.dateToString(lot.getDatum_ulaza()));
		}
		else{
			Set<FakturaLot> fakture = lot.getFaktureLotovi();
			for (FakturaLot f: fakture){
				datumi.add(Conversions.dateToString (f.getFaktura().getDatum_kreiranja()) );
			}
		}
		
		return datumi;
	}
	
	
	public Integer vratiKolicine(Lot lot, Boolean ulazni){
		List<Integer> kolicine = new ArrayList<Integer>();
		if (ulazni){
			return GlavneMetode.vratiKolicinuUlaza(lot);	
		}
		else{
			return GlavneMetode.vratiKolicinuIzlaza(lot);
		}
	}
	
	public Integer vratiKolicinuOtpisanog(Lot lot){
		return GlavneMetode.vratiKolicinuOtpisanog(lot);
	}
	
	public Integer vratiTrenutnoStanje(Lot lot){
		Integer suma = GlavneMetode.vratiKolicinuUlaza(lot);
		suma -= GlavneMetode.vratiKolicinuIzlaza(lot);
		return suma;
	}

	public void dodaj(String[] celije){
		lista.add(celije);
	}
	
	
	public void createPDF(String datumOd, String datumDo){
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {


			String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Izvjestaj_"
					+ datumOd + "_" + datumDo + ".pdf";

			try {
				Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
				document.open();
				
				PdfPTable table = new PdfPTable(3);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10f);
				table.setSpacingAfter(10f);
				
				String[] top = {"SIDEVS", "Od: "+datumOd, "Do: "+datumDo};					
				for (String s: top){
					PdfPCell cell = new PdfPCell(new Paragraph(s, boldFont));
					cell.setPadding(10);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				document.add(table);
				document.add(new Phrase("\n"));
				
				table = new PdfPTable(2);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10f);
				table.setSpacingAfter(10f);
				String[] sta = {"Lijek: " + odabraniLijek.getNaziv(), "Skladiste: "+odabranoSkladiste.getBroj_skladista()};					
				for (String s: sta){
					PdfPCell cell = new PdfPCell(new Paragraph(s, boldFont));
					cell.setPadding(10);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(cell);
				}
				document.add(table);
				
				PdfPTable headerTable = new PdfPTable(5);
				headerTable.setWidthPercentage(100);
				headerTable.setSpacingBefore(10f);
				headerTable.setSpacingAfter(10f);
				
				String[] header = {"Datum", "Tip", "Lot", "Kolicina", "Trenutno stanje"};
				
				for (int i=0; i<header.length; i++){
					PdfPCell cell = new PdfPCell(new Paragraph(header[i], boldFont));
					cell.setPadding(10);
					headerTable.addCell(cell);
				}

				document.add(headerTable);

				PdfPTable t = new PdfPTable(5);
				t.setWidthPercentage(100);
				t.setSpacingBefore(10f);
				t.setSpacingAfter(10f);
				for (String[] stringovi: lista){
					for (String ss: stringovi){
						PdfPCell cell = new PdfPCell(new Paragraph(ss));
						cell.setPadding(10);
						t.addCell(cell);
					}
				}
				document.add(t);

				document.close();
				writer.close();
			} catch (DocumentException e) {
				logger.error(e);
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				logger.error(e);
				System.out.println(e.getMessage());
			}

			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File(new_file_path);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					logger.error(ex);
				}
			}
		}		
	}

	
	
}
