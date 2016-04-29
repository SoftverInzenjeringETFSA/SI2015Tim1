package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

import javax.swing.JButton;

public class PocetniEkran {

	private JFrame frmAdministratorPocetniEkran;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetniEkran window = new PocetniEkran();
					window.frmAdministratorPocetniEkran.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public PocetniEkran(){
		initialize();
		frmAdministratorPocetniEkran.setVisible(true);
	}
	public PocetniEkran(Sessions s) throws Exception{
		initialize();
		frmAdministratorPocetniEkran.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorPocetniEkran = new JFrame();
		frmAdministratorPocetniEkran.setTitle("Administrator- Pocetni ekran");
		frmAdministratorPocetniEkran.setBounds(100, 100, 340, 216);
		frmAdministratorPocetniEkran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorPocetniEkran.getContentPane().setLayout(null);
		frmAdministratorPocetniEkran.setLocationRelativeTo(null);
		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog korisnika");
		btnDodajNovogKorisnika.setBounds(86, 53, 174, 23);
		frmAdministratorPocetniEkran.getContentPane().add(btnDodajNovogKorisnika);
		
		JButton btnModifikujKorisnika = new JButton("Modifikuj korisnika");
		btnModifikujKorisnika.setBounds(86, 87, 174, 23);
		frmAdministratorPocetniEkran.getContentPane().add(btnModifikujKorisnika);
		
		JButton btnObrisiKorisnika = new JButton("Obrisi korisnika");
		btnObrisiKorisnika.setBounds(86, 121, 174, 23);
		frmAdministratorPocetniEkran.getContentPane().add(btnObrisiKorisnika);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(225, 11, 89, 23);
		frmAdministratorPocetniEkran.getContentPane().add(btnOdjava);
	}

}
