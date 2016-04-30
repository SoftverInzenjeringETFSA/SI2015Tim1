package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class BrisanjeKupca {

	private JFrame frmBrisanjeKupca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKupca window = new BrisanjeKupca();
					window.frmBrisanjeKupca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BrisanjeKupca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBrisanjeKupca = new JFrame();
		frmBrisanjeKupca.setTitle("Brisanje kupca");
		frmBrisanjeKupca.setBounds(100, 100, 291, 123);
		frmBrisanjeKupca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBrisanjeKupca.getContentPane().setLayout(null);
		
		JLabel lblKupac = new JLabel("Kupac:");
		lblKupac.setBounds(10, 11, 69, 14);
		frmBrisanjeKupca.getContentPane().add(lblKupac);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Izaberite kupca"}));
		comboBox.setBounds(89, 8, 112, 20);
		frmBrisanjeKupca.getContentPane().add(comboBox);
		
		JButton btnIzbrii = new JButton("Izbriši");
		btnIzbrii.setBounds(89, 39, 112, 23);
		frmBrisanjeKupca.getContentPane().add(btnIzbrii);
	}

}
