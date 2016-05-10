package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EvidencijaLotova {

	private JFrame frmEvidencijaLota;
	private JTextField textFieldBroj_lota;
	private JTextField textField_datum_dan;
	private JTextField textField_datum_mjesec;
	private JTextField textField_datum_godina;
	private JTextField textField_tezina;
	private JTextField textField_cijena;
	private JTextField textField_kolicina;
	private JLabel label_obavijest;
	private AutoCompleteJComboBox comboBox;
	private JComboBox comboBox_Skladiste;
	private Sessions s;

	public EvidencijaLotova(Sessions s) throws Exception {
		this.s = s;
		initialize();
		frmEvidencijaLota.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaLota = new JFrame();
		frmEvidencijaLota.setTitle("Evidencija lota");
		frmEvidencijaLota.setBounds(100, 100, 346, 293);
		frmEvidencijaLota.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEvidencijaLota.getContentPane().setLayout(null);
		frmEvidencijaLota.setLocationRelativeTo(null);
		
		JLabel lblBrojLota = new JLabel("Broj lota:");
		lblBrojLota.setBounds(10, 11, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblBrojLota);
		
		JLabel lblLijek = new JLabel("Lijek:");
		lblLijek.setBounds(10, 36, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblLijek);
		
		JLabel lblRokTrajanja = new JLabel("Rok trajanja:");
		lblRokTrajanja.setBounds(10, 86, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblRokTrajanja);
		
		JLabel lblTeina = new JLabel("Težina(g):");
		lblTeina.setBounds(10, 111, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblTeina);
		
		JLabel lblUlaznaCijena = new JLabel("Ulazna cijena:");
		lblUlaznaCijena.setBounds(10, 136, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblUlaznaCijena);
		
		JLabel lblKoliinatableta = new JLabel("Količina (tableta):");
		lblKoliinatableta.setBounds(10, 170, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblKoliinatableta);
		
		textFieldBroj_lota = new JTextField();
		textFieldBroj_lota.setBounds(119, 8, 170, 20);
		frmEvidencijaLota.getContentPane().add(textFieldBroj_lota);
		textFieldBroj_lota.setColumns(10);
		
		comboBox = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		comboBox.setBounds(119, 33, 170, 20);
		frmEvidencijaLota.getContentPane().add(comboBox);
		
		textField_datum_dan = new JTextField();
		textField_datum_dan.setBounds(119, 83, 32, 20);
		frmEvidencijaLota.getContentPane().add(textField_datum_dan);
		textField_datum_dan.setColumns(10);
		
		JLabel label = new JLabel(".");
		label.setBounds(156, 89, 14, 14);
		frmEvidencijaLota.getContentPane().add(label);
		
		textField_datum_mjesec = new JTextField();
		textField_datum_mjesec.setColumns(10);
		textField_datum_mjesec.setBounds(163, 83, 32, 20);
		frmEvidencijaLota.getContentPane().add(textField_datum_mjesec);
		
		JLabel label_1 = new JLabel(".");
		label_1.setBounds(199, 89, 14, 14);
		frmEvidencijaLota.getContentPane().add(label_1);
		
		textField_datum_godina = new JTextField();
		textField_datum_godina.setBounds(203, 83, 86, 20);
		frmEvidencijaLota.getContentPane().add(textField_datum_godina);
		textField_datum_godina.setColumns(10);
		
		textField_tezina = new JTextField();
		textField_tezina.setBounds(119, 108, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_tezina);
		textField_tezina.setColumns(10);
		
		textField_cijena = new JTextField();
		textField_cijena.setBounds(119, 133, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_cijena);
		textField_cijena.setColumns(10);
		
		textField_kolicina = new JTextField();
		textField_kolicina.setBounds(119, 167, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_kolicina);
		textField_kolicina.setColumns(10);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 229, 310, 14);
		frmEvidencijaLota.getContentPane().add(label_obavijest);
		
		JButton btnEvidentiraj = new JButton("Evidentiraj");
		btnEvidentiraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (validirajPolja()) {
						
						String naziv_lijeka = comboBox.getSelectedItem().toString();
						Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", naziv_lijeka)).uniqueResult();
						
						int id_skladista = Integer.parseInt(comboBox_Skladiste.getSelectedItem().toString());
						Skladiste skladiste = s.getSession().get(Skladiste.class, id_skladista);
						
						LotVM l = new LotVM(s);
						int kolicina = 0;
						if(!textField_kolicina.getText().isEmpty()){
							kolicina = Integer.parseInt(textField_kolicina.getText());
						}
						int godina = Integer.parseInt(textField_datum_godina.getText());
						int mjesec = Integer.parseInt(textField_datum_mjesec.getText());
						int dan = Integer.parseInt(textField_datum_dan.getText());
						@SuppressWarnings("deprecation")
						Date datum = new Date(godina, mjesec, dan);
						
						l.dodajLot(textFieldBroj_lota.getText(), Double.parseDouble(textField_tezina.getText()), Double.parseDouble(textField_cijena.getText()), datum, kolicina, lijek, skladiste);
						
						refreshPolja();
						label_obavijest.setForeground(Color.green);
						label_obavijest.setText("Uspješno ste kreirali lot");
					}
				} catch (Exception e) {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText(e.getMessage());
				}
			}
		});
		btnEvidentiraj.setBounds(119, 198, 170, 23);
		frmEvidencijaLota.getContentPane().add(btnEvidentiraj);
		
		comboBox_Skladiste = new JComboBox(SkladisteVM.dajSvaSkladista(s));
		comboBox_Skladiste.setBounds(119, 58, 170, 20);
		frmEvidencijaLota.getContentPane().add(comboBox_Skladiste);
		
		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 61, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblSkladite);
	}

	public void prikazi() {
		frmEvidencijaLota.setVisible(true);
	}
	private boolean validirajPolja() {
		String msg = "";
		label_obavijest.setForeground(Color.RED);
		if(!Validator.validirajString(textFieldBroj_lota.getText())) msg = "Popunite broj lota(samo slova i brojevi)";
		else if(textFieldBroj_lota.getText().length() != 6) msg = "Broj lota mora biti dužine 6";
		else if(comboBox.getSelectedIndex() == -1) msg = "Odaberite lijek";
		else if(!Validator.validirajBrojPozitivan(textField_tezina.getText())) msg= "Unesite težinu lijeka(veću od 0)";
		else if(!Validator.validirajBrojPozitivan(textField_cijena.getText())) msg= "Unesite cijenu lijeka(veću od 0)";
		else if(!Validator.validirajDatum(textField_datum_dan.getText(), textField_datum_mjesec.getText(), textField_datum_godina.getText())) msg = "Pogrešan format datuma";
		if(msg != ""){
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}
	private void refreshPolja() {
		textFieldBroj_lota.setText("");
		comboBox.setSelectedIndex(-1);
		textField_datum_dan.setText("");
		textField_datum_mjesec.setText("");
		textField_datum_godina.setText("");
		textField_tezina.setText("");
		textField_cijena.setText("");
		textField_kolicina.setText("");
	}
}
