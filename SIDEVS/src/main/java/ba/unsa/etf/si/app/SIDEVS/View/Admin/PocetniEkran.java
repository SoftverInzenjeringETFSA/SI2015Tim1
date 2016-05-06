package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

import javax.swing.JButton;

public class PocetniEkran {
	private Sessions _sesija;
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
		_sesija = s;
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
		btnDodajNovogKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{				
					ba.unsa.etf.si.app.SIDEVS.View.Admin.DodavanjeKorisnika dk = new ba.unsa.etf.si.app.SIDEVS.View.Admin.DodavanjeKorisnika(_sesija);
								
					//frmAdministratorPocetniEkran.setVisible(false);
					//frmAdministratorPocetniEkran.dispose();
				}
				catch(Exception ex){
					//EXCEPTION
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnDodajNovogKorisnika);
		
		JButton btnModifikujKorisnika = new JButton("Modifikuj korisnika");
		btnModifikujKorisnika.setBounds(86, 87, 174, 23);
		btnModifikujKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{				
					ba.unsa.etf.si.app.SIDEVS.View.Admin.ModifikacijaKorisnika mk = new ba.unsa.etf.si.app.SIDEVS.View.Admin.ModifikacijaKorisnika(_sesija);
								
					//frmAdministratorPocetniEkran.setVisible(false);
					//frmAdministratorPocetniEkran.dispose();
				}
				catch(Exception ex){
					//EXCEPTION
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnModifikujKorisnika);
		
		JButton btnObrisiKorisnika = new JButton("Obrisi korisnika");
		btnObrisiKorisnika.setBounds(86, 121, 174, 23);
		btnObrisiKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{				
					ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika ok = new ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika(_sesija);
								
					//frmAdministratorPocetniEkran.setVisible(false);
					//frmAdministratorPocetniEkran.dispose();
				}
				catch(Exception ex){
					//EXCEPTION
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnObrisiKorisnika);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(225, 11, 89, 23);
		frmAdministratorPocetniEkran.getContentPane().add(btnOdjava);
	}

}
