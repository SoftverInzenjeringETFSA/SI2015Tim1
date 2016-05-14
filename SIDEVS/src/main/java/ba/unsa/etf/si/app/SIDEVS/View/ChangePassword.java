package ba.unsa.etf.si.app.SIDEVS.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;

public class ChangePassword {
	
	final static Logger logger = Logger.getLogger(ChangePassword.class);

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
					logger.error(e);
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
		frmChangePassword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		frmChangePassword.setLocationRelativeTo(null);
		
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
				try {
					boolean b = ba.unsa.etf.si.app.SIDEVS.ViewModel.ChangePasswordVM.ChangePassword(txt_ime.getText(), txtstaripass.getPassword(), txtnovipass.getPassword(), txtponovljenipass.getPassword());
					if(!b) throw new Exception();
					JOptionPane.showMessageDialog(null, "Operacija uspješno završena", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);	
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Došlo je do greške", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);		
					//_sesija.getTrasaction().rollback();
					logger.error(e);
				}
				resetContent();
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
	public void resetContent(){
		txt_ime.setText(null);
		txtstaripass.setText(null);
		txtnovipass.setText(null);
		txtponovljenipass.setText(null);
	}
}
