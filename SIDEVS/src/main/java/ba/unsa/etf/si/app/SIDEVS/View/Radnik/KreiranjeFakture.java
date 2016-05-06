package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KreiranjeFakture {

	private JFrame frmKreiranjeFakture;
	private JTextField textField;
	private JTable table;
	private Sessions s;

	public KreiranjeFakture(Sessions s) throws Exception {
		this.s = s;
		initialize();
		frmKreiranjeFakture.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeFakture = new JFrame();
		frmKreiranjeFakture.setResizable(false);
		frmKreiranjeFakture.setTitle("Kreiranje fakture");
		frmKreiranjeFakture.setBounds(100, 100, 713, 420);
		frmKreiranjeFakture.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeFakture.getContentPane().setLayout(null);
		frmKreiranjeFakture.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 201, 90);
		panel.setBorder(new TitledBorder(null, "Kupac", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmKreiranjeFakture.getContentPane().add(panel);
		panel.setLayout(null);
		
		AutoCompleteJComboBox comboBox_kupac = new AutoCompleteJComboBox(s, Kupac.class, "naziv");
		comboBox_kupac.setBounds(10, 21, 181, 20);
		panel.add(comboBox_kupac);
		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog kupca");
		btnDodajNovogKorisnika.setBounds(10, 52, 181, 23);
		panel.add(btnDodajNovogKorisnika);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 112, 201, 55);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Skladi\u0161te", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_1);
		
		JComboBox comboBox_skladista = new JComboBox(SkladisteVM.dajSvaSkladista(s));
		comboBox_skladista.setBounds(10, 21, 181, 20);
		panel_1.add(comboBox_skladista);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 178, 201, 115);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proizvod", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_2);
		
		AutoCompleteJComboBox comboBox_lijek = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		comboBox_lijek.setBounds(10, 21, 181, 20);
		panel_2.add(comboBox_lijek);
		
		AutoCompleteJComboBox comboBox_lot = new AutoCompleteJComboBox(s, Lot.class, "broj_lota");
		comboBox_lot.setBounds(10, 52, 181, 20);
		panel_2.add(comboBox_lot);
		
		JLabel lblKoliina = new JLabel("Količina (kom):");
		lblKoliina.setBounds(10, 83, 84, 14);
		panel_2.add(lblKoliina);
		
		textField = new JTextField();
		textField.setBounds(96, 83, 95, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnDodajStavku = new JButton("Dodaj stavku");
		btnDodajStavku.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validriajUnosStavke()){
					dodajStavkuUTabelu();
				}
			}

			private void dodajStavkuUTabelu() {
				// TODO Auto-generated method stub
				
			}

			private boolean validriajUnosStavke() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		btnDodajStavku.setBounds(10, 304, 201, 23);
		frmKreiranjeFakture.getContentPane().add(btnDodajStavku);
		
		JButton btnFakturii = new JButton("Fakturiši");
		btnFakturii.setBounds(10, 338, 201, 23);
		frmKreiranjeFakture.getContentPane().add(btnFakturii);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 20, 466, 341);
		frmKreiranjeFakture.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Red. br.", "Skladi\u0161te", "Naziv", "Lot", "Koli\u010Dina", "Jed. cijena", "Cijena"
			}
		));
		
		JLabel label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 372, 677, 14);
		frmKreiranjeFakture.getContentPane().add(label_obavijest);
	}
	
	public void prikazi() {
		frmKreiranjeFakture.setVisible(true);
	}
}
