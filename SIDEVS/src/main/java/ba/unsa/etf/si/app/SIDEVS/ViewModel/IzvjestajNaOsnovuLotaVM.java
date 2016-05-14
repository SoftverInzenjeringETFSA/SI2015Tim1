package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
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

public class IzvjestajNaOsnovuLotaVM {
	final static Logger logger = Logger.getLogger(IzvjestajNaOsnovuLotaVM.class);
	
	private Sessions sesija;
	private Document document;
	private PdfPTable tableData;
	private List<String[]> lista;
	
	public IzvjestajNaOsnovuLotaVM(Sessions s){
		sesija = s;
		document = new Document();
		lista=new ArrayList<String[]>();
		tableData = new PdfPTable(5);
		tableData.setWidthPercentage(100);
		tableData.setSpacingBefore(10f);
		tableData.setSpacingAfter(10f);
	}

	
	public static List<String> lista_datumi_izlaza (Sessions ses, String broj_lota)throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		Criteria criteria = ses.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", broj_lota));
		List<Lot> listaLotova = criteria.list();
		List<FakturaLot> listaFakturaLot = new ArrayList<FakturaLot> (listaLotova.get(0).getFaktureLotovi());
		List<String> lista_datuma_izlaza = new ArrayList<String>();
		for (int i=0;i<listaFakturaLot.size();i++)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String datum_izlaza_string=formatter.format(listaFakturaLot.get(i).getFaktura().getDatum_kreiranja());
			lista_datuma_izlaza.add(datum_izlaza_string);
		}
		return lista_datuma_izlaza;
		}
		catch (Exception e) {
			logger.error(e);
		}
		return Collections.emptyList();
	}
	
	
	public void dodaj(String[] celije){
		lista.add(celije);
	}
	
	
	public void createPDF(String lot){
		//pdf
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

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
				document.open();
				
				PdfPTable table = new PdfPTable(2);
				
				String[] top = {"IZVJESTAJ ", "Lot broj: " + lot};					
				for (String s: top){
					PdfPCell cell = new PdfPCell(new Paragraph(s, titleFont));
					cell.setPadding(10);
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);
				}
				document.add(table);
				document.add(new Phrase("\n"));
				
				PdfPTable tLot = new PdfPTable(1);
				String str ="Lot broj:  " + lot;
				PdfPCell cell = new PdfPCell(new Paragraph(str,boldFont));
				cell.setBorder(Rectangle.BOTTOM);
				tLot.addCell(cell);	
				document.add(tLot);
				document.add(new Phrase("\n"));
				
				PdfPTable headerTable = new PdfPTable(3);
				
				String[] header = {"Datum", "Kolicina", "Tip"};
				
				for (int i=0; i<header.length; i++){
					cell = new PdfPCell(new Paragraph(header[i], boldFont));
					//cell.setPadding(10);
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
