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

public class KreiranjeFakture {

	private JFrame frmKreiranjeFakture;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeFakture window = new KreiranjeFakture();
					window.frmKreiranjeFakture.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeFakture() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeFakture = new JFrame();
		frmKreiranjeFakture.setTitle("Kreiranje fakture");
		frmKreiranjeFakture.setBounds(100, 100, 669, 423);
		frmKreiranjeFakture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKreiranjeFakture.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 157, 90);
		panel.setBorder(new TitledBorder(null, "Kupac", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmKreiranjeFakture.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Izaberite kupca"}));
		comboBox.setBounds(10, 21, 134, 20);
		panel.add(comboBox);
		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog kupca");
		btnDodajNovogKorisnika.setBounds(10, 52, 134, 23);
		panel.add(btnDodajNovogKorisnika);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 112, 157, 55);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Skladi\u0161te", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Izaberite skladište"}));
		comboBox_1.setBounds(10, 21, 134, 20);
		panel_1.add(comboBox_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 178, 157, 115);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proizvod", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frmKreiranjeFakture.getContentPane().add(panel_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Izaberite proizvod"}));
		comboBox_2.setBounds(10, 21, 134, 20);
		panel_2.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Izaberite lot"}));
		comboBox_3.setBounds(10, 52, 134, 20);
		panel_2.add(comboBox_3);
		
		JLabel lblKoliina = new JLabel("Količina (kom):");
		lblKoliina.setBounds(10, 83, 84, 14);
		panel_2.add(lblKoliina);
		
		textField = new JTextField();
		textField.setBounds(96, 83, 51, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnDodajStavku = new JButton("Dodaj stavku");
		btnDodajStavku.setBounds(10, 304, 157, 23);
		frmKreiranjeFakture.getContentPane().add(btnDodajStavku);
		
		JButton btnFakturii = new JButton("Fakturiši");
		btnFakturii.setBounds(10, 338, 157, 23);
		frmKreiranjeFakture.getContentPane().add(btnFakturii);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 21, 466, 341);
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
	}
}
