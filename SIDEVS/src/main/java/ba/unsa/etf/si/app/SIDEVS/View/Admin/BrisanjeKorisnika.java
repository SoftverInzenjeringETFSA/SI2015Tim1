package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;

import javax.swing.JButton;

public class BrisanjeKorisnika {
	
	final static Logger logger = Logger.getLogger(BrisanjeKorisnika.class);
	
	private Sessions _sesija;
	private JFrame frmAdministratorBrisanjeKorisnika;
	private AutoCompleteJComboBox  listaKorisnikaBrisanje ;
	private JLabel noticeLabel;
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
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public void ugasi(){
		frmAdministratorBrisanjeKorisnika.dispose();
	}
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
		frmAdministratorBrisanjeKorisnika.setResizable(false);
		
		JLabel label = new JLabel("Odaberi korisnika");
		label.setBounds(117, 26, 169, 14);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(label);
		
		listaKorisnikaBrisanje = new AutoCompleteJComboBox(s, Korisnik.class, "email");
		listaKorisnikaBrisanje.setBounds(117, 51, 127, 20);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(listaKorisnikaBrisanje);

		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.setBounds(117, 82, 127, 23);
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					if (validirajPolja()){
						noticeLabel.setText("");
						
						Korisnik k = (Korisnik) _sesija.getSession().createCriteria(Korisnik.class).
								add(Restrictions.eq("email", listaKorisnikaBrisanje.getSelectedItem())).list().get(0);
						
						boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(_sesija, k.getIme(), k.getPrezime());		
						if(!state) throw new Exception("Brisanje nije uspjelo");

									
						noticeLabel.setForeground(Color.decode("#008000"));
						noticeLabel.setText("Korisnik je uspješno obrisan");
					}
									
				}
				
			catch(Exception ex){
				noticeLabel.setForeground(Color.red);
				noticeLabel.setText(ex.getMessage());
				logger.error(ex);
				
			}
				listaKorisnikaBrisanje.setSelectedItem("");
			}
		});
		frmAdministratorBrisanjeKorisnika.getContentPane().add(btnObrisi);
		
		noticeLabel = new JLabel("");
		noticeLabel.setBounds(10, 131, 335, 14);
		frmAdministratorBrisanjeKorisnika.getContentPane().add(noticeLabel);
	}
	
	private boolean validirajPolja() {
		String msg = "";
		if (listaKorisnikaBrisanje.getSelectedItem()==null) msg="Morate unijeti korisnika";
		else msg = Validator.validirajKorisnika(_sesija, listaKorisnikaBrisanje.getSelectedItem().toString());
		if(msg != ""){
			noticeLabel.setForeground(Color.red);
			noticeLabel.setText(msg);
			return false;
		}
		return true;
	}
	
	public void prikazi() {
		frmAdministratorBrisanjeKorisnika.setVisible(true);
	}

}
