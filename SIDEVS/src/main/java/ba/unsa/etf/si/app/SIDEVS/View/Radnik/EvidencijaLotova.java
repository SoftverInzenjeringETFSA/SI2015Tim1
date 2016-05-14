package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.criterion.Restrictions;

import Exceptions.WrongInputException;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EvidencijaLotova {

	private JFrame frmEvidencijaLota;
	private JTextField textField_tezina;
	private JTextField textField_kolicina;
	private JLabel label_obavijest;
	private AutoCompleteJComboBox comboBox;
	private JComboBox comboBox_Skladiste;
	private Sessions s;
	private JTextField textField_kolicina_pakovanje;
	private JFormattedTextField textFieldBroj_lota;
	private JFormattedTextField txtRokTrajanja;
	private JTextField textField_cijena;
	

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
		frmEvidencijaLota.setResizable(false);
		frmEvidencijaLota.setTitle("Evidencija lota");
		frmEvidencijaLota.setBounds(100, 100, 366, 320);
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
		lblKoliinatableta.setBounds(10, 167, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblKoliinatableta);
		
		comboBox = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		comboBox.setBounds(150, 33, 170, 20);
		frmEvidencijaLota.getContentPane().add(comboBox);
		
		textField_tezina = new JTextField();
		textField_tezina.setBounds(150, 108, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_tezina);
		textField_tezina.setColumns(10);
		
		textField_kolicina = new JTextField();
		textField_kolicina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (Validator.daLiJeBroj(evt.getKeyChar())) {
					evt.consume();
				}
			}
		});
		textField_kolicina.setBounds(150, 164, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_kolicina);
		textField_kolicina.setColumns(10);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 256, 310, 14);
		frmEvidencijaLota.getContentPane().add(label_obavijest);
		
		JButton btnEvidentiraj = new JButton("Evidentiraj");
		btnEvidentiraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (validirajPolja()) {
						System.out.println("tu");
						String naziv_lijeka = comboBox.getSelectedItem().toString();
						Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", naziv_lijeka)).uniqueResult();
						
						int id_skladista = Integer.parseInt(comboBox_Skladiste.getSelectedItem().toString());
						Skladiste skladiste = s.getSession().get(Skladiste.class, id_skladista);
						
						LotVM l = new LotVM(s);
						int kolicina = 0;
						
						kolicina = Integer.parseInt(textField_kolicina.getText());
							
				        java.util.Date datum = Conversions.stringToDate(txtRokTrajanja.getText());
					
						
						l.dodajLot(textFieldBroj_lota.getText(), Double.parseDouble(textField_tezina.getText()), Double.parseDouble(textField_cijena.getText()), datum, kolicina, lijek, skladiste, Integer.parseInt(textField_kolicina_pakovanje.getText()));
						
						refreshPolja();
						label_obavijest.setForeground(Color.green);
						label_obavijest.setText("Uspješno ste kreirali lot");
					}
				} 
				catch (NumberFormatException e) {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText("Morate unijeti sve podatke");
				}
				catch (Exception e) {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText(e.getMessage());
				}
			}
		});
		btnEvidentiraj.setBounds(150, 222, 170, 23);
		frmEvidencijaLota.getContentPane().add(btnEvidentiraj);
		
		comboBox_Skladiste = new JComboBox(SkladisteVM.dajSvaSkladista(s));
		comboBox_Skladiste.setBounds(150, 58, 170, 20);
		frmEvidencijaLota.getContentPane().add(comboBox_Skladiste);
		
		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 61, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblSkladite);
		
		textField_kolicina_pakovanje = new JTextField();
		textField_kolicina_pakovanje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (Validator.daLiJeBroj(evt.getKeyChar())) {
					evt.consume();
				}
			}
		});
		textField_kolicina_pakovanje.setBounds(150, 191, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_kolicina_pakovanje);
		textField_kolicina_pakovanje.setColumns(10);
		
		JLabel lblKoliinapakovanje = new JLabel("Količina (pakovanje):");
		lblKoliinapakovanje.setBounds(10, 194, 109, 14);
		frmEvidencijaLota.getContentPane().add(lblKoliinapakovanje);
		
		textFieldBroj_lota = new JFormattedTextField();
		
		textFieldBroj_lota.setBounds(148, 8, 172, 20);
		frmEvidencijaLota.getContentPane().add(textFieldBroj_lota);
		
		try {
			txtRokTrajanja = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		} catch (WrongInputException e) {
			label_obavijest.setText("ovo");
		}
		txtRokTrajanja.setBounds(150, 83, 170, 20);
		frmEvidencijaLota.getContentPane().add(txtRokTrajanja);
		
		textField_cijena = new JTextField();
		textField_cijena.setBounds(150, 133, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_cijena);
		textField_cijena.setColumns(10);
	}

	public void prikazi() {
		frmEvidencijaLota.setVisible(true);
	}
	private boolean validirajPolja() throws WrongInputException {
		String msg = "";
		label_obavijest.setForeground(Color.RED);

		String brojLota = textFieldBroj_lota.getText();

		if (brojLota.length()==0) msg = "Morate unijeti broj lota";
		else if(brojLota.length() < 6 || brojLota.length()>15) msg = "Broj lota mora biti duzine između 6 i 15";
		else if (!Validator.validirajBrojPozitivan(brojLota)) msg = "Broj lota ne moze sadrzavati druge karaktere osim brojeva";
		else if(comboBox.getSelectedIndex() == -1) msg = "Odaberite lijek";
		else if (!Validator.isDateValid(txtRokTrajanja.getText())) msg = "Neispravan datum";
		else if (textField_cijena.getText().length()==0) msg = "Niste unijeli cijenu";
		else if (!Validator.validirajCijenu(textField_cijena.getText())) msg = "Cijena nije u ispravnom formatu";
		
		 if(msg != ""){
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}
	private void refreshPolja() {
		textFieldBroj_lota.setText("");
		comboBox.setSelectedIndex(-1);
		txtRokTrajanja.setText("");
		textField_tezina.setText("");
		textField_cijena.setText("");
		textField_kolicina.setText("");
		textField_kolicina_pakovanje.setText("");
	}
}
