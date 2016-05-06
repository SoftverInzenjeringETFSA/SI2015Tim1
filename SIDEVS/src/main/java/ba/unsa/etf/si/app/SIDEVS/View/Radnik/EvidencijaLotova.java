package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class EvidencijaLotova {

	private JFrame frmEvidencijaLota;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Sessions s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvidencijaLotova window = new EvidencijaLotova();
					window.frmEvidencijaLota.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EvidencijaLotova() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaLota = new JFrame();
		frmEvidencijaLota.setTitle("Evidencija lota");
		frmEvidencijaLota.setBounds(100, 100, 346, 261);
		frmEvidencijaLota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEvidencijaLota.getContentPane().setLayout(null);
		
		JLabel lblBrojLota = new JLabel("Broj lota:");
		lblBrojLota.setBounds(10, 11, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblBrojLota);
		
		JLabel lblLijek = new JLabel("Lijek:");
		lblLijek.setBounds(10, 36, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblLijek);
		
		JLabel lblRokTrajanja = new JLabel("Rok trajanja:");
		lblRokTrajanja.setBounds(10, 61, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblRokTrajanja);
		
		JLabel lblTeina = new JLabel("Težina:");
		lblTeina.setBounds(10, 86, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblTeina);
		
		JLabel lblUlaznaCijena = new JLabel("Ulazna cijena:");
		lblUlaznaCijena.setBounds(10, 111, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblUlaznaCijena);
		
		JLabel lblKoliinatableta = new JLabel("Količina (tableta):");
		lblKoliinatableta.setBounds(10, 136, 99, 14);
		frmEvidencijaLota.getContentPane().add(lblKoliinatableta);
		
		textField = new JTextField();
		textField.setBounds(119, 8, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField);
		textField.setColumns(10);
		
		AutoCompleteJComboBox comboBox = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		comboBox.setBounds(119, 33, 170, 20);
		frmEvidencijaLota.getContentPane().add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 58, 32, 20);
		frmEvidencijaLota.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel(".");
		label.setBounds(153, 61, 14, 14);
		frmEvidencijaLota.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(161, 58, 32, 20);
		frmEvidencijaLota.getContentPane().add(textField_2);
		
		JLabel label_1 = new JLabel(".");
		label_1.setBounds(195, 61, 14, 14);
		frmEvidencijaLota.getContentPane().add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(203, 58, 86, 20);
		frmEvidencijaLota.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(119, 83, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(119, 108, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(119, 133, 170, 20);
		frmEvidencijaLota.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnEvidentiraj = new JButton("Evidentiraj");
		btnEvidentiraj.setBounds(119, 169, 170, 23);
		frmEvidencijaLota.getContentPane().add(btnEvidentiraj);
	}

}
