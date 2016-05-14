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

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import javax.swing.JFileChooser;

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

public class IzvjestajZaOdredjeniPeriodVM {
	final static Logger logger = Logger.getLogger(IzvjestajZaOdredjeniPeriodVM.class);
	
	private Sessions sesija;
	private Document document;
	private List<String[]> lista;
	
	
	public IzvjestajZaOdredjeniPeriodVM(Sessions sesija){
		this.sesija=sesija;
		document = new Document();
		lista=new ArrayList<String[]>();
	}
	
	
	public Set<Skladiste> vratiSkladista(Lot lot){
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		Set<Skladiste> skladista = new HashSet<Skladiste>();
		for (Pakovanje p: pakovanja){
			skladista.add(p.getSkladiste());
		}
		return skladista;
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

				Document document = new Document();
				try {
					Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
					Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
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
					
					PdfPTable headerTable = new PdfPTable(5);		
					String[] header = {"Proizvod", "Lot", "Skladište", "Ulazi", "Izlazi"};
					
					for (int i=0; i<header.length; i++){
						PdfPCell cell = new PdfPCell(new Paragraph(header[i], boldFont));
						cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
						cell.setPadding(10);
						headerTable.addCell(cell);
					}

					document.add(headerTable);

					PdfPTable t = new PdfPTable(5);
					for (String[] stringovi: lista){
						for (String ss: stringovi){
							PdfPCell cell = new PdfPCell(new Paragraph(ss,obicni));
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
