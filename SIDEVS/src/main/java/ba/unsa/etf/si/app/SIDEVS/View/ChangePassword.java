package ba.unsa.etf.si.app.SIDEVS.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class ChangePassword {

	private JFrame frmChangePassword;
	private Sessions sesija;
	private JPasswordField txtstaripass;
	private JPasswordField txtnovipass;
	private JPasswordField txtponovljenipass;
	private JTextField txt_ime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword();
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangePassword() {
		//this.sesija=s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangePassword = new JFrame();
		frmChangePassword.setBounds(100, 100, 350, 262);
		frmChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel lblStariPassword = new JLabel("Stara lozinka:");
		lblStariPassword.setBounds(26, 72, 99, 14);
		frmChangePassword.getContentPane().add(lblStariPassword);
		
		JLabel lblNovaLozinka = new JLabel("Nova lozinka:");
		lblNovaLozinka.setBounds(26, 107, 99, 14);
		frmChangePassword.getContentPane().add(lblNovaLozinka);
		
		JLabel lblPonovljenaLozinka = new JLabel("Ponovljena lozinka:");
		lblPonovljenaLozinka.setBounds(26, 144, 112, 14);
		frmChangePassword.getContentPane().add(lblPonovljenaLozinka);
		
		txtstaripass = new JPasswordField();
		txtstaripass.setBounds(146, 69, 155, 20);
		frmChangePassword.getContentPane().add(txtstaripass);
		
		txtnovipass = new JPasswordField();
		txtnovipass.setBounds(146, 104, 155, 20);
		frmChangePassword.getContentPane().add(txtnovipass);
		
		txtponovljenipass = new JPasswordField();
		txtponovljenipass.setBounds(146, 141, 155, 20);
		frmChangePassword.getContentPane().add(txtponovljenipass);
		
		JLabel lblObavijest = new JLabel("");
		lblObavijest.setBounds(10, 198, 145, 14);
		frmChangePassword.getContentPane().add(lblObavijest);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnPotvrdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPotvrdi.setBounds(212, 176, 89, 23);
		frmChangePassword.getContentPane().add(btnPotvrdi);
		
		JLabel lblKorisnikoIme = new JLabel("Korisničko ime:");
		lblKorisnikoIme.setBounds(26, 39, 99, 14);
		frmChangePassword.getContentPane().add(lblKorisnikoIme);
		
		txt_ime = new JTextField();
		txt_ime.setBounds(146, 36, 155, 20);
		frmChangePassword.getContentPane().add(txt_ime);
		txt_ime.setColumns(10);
		frmChangePassword.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txt_ime, txtstaripass, txtnovipass, txtponovljenipass, btnPotvrdi, lblStariPassword, lblNovaLozinka, lblPonovljenaLozinka, lblObavijest, lblKorisnikoIme}));
	}
	
	public void prikazi() {
		frmChangePassword.setVisible(true);
	}
}
