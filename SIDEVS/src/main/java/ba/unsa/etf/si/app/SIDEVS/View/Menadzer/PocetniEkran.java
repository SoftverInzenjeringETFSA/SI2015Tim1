package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		lblDnevnikPromjena.setBounds(10, 135, 168, 14);
		frmMenadzerPocetniEkran.getContentPane().add(lblDnevnikPromjena);
		
		JLabel lblKupac = new JLabel("Kupac");
		lblKupac.setBounds(373, 135, 46, 14);
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
					if(tk==null)tk = new TransakcijeKupca(s);
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
					if(el==null)el = new EvidencijaLijeka(s);
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
					if(pl==null)pl = new PretragaLijeka(s);
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
					if(kf==null)kf = new KreiranjeFakture(s);
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
					if(ol==null)ol = new OtpisLijeka(s);
					ol.frmOtpisLijeka.setVisible(true);
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
					if(iop==null)iop = new IzvjestajZaOdredjeniPeriod(s);
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
					if(iui==null)iui = new IzvjestajUlaziIzlazi(s);
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
					if(inol==null)inol = new IzvjestajNaOsnovuLota(s);
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
					if(bk==null)bk = new BrisanjeKupca(s);
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
					if(iop!=null)iop.ugasi();
					if(iui!=null)iui.ugasi();
					if(inol!=null)inol.ugasi();
					if(dk!=null)dk.ugasi();
					if(bk!=null)bk.ugasi();
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
		btnPomoc.setBounds(528, 228, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnPomoc);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(dk==null)dk = new DodajKupca(s);				
					dk.frmDodajKupca.setVisible(true);
				} catch (Exception c) {
					logger.error(c);
				}
			}
		});
		btnDodaj.setBounds(373, 160, 141, 23);
		frmMenadzerPocetniEkran.getContentPane().add(btnDodaj);
	}
}
