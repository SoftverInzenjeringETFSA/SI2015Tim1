package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.app.SIDEVS.View.*;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.*;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;


import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PocetniEkran {
	final static Logger logger = Logger.getLogger(PocetniEkran.class);

	private JFrame frmMenadzerPocetniEkran;
	private Sessions s;
	private TransakcijeKupca tk;
	private EvidencijaLijeka el;
	private PretragaLijeka pl;
	private KreiranjeFakture kf;
	private OtpisLijeka ol;
	private IzvjestajZaOdredjeniPeriod iop;
	private IzvjestajUlaziIzlazi iui;
	private IzvjestajNaOsnovuLota inol;
	private DodajKupca dk;
	private BrisanjeKupca bk;
	private EvidencijaLotova elo;
	private DodajLotUSkladiste dlus;
	private OtpisLijeka oli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetniEkran window = new PocetniEkran();
					window.frmMenadzerPocetniEkran.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PocetniEkran() {
		initialize();
	}
	public PocetniEkran(Sessions s) throws Exception{
		initialize();
		this.s = s;
		frmMenadzerPocetniEkran.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenadzerPocetniEkran = new JFrame();
		frmMenadzerPocetniEkran.setResizable(false);
		frmMenadzerPocetniEkran.setTitle("Početni ekran");
		frmMenadzerPocetniEkran.setBounds(100, 100, 695, 300);
		frmMenadzerPocetniEkran.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMenadzerPocetniEkran.getContentPane().setLayout(null);
		frmMenadzerPocetniEkran.setLocationRelativeTo(null);
		
		
		JLabel lblIzvjestaj = new JLabel("Izvještaj");
		lblIzvjestaj.setBounds(10, 11, 106, 14);
		frmMenadzerPocetniEkran.getContentPane().add(lblIzvjestaj);
		
		JLabel lblLijek = new JLabel("Lijek");
		lblLijek.setBounds(373, 11, 46, 14);
		frmMenadzerPocetniEkran.getContentPane().add(lblLijek);
		
		JLabel lblDnevnikPromjena = new JLabel("Dnevnik promjena");
		lblDnevnikPromjena.setBounds(10, 145, 168, 14);
		frmMenadzerPocetniEkran.getContentPane().add(lblDnevnikPromjena);
		
		JLabel lblKupac = new JLabel("Kupac");
		lblKupac.setBounds(373, 145, 46, 14);
		frmMenadzerPocetniEkran.getContentPane().add(lblKupac);
		
		JButton btnTrenutnoStanjeNa = new JButton("Trenutno stanje na skladištima");
		btnTrenutnoStanjeNa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				TrenutnoStanjeVM t = new TrenutnoStanjeVM(s);
				t.createPDF();
			}
		});
		btnTrenutnoStanjeNa.setBounds(10, 28, 299, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnTrenutnoStanjeNa);
		
		JButton btnTransakcijeKupaca = new JButton("Transakcije kupaca");
		btnTransakcijeKupaca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					tk = new TransakcijeKupca(s);
					tk.prikazi();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
		btnTransakcijeKupaca.setBounds(10, 62, 299, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnTransakcijeKupaca);
		
		JButton btnEvidencijaLijekova = new JButton("Evidencija lijekova");
		btnEvidencijaLijekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					el = new EvidencijaLijeka(s);
					el.prikazi();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
		btnEvidencijaLijekova.setBounds(373, 28, 145, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnEvidencijaLijekova);
		
		JButton btnPretragaLijekova = new JButton("Pretraga lijekova");
		btnPretragaLijekova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPretragaLijekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					pl = new PretragaLijeka(s);
					pl.frmPretragaLijeka.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnPretragaLijekova.setBounds(528, 28, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnPretragaLijekova);
		
		JButton btnKreiranjeFakture = new JButton("Kreiranje fakture");
		btnKreiranjeFakture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					kf = new KreiranjeFakture(s);
					kf.prikazi();
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		});
		btnKreiranjeFakture.setBounds(373, 62, 145, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnKreiranjeFakture);
		
		JButton btnOtpisLijekova = new JButton("Otpis lijekova");
		btnOtpisLijekova.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					oli = new OtpisLijeka(s);
					oli.frmOtpisLijeka.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnOtpisLijekova.setBounds(528, 62, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnOtpisLijekova);
		
		JButton btnDesavanjaZaOdredjeni = new JButton("Dešavanja za određeni vremenski period");
		btnDesavanjaZaOdredjeni.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					iop = new IzvjestajZaOdredjeniPeriod(s);
					iop.prikazi();
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnDesavanjaZaOdredjeni.setBounds(10, 160, 309, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnDesavanjaZaOdredjeni);
		
		JButton btnIzvjestajOUlazima = new JButton("Izvještaj o ulazima i izlazima");
		btnIzvjestajOUlazima.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					iui = new IzvjestajUlaziIzlazi(s);
					iui.prikazi();
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnIzvjestajOUlazima.setBounds(10, 194, 309, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnIzvjestajOUlazima);
		
		JButton btnIzvjestajOUlazima_1 = new JButton("Izvještaj o ulazima i izlazima na osnovu lota");
		btnIzvjestajOUlazima_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					inol = new IzvjestajNaOsnovuLota(s);
					inol.frmMenadzerIzvjestajNa.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnIzvjestajOUlazima_1.setBounds(10, 228, 309, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnIzvjestajOUlazima_1);
		
		JButton btnObrisi = new JButton("Obriši");	
		btnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					bk = new BrisanjeKupca(s);
					bk.frmBrisanjeKupca.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});

		btnObrisi.setBounds(528, 160, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnObrisi);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(373, 228, 145, 23);
		btnOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(tk!=null)tk.ugasi();
					if(el!=null)el.ugasi();
					if(pl!=null)pl.ugasi();
					if(kf!=null)kf.ugasi();
					if(ol!=null)ol.ugasi();
					if(oli!=null)oli.ugasi();
					if(iop!=null)iop.ugasi();
					if(iui!=null)iui.ugasi();
					if(inol!=null)inol.ugasi();
					if(dk!=null)dk.ugasi();
					if(bk!=null)bk.ugasi();
					if(elo!=null) elo.ugasi();
					if(dlus!=null) dlus.ugasi();
					s.ubijSesiju();
					frmMenadzerPocetniEkran.dispose();
					new Login().prikazi();
				} catch (Exception e) {
					logger.error(e);
					//s.getTrasaction().rollback();
				}
			}
		});
		frmMenadzerPocetniEkran.getContentPane().add(btnOdjava);
		
		JButton btnPomoc = new JButton("Pomoć");
		btnPomoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String help = "<html><body width=300'" +
	                    "'><h1>Help</h1>" +
	                    "<p> Za sve informacije dostupni smo vam 24/7.<br><br>" +
	                    "<p> TELEFONSKI BROJ:  033/589-326 <br>" +
	                    "<p> EMAIL:            najbolji_tim@sidevs.ba <br><br>" +
	                    "<p> Ili nas posjetite lično na adresi:" +
	                    "<p> Zmaja od Bosne bb, Kampus Univerziteta u Sarajevu<br><br><br>" +
	                    "<p> Vaš SIDEVS <br>" +
	                    "";

            JOptionPane.showMessageDialog(null, help);
			}
		});
		btnPomoc.setBounds(528, 228, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnPomoc);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dk = new DodajKupca(s);				
					dk.frmDodajKupca.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnDodaj.setBounds(373, 160, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnDodaj);
		
		JButton btnEvidencijaLotova = new JButton("Evidencija lotova");
		btnEvidencijaLotova.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				    elo = new EvidencijaLotova(s);
					elo.prikazi();
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
		btnEvidencijaLotova.setBounds(373, 96, 145, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnEvidencijaLotova);
		
		JButton btnDodajLotU = new JButton("Dodaj/ažuriraj lot u skladištu");
		btnDodajLotU.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					dlus = new DodajLotUSkladiste(s);
					dlus.prikazi();
					}
					catch(Exception ex){
						logger.error(ex);
					}
			}
		});
		btnDodajLotU.setBounds(528, 96, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnDodajLotU);
	}
}
