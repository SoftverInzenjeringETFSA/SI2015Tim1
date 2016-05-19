package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.FakturaVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KreiranjeFakture {
	final static Logger logger = Logger.getLogger(KreiranjeFakture.class);

	private JFrame frmKreiranjeFakture;
	private JTextField textField_kolicina;
	private JTable table;
	private Sessions s;
	private int redni_broj = 1;

	// Za PDF
	private Kupac k;
	private List<Lot> lotovi = new ArrayList<Lot>();
	private List<Integer> kolicine = new ArrayList<Integer>();
	private List<Skladiste> skladista = new ArrayList<Skladiste>();
	private List<Double> cijene = new ArrayList<Double>();
	private JTextField textField_cijena;

	public KreiranjeFakture(Sessions s) throws Exception {
		this.s = s;
		initialize();
		
		frmKreiranjeFakture.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}
	
	public void ugasi(){
		frmKreiranjeFakture.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeFakture = new JFrame();
		frmKreiranjeFakture.setTitle("Kreiranje fakture");
		frmKreiranjeFakture.setBounds(100, 100, 755, 486);
		frmKreiranjeFakture.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeFakture.getContentPane().setLayout(null);
		frmKreiranjeFakture.setLocationRelativeTo(null);
		frmKreiranjeFakture.setResizable(false);

		final JPanel panel_kupac = new JPanel();
		panel_kupac.setBounds(10, 11, 201, 109);
		panel_kupac.setBorder(new TitledBorder(null, "Kupac", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmKreiranjeFakture.getContentPane().add(panel_kupac);
		panel_kupac.setLayout(null);

		final AutoCompleteJComboBox comboBox_kupac = new AutoCompleteJComboBox(s, Kupac.class, "naziv");
		comboBox_kupac.setBounds(10, 28, 181, 20);
		panel_kupac.add(comboBox_kupac);

		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog kupca");
		btnDodajNovogKorisnika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DodajKupca dk = new DodajKupca(s);
					dk.prikazi();	
				} catch (Exception e1) {
					logger.error(e1);
				}
			}
		});
		btnDodajNovogKorisnika.setBounds(10, 75, 181, 23);
		panel_kupac.add(btnDodajNovogKorisnika);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 131, 201, 55);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Skladi\u0161te",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_1);

		final JComboBox comboBox_skladista = new JComboBox(SkladisteVM.dajSvaSkladista(s));
		comboBox_skladista.setBounds(10, 21, 181, 20);
		panel_1.add(comboBox_skladista);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 197, 201, 113);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proizvod (lot)",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_2);

		final AutoCompleteJComboBox comboBox_lot = new AutoCompleteJComboBox(s, Lot.class, "broj_lota");
		comboBox_lot.setBounds(10, 27, 181, 20);
		panel_2.add(comboBox_lot);

		JLabel lblKoliina = new JLabel("Količina (kom):");
		lblKoliina.setBounds(10, 85, 84, 14);
		panel_2.add(lblKoliina);

		textField_kolicina = new JTextField();
		textField_kolicina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (Validator.daLiJeBroj(evt.getKeyChar())) {
					evt.consume();
				}
			}
		});
		textField_kolicina.setBounds(96, 82, 95, 20);
		panel_2.add(textField_kolicina);
		textField_kolicina.setColumns(10);
		
		JLabel lblCijena = new JLabel("Cijena:");
		lblCijena.setBounds(10, 60, 46, 14);
		panel_2.add(lblCijena);
		
		textField_cijena = new JTextField();
		textField_cijena.setBounds(96, 57, 95, 20);
		panel_2.add(textField_cijena);
		textField_cijena.setColumns(10);

		final JLabel label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 423, 719, 14);
		frmKreiranjeFakture.getContentPane().add(label_obavijest);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 20, 508, 358);
		frmKreiranjeFakture.getContentPane().add(scrollPane);

		final DefaultTableModel table_model = new DefaultTableModel();
		table = new JTable(table_model);
		scrollPane.setViewportView(table);

		table_model.addColumn("Red. br");
		table_model.addColumn("Skladište");
		table_model.addColumn("Lijek");
		table_model.addColumn("Lot");
		table_model.addColumn("Količina");
		table_model.addColumn("Jed. cijena");
		table_model.addColumn("Cijena");

		JButton btnDodajStavku = new JButton("Dodaj stavku");
		btnDodajStavku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (validriajUnosStavke()) {
					dodajStavkuUTabelu();
				}
			}

			private void dodajStavkuUTabelu() {
				try {
					Skladiste skladiste = s.getSession().get(Skladiste.class,
							Integer.parseInt(comboBox_skladista.getSelectedItem().toString()));
					Lot lot = s.getSession().get(Lot.class, comboBox_lot.getSelectedItem().toString());
					int kolicina = Integer.parseInt(textField_kolicina.getText());
					Double cijena = Double.parseDouble(textField_cijena.getText());

					if (k == null) {
						k = (Kupac) s.getSession().createCriteria(Kupac.class)
								.add(Restrictions.eq("naziv", comboBox_kupac.getSelectedItem().toString()))
								.uniqueResult();
					}
					String msg = "";
					if (k == null)
						msg = "Kupac ne postoji";
					else if (lot == null)
						msg = "Lot ne postoji";
					else {
						Boolean postoji = false;
						Criteria criteria = s.getSession().createCriteria(Pakovanje.class).add(Restrictions.eq("lot", lot));
						List<Pakovanje> pakovanja = criteria.list();
						for (Pakovanje pakovanje : pakovanja) {
							if(pakovanje.getSkladiste() == skladiste) {
								postoji = true;
								if(pakovanje.getKolicina() < kolicina){
									msg = "Na skladištu postoji " + pakovanje.getKolicina() + " pakovanja";
								}
							}
						}
						if(msg == "" && !postoji) msg = "Lot " + lot.getBroj_lota() + " ne postoji u skladištu " + skladiste.getBroj_skladista();
					}
					if (msg == "") {
						Lijek lijek = lot.getLijek();
						
						lotovi.add(lot);
						kolicine.add(kolicina);
						cijene.add(cijena);
						skladista.add(skladiste);

						table_model.addRow(new Object[] { redni_broj, skladiste.getBroj_skladista(), lijek.getNaziv(),
								lot.getBroj_lota(), kolicina, cijena, kolicina * cijena });
						redni_broj += 1;

						refreshPolja();
						label_obavijest.setForeground(Color.decode("#008000"));
						label_obavijest.setText("Uspješno ste dodali stavku");

						// Nakon prvog dodavanja zalediti kupca
						for (Component c : panel_kupac.getComponents()) {
							c.setEnabled(false);
						}
					} else {
						label_obavijest.setForeground(Color.RED);
						label_obavijest.setText(msg);
					}
				} catch (Exception ex) {
					logger.error(ex);
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText(ex.getMessage());
				}
			}

			private void refreshPolja() {
				comboBox_skladista.setSelectedIndex(0);
				comboBox_lot.setSelectedIndex(-1);
				textField_kolicina.setText("");
				textField_cijena.setText("");
			}

			private boolean validriajUnosStavke() {		
				System.out.println("tu");
				String msg = "";
				if (comboBox_kupac.getSelectedItem() == null)
					msg = "Odaberite kupca";
				
				//	msg = Validator.validirajKupca(s, comboBox_kupac.getSelectedItem().toString());
				else if (comboBox_skladista.getSelectedItem() == null)
					msg = "Odaberite skladište";
				else if (comboBox_lot.getSelectedItem() == null)
					msg = "Odaberite lot";
				
				//	msg = Validator.validirajLot(s, comboBox_lot.getSelectedItem().toString());
				else if (textField_cijena.getText().isEmpty()) 
					msg = "Unesite cijenu";
				else if (Double.parseDouble(textField_cijena.getText())<=0) msg = "Cijena mora biti veća od nule";
				else if (textField_kolicina.getText().isEmpty())
					msg = "Unesite količinu";
				else if (Double.parseDouble(textField_kolicina.getText())<=0) msg = "Količina mora biti veća od nule";
				
				if (comboBox_lot.getSelectedItem()!=null && msg == "") {
					System.out.println("ovdje");
					System.out.println(comboBox_lot.getSelectedItem().toString());
					msg = Validator.validirajLot(s, comboBox_lot.getSelectedItem().toString());
				}
				
				//provjera kolicine				
				if (comboBox_lot.getSelectedItem()!=null && msg == "")
	            {
	            	List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.like("broj_lota", (String) comboBox_lot.getSelectedItem())).list();
					Lot izabraniLot = lotovi.get(0);
					Integer kol=izabraniLot.getKolicina_tableta();
					System.out.println(kol.toString());
					System.out.println(textField_kolicina.getText());
					if (izabraniLot.getDatum_otpisa()!=null) { msg = "Izabrani lot je otpisan";
					}
					else if (Integer.parseInt(textField_kolicina.getText())>kol)
						msg="Nemate dovoljnu količinu lijeka na skladištu!";
	            }
				
				if (msg==""){
					msg = Validator.validirajKupca(s, comboBox_kupac.getSelectedItem().toString());
					
				}
				

				
				label_obavijest.setText(msg);
				if (msg != "") {
					label_obavijest.setForeground(Color.RED);
					return false;
				}
				return true;
			}
		});
		btnDodajStavku.setBounds(10, 321, 201, 23);
		frmKreiranjeFakture.getContentPane().add(btnDodajStavku);

		JButton btnFakturii = new JButton("Fakturiši");
		btnFakturii.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table_model.getRowCount() == 0) {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText("Nemate stavki na fakturi");
				} else {
					FakturaVM f = new FakturaVM(s);
					f.dodajFakturu(lotovi, kolicine, skladista, k, cijene);
					comboBox_skladista.setSelectedIndex(0);
					comboBox_lot.setSelectedIndex(-1);
					textField_kolicina.setText("");
					textField_cijena.setText("");
					table_model.setRowCount(0);
					for (Component c : panel_kupac.getComponents()) {
						c.setEnabled(true);
					}
					comboBox_kupac.setSelectedItem("");
					frmKreiranjeFakture.dispose();
				}
			}
		});
		btnFakturii.setBounds(10, 355, 201, 23);
		frmKreiranjeFakture.getContentPane().add(btnFakturii);

		JButton btnNewButton = new JButton("Obriši sve");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() == -1) {
					label_obavijest.setForeground(Color.red);
					label_obavijest.setText("Niste selektovali stavku");
					return;
				}
				comboBox_skladista.setSelectedIndex(0);
				comboBox_lot.setSelectedIndex(-1);
				textField_kolicina.setText("");
				for (int i = 0; i < table_model.getRowCount(); i++)
					table_model.removeRow(i);
				for (Component c : panel_kupac.getComponents()) {
					c.setEnabled(true);
				}
				comboBox_kupac.setSelectedItem("");
				label_obavijest.setForeground(Color.decode("#008000"));
				label_obavijest.setText("Uspješno ste obrisali fakturu");
			}
		});
		btnNewButton.setBounds(10, 389, 201, 23);
		frmKreiranjeFakture.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_brisi_redove = new JButton("Obriši odabrane redove");
		btnNewButton_brisi_redove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				removeSelectedRows(table);
			}
		});
		btnNewButton_brisi_redove.setBounds(546, 389, 183, 23);
		frmKreiranjeFakture.getContentPane().add(btnNewButton_brisi_redove);
	}

	public void prikazi() {
		frmKreiranjeFakture.setVisible(true);
	}

	public void removeSelectedRows(JTable table) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
		int brojac = 0;
		int k = 0;
		for (Lot lot : lotovi) {
			if(rows[k] == brojac){
				lotovi.remove(lot);
				kolicine.remove(brojac);
				skladista.remove(brojac);
			}
			brojac++;
		}
	}
}
