package ba.unsa.etf.si.app.SIDEVS.Interfejsi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdminBrisanjeKorisnika {

	private JFrame frmAdministratorBrisanjeKorisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminBrisanjeKorisnika window = new AdminBrisanjeKorisnika();
					window.frmAdministratorBrisanjeKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminBrisanjeKorisnika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorBrisanjeKorisnika = new JFrame();
		frmAdministratorBrisanjeKorisnika.setTitle("Administrator- Brisanje korisnika");
		frmAdministratorBrisanjeKorisnika.setBounds(100, 100, 385, 195);
		frmAdministratorBrisanjeKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorBrisanjeKorisnika.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Odaberi korisnika");
		label.setBounds(117, 26, 169, 14);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(label);
		
		JComboBox listaKorisnika = new JComboBox();
		listaKorisnika.setBounds(117, 51, 127, 20);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(listaKorisnika);
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.setBounds(117, 82, 127, 23);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(btnObrisi);
	}

}
