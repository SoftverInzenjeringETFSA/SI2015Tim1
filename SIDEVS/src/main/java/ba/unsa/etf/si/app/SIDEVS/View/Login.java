package ba.unsa.etf.si.app.SIDEVS.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

import javax.swing.JButton;
import java.awt.Color;

public class Login {

	private JFrame frmLogin;
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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
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
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(64, 32, 131, 14);
		frmLogin.getContentPane().add(lblKorisnikoIme);
		
		JLabel lblPassword = new JLabel("Password:");
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
		
		JLabel lblPromijeniPassword = new JLabel("Promijeni password");
		lblPromijeniPassword.setBounds(74, 186, 131, 14);
		frmLogin.getContentPane().add(lblPromijeniPassword);
		
		final JLabel labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(10, 216, 253, 14);
		frmLogin.getContentPane().add(labelError);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(64, 152, 131, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Sessions s = Sessions.getInstance(korisnickoIme.getText(), password.getText());
					if(s.getKorisnik().getClass() == Administrator.class){
						new ba.unsa.etf.si.app.SIDEVS.View.Admin.PocetniEkran(s);
					}
					else if(s.getKorisnik().getClass() == Menadzer.class){
						new ba.unsa.etf.si.app.SIDEVS.View.Menadzer.PocetniEkran(s);
					}
					else if(s.getKorisnik().getClass() == Radnik.class){
						new ba.unsa.etf.si.app.SIDEVS.View.Radnik.PocetniEkran(s);
					}
					frmLogin.setVisible(false);
					frmLogin.dispose();
				}
				catch(Exception ex){
					labelError.setText(ex.getMessage());
				}
			}
		});
		frmLogin.getContentPane().add(btnLogin);
	}
}
