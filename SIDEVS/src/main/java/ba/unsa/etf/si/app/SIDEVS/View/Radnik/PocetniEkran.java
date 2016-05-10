package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.View.Login;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.EvidencijaLotova;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.DodajKupca;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PocetniEkran {

	private JFrame frmPoetniEkran;
	private Sessions s;
	private EvidencijaLotova el;
	private KreiranjeFakture kf;
	private BrisanjeKupca bk;
	private PretragaLijeka pl;
    private OtpisLijeka ol;
    private DodajKupca dk;


	/**
	 * Create the application.
	 */
	public PocetniEkran(Sessions s) throws Exception{
		initialize();
		this.s = s;
		frmPoetniEkran.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPoetniEkran = new JFrame();
		frmPoetniEkran.setResizable(false);
		frmPoetniEkran.setTitle("Početni ekran");
		frmPoetniEkran.setBounds(100, 100, 450, 300);
		frmPoetniEkran.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmPoetniEkran.getContentPane().setLayout(null);
		frmPoetniEkran.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lijek", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 98);
		frmPoetniEkran.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEvidencijaLotova = new JButton("Evidencija lotova");
		btnEvidencijaLotova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(el == null) el = new EvidencijaLotova(s);
					el.prikazi();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEvidencijaLotova.setBounds(10, 21, 195, 23);
		panel.add(btnEvidencijaLotova);
		
		JButton btnPretragaLijekova = new JButton("Pretraga lijekova");
		btnPretragaLijekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(pl == null) pl = new PretragaLijeka(s);
					pl.frmPretragaLijeka.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		btnPretragaLijekova.setBounds(209, 21, 195, 23);
		panel.add(btnPretragaLijekova);
		
		JButton btnKreiranjeFaktura = new JButton("Kreiranje faktura");
		btnKreiranjeFaktura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(kf == null) kf = new KreiranjeFakture(s);
					kf.prikazi();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnKreiranjeFaktura.setBounds(10, 55, 195, 23);
		panel.add(btnKreiranjeFaktura);
		
		JButton btnOtpisLijekova = new JButton("Otpis lijekova");
		btnOtpisLijekova.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(ol == null) ol = new OtpisLijeka(s);
					ol.frmOtpisLijeka.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		btnOtpisLijekova.setBounds(209, 55, 195, 23);
		panel.add(btnOtpisLijekova);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Kupac", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 114, 414, 77);
		frmPoetniEkran.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnObrisi = new JButton("Obriši");
		btnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(bk == null) bk = new BrisanjeKupca(s);
					bk.frmBrisanjeKupca.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		btnObrisi.setBounds(210, 28, 194, 23);
		panel_1.add(btnObrisi);
		
		JButton btnDodajKupca = new JButton("Dodaj");
		btnDodajKupca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (dk==null) dk = new DodajKupca(s);				
					dk.frmDodajKupca.setVisible(true);
				} catch (Exception c) {
					c.printStackTrace();
				}
			}
		});
		btnDodajKupca.setBounds(10, 28, 194, 23);
		panel_1.add(btnDodajKupca);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(20, 211, 195, 23);
		btnOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					s.ubijSesiju();
					frmPoetniEkran.dispose();
					new Login().prikazi();
				} catch (Exception e) {
					//s.getTrasaction().rollback();
					e.printStackTrace();
				}
			}
		});
		frmPoetniEkran.getContentPane().add(btnOdjava);
		
		JButton btnPomo = new JButton("Pomoć");
		btnPomo.setBounds(218, 211, 195, 23);
		frmPoetniEkran.getContentPane().add(btnPomo);
	}
}
