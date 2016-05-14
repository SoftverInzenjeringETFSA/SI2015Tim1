package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.View.Login;
import ba.unsa.etf.si.app.SIDEVS.View.Menadzer.EvidencijaLijeka;

import javax.swing.JButton;

public class PocetniEkran {
	
	final static Logger logger = Logger.getLogger(PocetniEkran.class);
	private Sessions _sesija;
	private JFrame frmAdministratorPocetniEkran;
	private DodavanjeKorisnika dk;
	private ModifikacijaKorisnika mk;
	private BrisanjeKorisnika bk;

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
					logger.error(e);
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
		frmAdministratorPocetniEkran.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAdministratorPocetniEkran.getContentPane().setLayout(null);
		frmAdministratorPocetniEkran.setLocationRelativeTo(null);
		
		JButton btnDodajNovogKorisnika = new JButton("Dodaj novog korisnika");
		btnDodajNovogKorisnika.setBounds(86, 53, 174, 23);
		btnDodajNovogKorisnika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(dk == null) dk = new DodavanjeKorisnika(_sesija);
					dk.prikazi();
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnDodajNovogKorisnika);
		
		JButton btnModifikujKorisnika = new JButton("Modifikuj korisnika");
		btnModifikujKorisnika.setBounds(86, 87, 174, 23);
		btnModifikujKorisnika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(mk == null) mk = new ModifikacijaKorisnika(_sesija);
					mk.prikazi();
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnModifikujKorisnika);
		
		JButton btnObrisiKorisnika = new JButton("Obrisi korisnika");
		btnObrisiKorisnika.setBounds(86, 121, 174, 23);
		btnObrisiKorisnika.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(bk == null) bk = new BrisanjeKorisnika(_sesija);
					bk.prikazi();
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnObrisiKorisnika);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(225, 11, 89, 23);
		btnOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					_sesija.ubijSesiju();
					frmAdministratorPocetniEkran.dispose();
					new Login().prikazi();
				} catch (Exception e) {
					logger.error(e);
					//_sesija.getTrasaction().rollback();
					e.printStackTrace();
				}
			}
		});
		frmAdministratorPocetniEkran.getContentPane().add(btnOdjava);
	}

}
