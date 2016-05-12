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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Restrictions;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import javax.swing.JFileChooser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class IzvjestajZaOdredjeniPeriodVM {
	
	private Sessions sesija;
	
	public IzvjestajZaOdredjeniPeriodVM(Sessions sesija){
		this.sesija=sesija;
	}
	
	public List<Lot> vratiUlazneLotove(String datumOd, String datumDo){
		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
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
		

		
		public void createPDF(List<Lot> lotovi, String datumOd, String datumDo){
			
			
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				DateFormat df = new SimpleDateFormat("ddMMyy-HHmmss");
				Date dateobj = new Date();

				String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Izvjestaj_"
						+ datumOd + "_" + datumDo + ".pdf";

				Document document = new Document();
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
					
					PdfPTable headerTable = new PdfPTable(5);
					headerTable.setWidthPercentage(100);
					headerTable.setSpacingBefore(10f);
					headerTable.setSpacingAfter(10f);
					
					String[] header = {"Proizvod", "Lot", "Skladište", "Ulazi", "Izlazi"};
					
					for (int i=0; i<header.length; i++){
						PdfPCell cell = new PdfPCell(new Paragraph(header[i], boldFont));
						cell.setPadding(10);
						headerTable.addCell(cell);
					}

					
					document.add(headerTable);

					PdfPTable table_lot = new PdfPTable(5);		
					table_lot.setWidthPercentage(100);
					table_lot.setSpacingBefore(10f);
					table_lot.setSpacingAfter(10f);
						for (Lot lot: lotovi){
							for (Skladiste s: vratiSkladista(lot)){
								
								String [] kolone = {lot.getLijek().getNaziv(), 
													lot.getBroj_lota(),
													Integer.toString(s.getBroj_skladista()),
													Integer.toString(vratiUkupniUlaz(lot, s)),
													Integer.toString(vratiUkupniIzlaz(lot, s, datumOd, datumDo))
													};
								
								for (String celija: kolone){
									PdfPCell cell = new PdfPCell(new Paragraph(celija));
									cell.setPadding(16);
									cell.setBorder(Rectangle.NO_BORDER);
									table_lot.addCell(cell);
								}								
							}
						}
						document.add(table_lot);

					document.close();
					writer.close();
				} catch (DocumentException e) {
					System.out.println(e.getMessage());
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}

				if (Desktop.isDesktopSupported()) {
					try {
						File myFile = new File(new_file_path);
						Desktop.getDesktop().open(myFile);
					} catch (IOException ex) {
						// no application registered for PDFs
					}
				}
			}		
		}
}
