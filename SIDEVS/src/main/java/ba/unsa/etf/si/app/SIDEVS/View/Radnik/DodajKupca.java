package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DodajKupca {

	private JFrame frmDodajKupca;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajKupca window = new DodajKupca();
					window.frmDodajKupca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodajKupca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajKupca = new JFrame();
		frmDodajKupca.setTitle("Dodaj kupca");
		frmDodajKupca.setBounds(100, 100, 302, 196);
		frmDodajKupca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodajKupca.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 67, 14);
		frmDodajKupca.getContentPane().add(lblId);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setBounds(10, 36, 91, 14);
		frmDodajKupca.getContentPane().add(lblImeIPrezime);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		lblNewLabel.setBounds(10, 61, 46, 14);
		frmDodajKupca.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(111, 33, 141, 20);
		frmDodajKupca.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 8, 141, 20);
		frmDodajKupca.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(111, 58, 141, 20);
		frmDodajKupca.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(111, 103, 141, 23);
		frmDodajKupca.getContentPane().add(btnDodaj);
	}

}
