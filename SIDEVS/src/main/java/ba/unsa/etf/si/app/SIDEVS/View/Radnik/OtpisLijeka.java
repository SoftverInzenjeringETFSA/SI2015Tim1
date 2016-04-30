package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JButton;

public class OtpisLijeka {

	private JFrame frmOtpisLijeka;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtpisLijeka window = new OtpisLijeka();
					window.frmOtpisLijeka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OtpisLijeka() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOtpisLijeka = new JFrame();
		frmOtpisLijeka.setTitle("Otpis lijeka");
		frmOtpisLijeka.setBounds(100, 100, 290, 236);
		frmOtpisLijeka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOtpisLijeka.getContentPane().setLayout(null);
		
		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 11, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblSkladite);
		
		JLabel lblProizvod = new JLabel("Proizvod:");
		lblProizvod.setBounds(10, 36, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblProizvod);
		
		JLabel lblLot = new JLabel("LOT:");
		lblLot.setBounds(10, 61, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblLot);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Izaberite skladište"}));
		comboBox.setBounds(101, 8, 147, 20);
		frmOtpisLijeka.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Izaberite proizvod"}));
		comboBox_1.setBounds(101, 33, 147, 20);
		frmOtpisLijeka.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Izaberite lot"}));
		comboBox_2.setBounds(101, 58, 147, 20);
		frmOtpisLijeka.getContentPane().add(comboBox_2);
		
		JLabel label = new JLabel("________________________________________");
		label.setBounds(10, 86, 272, 14);
		frmOtpisLijeka.getContentPane().add(label);
		
		JLabel lblKoliina = new JLabel("Količina (kom):");
		lblKoliina.setBounds(10, 111, 118, 14);
		frmOtpisLijeka.getContentPane().add(lblKoliina);
		
		textField = new JTextField();
		textField.setBounds(138, 111, 110, 20);
		frmOtpisLijeka.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOtpii = new JButton("Otpiši");
		btnOtpii.setBounds(138, 142, 110, 23);
		frmOtpisLijeka.getContentPane().add(btnOtpii);
	}
}
