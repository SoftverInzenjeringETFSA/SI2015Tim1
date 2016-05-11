package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import javax.swing.JFrame;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DodajLotUSkladiste {

	private JFrame frmDodajLotUSkladiste;
	private Sessions s;
	private JTextField textField_kolicina;
	private AutoCompleteJComboBox comboBox;
	private JLabel label_obavijest;

	/**
	 * Create the application.
	 */
	public DodajLotUSkladiste(Sessions s) throws Exception {
		this.s = s;
		initialize();
		frmDodajLotUSkladiste.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajLotUSkladiste = new JFrame();
		frmDodajLotUSkladiste.setResizable(false);
		frmDodajLotUSkladiste.setTitle("Dodaj/ažuriraj lot u skladište");
		frmDodajLotUSkladiste.setBounds(100, 100, 295, 185);
		frmDodajLotUSkladiste.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajLotUSkladiste.getContentPane().setLayout(null);

		JLabel lblLot = new JLabel("Lot:");
		lblLot.setBounds(10, 11, 69, 14);
		frmDodajLotUSkladiste.getContentPane().add(lblLot);

		comboBox = new AutoCompleteJComboBox(s, Lot.class, "broj_lota");
		comboBox.setBounds(89, 8, 166, 20);
		frmDodajLotUSkladiste.getContentPane().add(comboBox);

		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 37, 69, 14);
		frmDodajLotUSkladiste.getContentPane().add(lblSkladite);

		JLabel lblKoliina = new JLabel("Količina:");
		lblKoliina.setBounds(10, 62, 69, 14);
		frmDodajLotUSkladiste.getContentPane().add(lblKoliina);

		final JComboBox comboBox_skladiste = new JComboBox(SkladisteVM.dajSvaSkladista(s));
		comboBox_skladiste.setBounds(89, 34, 166, 20);
		frmDodajLotUSkladiste.getContentPane().add(comboBox_skladiste);

		textField_kolicina = new JTextField();
		textField_kolicina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if (Validator.daLiJeBroj(evt.getKeyChar())) {
					evt.consume();
				}
			}
		});
		textField_kolicina.setBounds(89, 59, 166, 20);
		frmDodajLotUSkladiste.getContentPane().add(textField_kolicina);
		textField_kolicina.setColumns(10);

		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 124, 245, 14);
		frmDodajLotUSkladiste.getContentPane().add(label_obavijest);
		frmDodajLotUSkladiste.setLocationRelativeTo(null);

		JButton btnDodaj = new JButton("Potvrdi");
		btnDodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (validirajUnos()) {
					try {
						LotVM lot = new LotVM(s);
						String text = lot.dodajUPaket(s.getSession().get(Lot.class, comboBox.getSelectedItem().toString()), s.getSession().get(Skladiste.class, Integer.parseInt(comboBox_skladiste.getSelectedItem().toString())), Integer.parseInt(textField_kolicina.getText()));
						label_obavijest.setForeground(Color.green);
						label_obavijest.setText(text);
						refreshPolja();
					} catch (Exception exc) {
						label_obavijest.setForeground(Color.RED);
						label_obavijest.setText(exc.getMessage());
					}
				}

			}

			private void refreshPolja() {
				comboBox.setSelectedItem("");
				comboBox_skladiste.setSelectedIndex(0);
				textField_kolicina.setText("");
			}

			private boolean validirajUnos() {
				String msg = "";
				if (comboBox.getSelectedItem() == null)
					msg = "Odaberite lot";
				else if (comboBox_skladiste.getSelectedItem() == null)
					msg = "Odaberite skladište";
				else if (textField_kolicina.getText().isEmpty())
					msg = "Unesite količinu";
				if (msg != "") {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText(msg);
					return false;
				}
				return true;
			}
		});
		btnDodaj.setBounds(89, 90, 166, 23);
		frmDodajLotUSkladiste.getContentPane().add(btnDodaj);
	}

	public void prikazi() {
		frmDodajLotUSkladiste.setVisible(true);
	}
}
