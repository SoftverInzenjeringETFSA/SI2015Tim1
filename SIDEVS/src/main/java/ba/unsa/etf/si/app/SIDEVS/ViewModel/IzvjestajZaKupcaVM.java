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

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
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
import ba.unsa.etf.si.app.SIDEVS.Validation.*;

public final class IzvjestajZaKupcaVM {
	final static Logger logger = Logger.getLogger(IzvjestajZaKupcaVM.class);
	
	private Sessions sesija;
	private Document document;
	private PdfPTable tableData;
	private List<String[]> lista;
	private Kupac kupac;
	private String total;
	

	public IzvjestajZaKupcaVM(Sessions s){
		sesija = s;
		document = new Document();
		lista=new ArrayList<String[]>();
	}
	
	public void setTotal(String total){
		this.total = total;
	}
	
	public List<FakturaLot> vratiFaktureKupca(Kupac k, String datumOd, String datumDo){
		kupac = k;
		List<FakturaLot> faktureKupca = new ArrayList<FakturaLot>();

		Date datum_od = Conversions.stringToDate(datumOd);
		Date datum_do = Conversions.stringToDate(datumDo);
	
		List<Faktura> fakture_tmp = sesija.getSession().createCriteria(Faktura.class).add(Restrictions.eq("kupac", k)).
				add(Restrictions.between("datum_kreiranja", datum_od, datum_do)).list();
		
		for (Faktura f: fakture_tmp){
			Set<FakturaLot> fl = f.getFaktureLotovi();
			for (FakturaLot fl1: fl){
				if (f.getKupac()==k){
					faktureKupca.add(fl1);
				}
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
	
	public Integer vratiKolicinuLijekova(List<FakturaLot> fakture, Lijek lijek){
		
			int suma = 0;
			for (FakturaLot fl: fakture){
				if (fl.getLot().getLijek() == lijek){
					suma += fl.getKolicina();
				}
			}
	
		return suma;
	}
	
	public Double vratiCijenuLijekova(List<FakturaLot> fakture, Lijek lijek){
		
		Double suma = (double) 0;
		for (FakturaLot fl: fakture){
			if (fl.getLot().getLijek() == lijek){
				suma += fl.getCijena();
			}
		}

	return suma;
}

	public void dodaj(String[] celije) {
		lista.add(celije);
	}
	
	public void createPDF(String datumOd, String datumDo){
		
		String datum = Conversions.dateToString(new Date());
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {


			String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Izvjestaj_"
					+ "_lot_" + datum + ".pdf";

			try {
				Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
				Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
				Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
				Font obicni = new Font(Font.FontFamily.HELVETICA, 12);

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
				document.open();
				
				PdfPTable table = new PdfPTable(3);
				
				String[] top = {"Izvještaj: ", "od " +datumOd, "do "+datumDo};					
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

				
				PdfPTable tLot = new PdfPTable(1);
				String str ="Kupac: " + kupac.getNaziv();
				PdfPCell cell = new PdfPCell(new Paragraph(str,boldFont));
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setBorder(Rectangle.BOTTOM);
				tLot.addCell(cell);	
				document.add(tLot);
				document.add(new Phrase("\n"));
				
				PdfPTable headerTable = new PdfPTable(3);
				
				String[] header = {"Lijek", "Kolicina", "Vrijednost"};
				
				for (int i=0; i<header.length; i++){
					cell = new PdfPCell(new Paragraph(header[i], boldFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerTable.addCell(cell);
				}

				document.add(headerTable);

				PdfPTable t = new PdfPTable(3);
				for (String[] stringovi: lista){
					for (String ss: stringovi){
						cell = new PdfPCell(new Paragraph(ss));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPadding(6);
						t.addCell(cell);
					}
				}
				document.add(t);
				document.add(new Phrase("\n"));
				
				t = new PdfPTable(3);
				String[] tot = {"Total: ","", total};
				for (String ss: tot){	
						cell = new PdfPCell(new Paragraph(ss));
						cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
						cell.setBorder(Rectangle.BOTTOM);
						if (ss==total) cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setPadding(6);
						t.addCell(cell);
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
