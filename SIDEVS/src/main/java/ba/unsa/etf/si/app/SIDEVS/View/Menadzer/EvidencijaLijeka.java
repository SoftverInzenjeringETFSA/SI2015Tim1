package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.LijekVM;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EvidencijaLijeka {

	private JFrame frmEvidencijaLijeka;
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
					EvidencijaLijeka window = new EvidencijaLijeka();
					window.frmEvidencijaLijeka.setVisible(true);
				} catch (Exception e) {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEvidencijaLijeka = new JFrame();
		frmEvidencijaLijeka.setTitle("Evidencija lijeka");
		frmEvidencijaLijeka.setBounds(100, 100, 401, 205);
		frmEvidencijaLijeka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEvidencijaLijeka.getContentPane().setLayout(null);
		
		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setBounds(10, 11, 106, 14);
		frmEvidencijaLijeka.getContentPane().add(lblNazivProizvoda);
		
		JLabel lblProizvoa = new JLabel("Proizvođač:");
		lblProizvoa.setBounds(10, 36, 106, 14);
		frmEvidencijaLijeka.getContentPane().add(lblProizvoa);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 61, 106, 14);
		frmEvidencijaLijeka.getContentPane().add(lblId);
		
		textField = new JTextField();
		textField.setBounds(126, 8, 181, 20);
		frmEvidencijaLijeka.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 33, 181, 20);
		frmEvidencijaLijeka.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(126, 58, 181, 20);
		frmEvidencijaLijeka.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		final JLabel labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(10, 141, 365, 14);
		frmEvidencijaLijeka.getContentPane().add(labelError);
		
		JButton btnEvidentiraj = new JButton("Evidentiraj");
		btnEvidentiraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//LijekVM l = new LijekVM();
			}
		});
		btnEvidentiraj.setBounds(126, 89, 181, 23);
		frmEvidencijaLijeka.getContentPane().add(btnEvidentiraj);
	}
}
