package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

import javax.swing.JButton;

public class PocetniEkran {

	private JFrame frmPoetniEkran;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetniEkran window = new PocetniEkran();
					window.frmPoetniEkran.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
		frmPoetniEkran.setTitle("Početni ekran");
		frmPoetniEkran.setBounds(100, 100, 450, 300);
		frmPoetniEkran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPoetniEkran.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "LijekVM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 98);
		frmPoetniEkran.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEvidencijaLotova = new JButton("Evidencija lotova");
		btnEvidencijaLotova.setBounds(10, 21, 195, 23);
		panel.add(btnEvidencijaLotova);
		
		JButton btnPretragaLijekova = new JButton("Pretraga lijekova");
		btnPretragaLijekova.setBounds(209, 21, 195, 23);
		panel.add(btnPretragaLijekova);
		
		JButton btnKreiranjeFaktura = new JButton("Kreiranje faktura");
		btnKreiranjeFaktura.setBounds(10, 55, 195, 23);
		panel.add(btnKreiranjeFaktura);
		
		JButton btnOtpisLijekova = new JButton("Otpis lijekova");
		btnOtpisLijekova.setBounds(209, 55, 195, 23);
		panel.add(btnOtpisLijekova);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Kupac", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 114, 414, 77);
		frmPoetniEkran.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnObrii = new JButton("Obriši");
		btnObrii.setBounds(10, 28, 394, 23);
		panel_1.add(btnObrii);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(20, 211, 195, 23);
		frmPoetniEkran.getContentPane().add(btnOdjava);
		
		JButton btnPomo = new JButton("Pomoć");
		btnPomo.setBounds(218, 211, 195, 23);
		frmPoetniEkran.getContentPane().add(btnPomo);
	}

}
