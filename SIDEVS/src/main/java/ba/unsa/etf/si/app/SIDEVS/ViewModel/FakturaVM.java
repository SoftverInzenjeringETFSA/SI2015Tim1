package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

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

import ba.unsa.etf.si.app.SIDEVS.Model.Faktura;
import ba.unsa.etf.si.app.SIDEVS.Model.FakturaLot;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public final class FakturaVM {
	private Sessions s;

	public FakturaVM(Sessions s) {
		this.s = s;
	}

	public void dodajFakturu(Set<Lot> lotovi, List<Integer> kolicine, List<Skladiste> skladista, Kupac k) {
		try {
			Faktura f = new Faktura();
			f.setKorisnik(s.getKorisnik());
			double izlazna_cijena = 0;
			int i = 0;
			for (Lot l : lotovi) {
				izlazna_cijena += l.getUlazna_cijena() * kolicine.get(i);
				i++;
			}
			f.setIzlazna_cijena(izlazna_cijena);
			f.setKupac(k);
			// Dodavanje u fakture lotovi i skladiste
			i = 0;
			for (Lot l : lotovi) {
				FakturaLot fk = new FakturaLot();
				fk.setFaktura(f);
				fk.setLot(l);
				fk.setKolicina(kolicine.get(i));
				fk.setSkladiste(skladista.get(i));
				i++;

				s.getSession().beginTransaction();
				s.getSession().save(fk);
				s.getTrasaction().commit();
			}

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

					Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new_file_path));
					document.open();

					// Tabel o fakturi
					PdfPTable table = new PdfPTable(2);
					table.setWidthPercentage(100);
					table.setSpacingBefore(10f);
					table.setSpacingAfter(10f);

					PdfPCell cell1 = new PdfPCell(new Paragraph("SIDEVS", boldFont));
					cell1.setPadding(10);
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

					table.addCell(cell1);

					df = new SimpleDateFormat("dd-MM-yyyy");

					PdfPCell cell2 = new PdfPCell(new Paragraph("Datum kreiranja: " + df.format(dateobj)));
					cell2.setPadding(10);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

					table.addCell(cell2);

					cell1 = new PdfPCell(new Paragraph("Faktura " + f.getId()));
					cell1.setPadding(10);
					cell1.setColspan(2);

					table.addCell(cell1);

					// Tabela podaci o kupcu

					PdfPCell cell3 = new PdfPCell(new Paragraph("Kupac: " + k.getNaziv()));
					cell3.setPadding(10);
					cell3.setBorder(Rectangle.BOTTOM);
					table.addCell(cell3);
					PdfPCell cell4 = new PdfPCell(new Paragraph("Adresa: " + k.getAdresa()));
					cell4.setPadding(10);
					cell4.setBorder(Rectangle.BOTTOM);
					table.addCell(cell4);
					table.addCell(cell4);

					document.add(table);

					// Izlaz lijekovi
					table = new PdfPTable(4);
					table.setWidthPercentage(100);
					table.setSpacingBefore(10f);
					table.setSpacingAfter(10f);

					Set<Lijek> lijekovi = new HashSet<Lijek>();
					double total = 0;

					for (Lot lot : lotovi) {
						PdfPTable table_lot = new PdfPTable(4);
						Lijek lijek = lot.getLijek();
						if (!lijekovi.contains(lijek)) {
							PdfPCell cell = new PdfPCell(new Paragraph(lijek.getNaziv(), boldFont));
							cell.setPadding(10);
							cell.setBorder(Rectangle.BOTTOM);
							cell.setColspan(4);
							cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
							table.addCell(cell);

							// Headers tabele
							cell = new PdfPCell(new Paragraph("Broj lota", boldFont));
							cell.setPadding(10);
							cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
							table.addCell(cell);

							cell = new PdfPCell(new Paragraph("Izlazna cijena", boldFont));
							cell.setPadding(10);
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							table.addCell(cell);

							cell = new PdfPCell(new Paragraph("Količina", boldFont));
							cell.setPadding(10);
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
							table.addCell(cell);

							cell = new PdfPCell(new Paragraph("Skladište", boldFont));
							cell.setPadding(10);
							cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
							table.addCell(cell);

							// Dodamo lotove
							int index = 0;
							int kolicina_po_lijeku = 0;
							double cijena_po_lijeku = 0;
							for (Lot lot_ostali : lotovi) {
								if (lot_ostali.getLijek() == lijek) {
									// 1
									cell = new PdfPCell(new Paragraph(lot_ostali.getBroj_lota()));
									cell.setPadding(10);
									cell.setBorder(Rectangle.NO_BORDER);
									table_lot.addCell(cell);
									// 2
									double cijena = new BigDecimal(lot_ostali.getUlazna_cijena() * 0.17
											+ lot_ostali.getUlazna_cijena()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									cijena_po_lijeku += cijena;
									total += cijena;
									cell = new PdfPCell(new Paragraph(Double.toString(cijena) + " KM"));
									cell.setPadding(10);
									cell.setBorder(Rectangle.NO_BORDER);
									table_lot.addCell(cell);
									// 3
									cell = new PdfPCell(new Paragraph(kolicine.get(index).toString()));
									cell.setPadding(10);
									cell.setBorder(Rectangle.NO_BORDER);
									table_lot.addCell(cell);

									kolicina_po_lijeku += kolicine.get(index);

									// 4
									cell = new PdfPCell(
											new Paragraph(Integer.toString(skladista.get(index).getBroj_skladista())));
									cell.setPadding(10);
									cell.setBorder(Rectangle.NO_BORDER);
									table_lot.addCell(cell);
								}
								index++;
							}

							if (kolicina_po_lijeku != 0) {
								cell = new PdfPCell(new Paragraph("", boldFont));
								cell.setPadding(10);
								cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
								table_lot.addCell(cell);
								
								cell = new PdfPCell(new Paragraph(Double.toString(cijena_po_lijeku) + " KM", boldFont));
								cell.setPadding(10);
								cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
								table_lot.addCell(cell);

								cell = new PdfPCell(new Paragraph(Integer.toString(kolicina_po_lijeku), boldFont));
								cell.setPadding(10);
								cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
								table_lot.addCell(cell);

								cell = new PdfPCell(new Paragraph("", boldFont));
								cell.setPadding(10);
								cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
								table_lot.addCell(cell);
							}

							lijekovi.add(lijek);
							cell = new PdfPCell(table_lot);
							cell.setPadding(10);
							cell.setBorder(Rectangle.BOTTOM);
							cell.setColspan(4);
							table.addCell(cell);
						}
					}
					
					PdfPCell cell = new PdfPCell(new Paragraph("Total: " + Double.toString(new BigDecimal(total).setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue()) + " KM", boldFont));
					cell.setPadding(10);
					cell.setColspan(4);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell);

					document.add(table);

					document.close();
					writer.close();
				} catch (DocumentException e) {
					System.out.println(e.getMessage());
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}

				// Otvori pdf
				if (Desktop.isDesktopSupported()) {
					try {
						File myFile = new File(new_file_path);
						Desktop.getDesktop().open(myFile);
					} catch (IOException ex) {
						// no application registered for PDFs
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
