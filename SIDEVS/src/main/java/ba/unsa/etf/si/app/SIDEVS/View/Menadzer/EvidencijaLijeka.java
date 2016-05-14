package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.LijekVM;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EvidencijaLijeka {
	final static Logger logger = Logger.getLogger(EvidencijaLijeka.class);
	private JFrame frmEvidencijaLijeka;
	private JTextField textFieldNaziv;
	private JTextField textFieldProizvodjac;
	private JLabel labelObavijest;
	private Sessions s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvidencijaLijeka window = new EvidencijaLijeka();
					window.frmEvidencijaLijeka.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EvidencijaLijeka() {
		initialize();
	}

	public EvidencijaLijeka(Sessions s) throws Exception {
		initialize();
		this.s = s;
		frmEvidencijaLijeka.setVisible(true);
		if (!s.daLiPostoji()) {
			
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaLijeka = new JFrame();
		frmEvidencijaLijeka.setResizable(false);
		frmEvidencijaLijeka.setTitle("Evidencija lijeka");
		frmEvidencijaLijeka.setBounds(100, 100, 401, 205);
		frmEvidencijaLijeka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEvidencijaLijeka.getContentPane().setLayout(null);
		frmEvidencijaLijeka.setLocationRelativeTo(null);

		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setBounds(31, 36, 94, 14);
		frmEvidencijaLijeka.getContentPane().add(lblNazivProizvoda);

		JLabel lblProizvoa = new JLabel("Proizvođač:");
		lblProizvoa.setBounds(31, 67, 82, 14);
		frmEvidencijaLijeka.getContentPane().add(lblProizvoa);

		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(126, 33, 181, 20);
		frmEvidencijaLijeka.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);

		textFieldProizvodjac = new JTextField();
		textFieldProizvodjac.setBounds(126, 64, 181, 20);
		frmEvidencijaLijeka.getContentPane().add(textFieldProizvodjac);
		textFieldProizvodjac.setColumns(10);

		labelObavijest = new JLabel("");
		labelObavijest.setBounds(10, 141, 365, 14);
		frmEvidencijaLijeka.getContentPane().add(labelObavijest);

		JButton btnEvidentiraj = new JButton("Evidentiraj");
		btnEvidentiraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (validirajPolja()) {
						LijekVM l = new LijekVM(s);
						l.dodajLijek((long) 1, textFieldNaziv.getText(),textFieldProizvodjac.getText());
						textFieldNaziv.setText("");
						textFieldProizvodjac.setText("");
						labelObavijest.setForeground(Color.green);
						labelObavijest.setText("Uspješno ste kreirali lijek");
					}
				} catch (Exception e) {
					logger.error(e);
					labelObavijest.setForeground(Color.RED);
					labelObavijest.setText(e.getMessage());
				}
			}
		});
		btnEvidentiraj.setBounds(126, 107, 181, 23);
		frmEvidencijaLijeka.getContentPane().add(btnEvidentiraj);
	}

	private Boolean validirajPolja() {
		String msg = "";
		labelObavijest.setForeground(Color.RED);
		if(textFieldNaziv.getText().isEmpty()) msg = "Popunite naziv proizvoda";
		else if(textFieldProizvodjac.getText().isEmpty()) msg = "Popunite naziv proizvođača proizvoda";		
		if(msg != ""){
			labelObavijest.setText(msg);
			return false;
		}
		return true;
	}

	public void prikazi() {
		frmEvidencijaLijeka.setVisible(true);
	}
}
