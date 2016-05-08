package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import ba.unsa.etf.si.app.SIDEVS.Model.Faktura;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public final class FakturaVM {
	private Sessions s;

	public FakturaVM(Sessions s) {
		this.s = s;
	}

	public void dodajFakturu(Set<Lot> lotovi, List<Integer> kolicine, List<Integer> skladista, Kupac k) {
		try {
			
			Faktura f = new Faktura();
			f.setKorisnik(s.getKorisnik());
			f.setLotovi(lotovi);
			
			double izlazna_cijena = 0;
			int i = 0;
			for (Lot l : lotovi) {
				izlazna_cijena += l.getUlazna_cijena() * kolicine.get(i);
				i++;
			}
			f.setIzlazna_cijena(izlazna_cijena);
			f.setKupac(k);
			
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				DateFormat df = new SimpleDateFormat("ddMMyy-HHmmss");
				Date dateobj = new Date();

				String new_file_path = chooser.getSelectedFile().getAbsolutePath().toString() + "\\Faktura"
						+ df.format(dateobj).toString() + ".pdf";

				Document document = new Document();
				try {
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
					document.open();
					df = new SimpleDateFormat("dd-MM-yyyy");
					document.add(new Paragraph("Faktura " + df.format(dateobj)));
					document.add(new Paragraph(k.getNaziv()));
					document.close();
					writer.close();
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
