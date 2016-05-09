package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JButton;

public class BrisanjeKorisnika {
	
	private Sessions _sesija;
	private JFrame frmAdministratorBrisanjeKorisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKorisnika window = new BrisanjeKorisnika();
					window.frmAdministratorBrisanjeKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BrisanjeKorisnika() {
		initialize(_sesija);
	}
	public BrisanjeKorisnika(Sessions s) throws Exception {
		_sesija = s;
		initialize(_sesija);
		frmAdministratorBrisanjeKorisnika.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		
		Transaction t = s.getSession().beginTransaction();
		
		frmAdministratorBrisanjeKorisnika = new JFrame();
		frmAdministratorBrisanjeKorisnika.setTitle("Administrator- Brisanje korisnika");
		frmAdministratorBrisanjeKorisnika.setBounds(100, 100, 385, 195);
		frmAdministratorBrisanjeKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministratorBrisanjeKorisnika.getContentPane().setLayout(null);
		frmAdministratorBrisanjeKorisnika.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Odaberi korisnika");
		label.setBounds(117, 26, 169, 14);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(label);
		
		final AutoCompleteJComboBox  listaKorisnikaBrisanje = new AutoCompleteJComboBox(s, Korisnik.class, "ime");
		listaKorisnikaBrisanje.setBounds(117, 51, 127, 20);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(listaKorisnikaBrisanje);
		
		/*JComboBox listaKorisnika = new JComboBox();
		listaKorisnika.setBounds(117, 51, 127, 20);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(listaKorisnika);*/
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.setBounds(117, 82, 127, 23);
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					String txt = listaKorisnikaBrisanje.getSelectedItem().toString();	
					String[] parts = txt.split(" ");
					String part1 = parts[0];
					String part2 = parts[1];
					
					boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(_sesija, part1, part2);		
					if(!state) throw new Exception();

					JOptionPane.showMessageDialog(null, "Korisnik uspješno obrisan", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);				
				}
				catch(Exception ex){
					System.out.println(ex.toString());
					JOptionPane.showMessageDialog(null, "Došlo je do greške u brisanju", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				listaKorisnikaBrisanje.setSelectedItem("");
			}
		});
		frmAdministratorBrisanjeKorisnika.getContentPane().add(btnObrisi);
	}
	
	public void prikazi() {
		frmAdministratorBrisanjeKorisnika.setVisible(true);
	}

}
