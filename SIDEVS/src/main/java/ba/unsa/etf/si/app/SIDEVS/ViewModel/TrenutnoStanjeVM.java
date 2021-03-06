package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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

public class TrenutnoStanjeVM {
	final static Logger logger = Logger.getLogger(TrenutnoStanjeVM.class);
	private Sessions sesija;
	private Document document;
	private List<Lot> lotovi;
	private List<Skladiste> skladista;
	private List<Lijek> lijekovi;
	
	public TrenutnoStanjeVM(Sessions s){
		sesija = s;
		document = new Document();
		lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.isNull("datum_otpisa")).list();
		skladista = sesija.getSession().createCriteria(Skladiste.class).list();
		lijekovi = sesija.getSession().createCriteria(Lijek.class).list();
	}

	
	
	public Integer vratiTrenutnoStanje(Lot lot, Skladiste s){
		
		Integer suma = GlavneMetode.vratiKolicinuUlaza(lot, s);
		suma -= GlavneMetode.vratiKolicinuIzlaza(lot, s);
		return suma;
	}
	
	public void createPDF(){
		
		String datum = Conversions.dateToString(new Date());
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {


			String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Izvjestaj_"
					+ datum + "_"  + ".pdf";

			try {
				Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
				Font fontLijek = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
				
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
				document.open();
				
				PdfPTable table = new PdfPTable(2);
				
				String[] top = {"SIDEVS", "Datum: " + datum};					
				for (String s: top){
					PdfPCell cell = new PdfPCell(new Paragraph(s, font));
					cell.setPadding(10);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				document.add(table);
				document.add(new Phrase("\n"));
				
				
				for (Skladiste s: skladista){
					PdfPTable tSkladiste = new PdfPTable(1);
					String str ="Skladiste broj:  " + Integer.toString(s.getBroj_skladista());
					PdfPCell cell = new PdfPCell(new Paragraph(str,font));
					cell.setBorder(Rectangle.NO_BORDER);
					tSkladiste.addCell(cell);	
					document.add(tSkladiste);
					document.add(new Phrase("\n"));
					
					for (Lijek lijek: lijekovi){
						boolean imaLi = false;
						double total = 0;
						
						PdfPTable headerTable = new PdfPTable(4);
						
						String[] header = {"Lot", "Nabavna cijena", "Kolicina", "Vrijednost"};
						
						for (int i=0; i<header.length; i++){
							cell = new PdfPCell(new Paragraph(header[i],fontLijek));
							cell.setPadding(10);
							headerTable.addCell(cell);
						}
						
						PdfPTable tLotovi = new PdfPTable(4);
						for (Lot lot: lotovi){
							Set<Pakovanje> pakovanja = lot.getPakovanja();
							for (Pakovanje p: pakovanja){
								if (lot.getLijek() == lijek && p.getSkladiste()==s){
									imaLi=true;
									
									int kolicina = vratiTrenutnoStanje(lot, s);
									
									String[] podaciLota = {lot.getBroj_lota(),
															Double.toString(lot.getUlazna_cijena()),
															Integer.toString(kolicina),
															Double.toString(lot.getUlazna_cijena() * kolicina)
															};
									for(int i=0; i<4; i++){
									cell = new PdfPCell(new Paragraph( podaciLota[i] ));
									//cell.setPadding(10);
									if (i==0) cell.setBorder(Rectangle.LEFT);
									else if (i==3) cell.setBorder(Rectangle.RIGHT);
									else cell.setBorder(Rectangle.NO_BORDER);
									
									tLotovi.addCell(cell);
									}
									
									total += lot.getUlazna_cijena() * kolicina;
								}
							}
						}
						
						if (imaLi){
							PdfPTable tLijek = new PdfPTable(1);
							cell = new PdfPCell(new Paragraph("Lijek:  " + lijek.getNaziv(),fontLijek));
							tLijek.addCell(cell);
							
							PdfPTable tTotal = new PdfPTable(4);
							cell = new PdfPCell(new Paragraph("Total: "));
							cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
							tTotal.addCell(cell);
							cell = new PdfPCell(new Paragraph(" "));
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							tTotal.addCell(cell);
							cell = new PdfPCell(new Paragraph(" "));
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							tTotal.addCell(cell);
							cell = new PdfPCell(new Paragraph( Double.toString(total) ));
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
							tTotal.addCell(cell);
							
							document.add(tLijek);
							document.add(headerTable);
							document.add(tLotovi);
							document.add(tTotal);
							document.add(new Phrase("\n"));
							
						}
						
					}
				}
				
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
