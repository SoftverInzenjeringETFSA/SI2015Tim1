package ba.unsa.etf.si.app.SIDEVS.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.PocetniEkran;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.EvidencijaLotova;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;



import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Login {
	
	final static Logger logger = Logger.getLogger(Login.class);
	
	private JFrame frmLogin;
	private ChangePassword cp;
	private JTextField korisnickoIme;
	private JTextField password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login(); 
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void prikazi(){
		frmLogin.setVisible(true);
	}
	
	public Login() {
		HibernateUtil.getSessionFactory().openSession();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 279, 279);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);
		
		JLabel lblKorisnikoIme = new JLabel("Korisnički email:");
		lblKorisnikoIme.setBounds(64, 32, 131, 14);
		frmLogin.getContentPane().add(lblKorisnikoIme);
		
		JLabel lblPassword = new JLabel("Lozinka:");
		lblPassword.setBounds(64, 90, 131, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		korisnickoIme = new JTextField();
		korisnickoIme.setBounds(62, 59, 133, 20);
		frmLogin.getContentPane().add(korisnickoIme);
		korisnickoIme.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(64, 115, 131, 20);
		frmLogin.getContentPane().add(password);
		password.setColumns(10);
		
		
		
		
		final JLabel labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(10, 216, 253, 14);
		frmLogin.getContentPane().add(labelError);
		
		
		JLabel lblPromijeniPassword = new JLabel("Promijeni lozinku");
		lblPromijeniPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPromijeniPassword.setForeground(Color.BLUE);
		lblPromijeniPassword.setBounds(74, 191, 103, 14);
		frmLogin.getContentPane().add(lblPromijeniPassword);
		lblPromijeniPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cp == null) cp = new ChangePassword();
				cp.prikazi();
				
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(64, 152, 131, 28);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Sessions s = Sessions.getInstance(korisnickoIme.getText(), password.getText());
					//Dodano na dejanovu preporuku !!!
					if(s.getKorisnik().getClass() == Administrator.class){
						ba.unsa.etf.si.app.SIDEVS.View.Admin.PocetniEkran a = new ba.unsa.etf.si.app.SIDEVS.View.Admin.PocetniEkran(s);
					}
					else if(s.getKorisnik().getClass() == Menadzer.class){
						ba.unsa.etf.si.app.SIDEVS.View.Menadzer.PocetniEkran m = new ba.unsa.etf.si.app.SIDEVS.View.Menadzer.PocetniEkran(s);
					}
					else if(s.getKorisnik().getClass() == Radnik.class){
						ba.unsa.etf.si.app.SIDEVS.View.Radnik.PocetniEkran r = new ba.unsa.etf.si.app.SIDEVS.View.Radnik.PocetniEkran(s);
					}
					frmLogin.setVisible(false);
					frmLogin.dispose();
				}
				catch(Exception ex){
					logger.error(ex);
					labelError.setText(ex.getMessage());
				}
			}
		});
		frmLogin.getContentPane().add(btnLogin);
		frmLogin.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{korisnickoIme, lblKorisnikoIme, password, btnLogin, lblPromijeniPassword, lblPassword, labelError}));
	}
}
