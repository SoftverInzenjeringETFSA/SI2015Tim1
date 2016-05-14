package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class BrisanjeKupca {
	final static Logger logger = Logger.getLogger(BrisanjeKupca.class);
    
	private Sessions _sesija;
	public JFrame frmBrisanjeKupca;
    private JLabel noticeLabel;
    private  AutoCompleteJComboBox  listaKupaca;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrisanjeKupca window = new BrisanjeKupca();
					window.frmBrisanjeKupca.setVisible(true);
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
	public BrisanjeKupca() {
		initialize(_sesija);
	}
	
	public BrisanjeKupca(Sessions s) throws Exception {
		_sesija = s;
		initialize(_sesija);
		frmBrisanjeKupca.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		
		Transaction t = s.getSession().beginTransaction();
		
		frmBrisanjeKupca = new JFrame();
		frmBrisanjeKupca.setTitle("Brisanje kupca");
		frmBrisanjeKupca.setBounds(100, 100, 310, 139);
		frmBrisanjeKupca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBrisanjeKupca.getContentPane().setLayout(null);
		frmBrisanjeKupca.setLocationRelativeTo(null);
		
		JLabel lblKupac = new JLabel("Kupac:");
		lblKupac.setBounds(10, 11, 69, 14);
		frmBrisanjeKupca.getContentPane().add(lblKupac);
		
		listaKupaca = new AutoCompleteJComboBox(s, Kupac.class, "naziv");
		listaKupaca.setBounds(89, 8, 112, 20);
		frmBrisanjeKupca.getContentPane().add(listaKupaca);
		
	   
		
		JButton btnIzbriši = new JButton("Izbriši");
		btnIzbriši.setBounds(89, 39, 112, 23);
		btnIzbriši.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					if (validirajPolja()){
						String txt = listaKupaca.getSelectedItem().toString();	
						
						boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKupcaVM.BrisiKupca(_sesija, txt);		
						if(!state) throw new Exception();
						noticeLabel.setForeground(Color.GREEN);
						noticeLabel.setText("Kupac je uspješno obrisan");
					}
				}
				catch(Exception ex){
					logger.error(ex);
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
					
				}
				listaKupaca.setSelectedItem("");
			}
		});
		frmBrisanjeKupca.getContentPane().add(btnIzbriši);
		
		 noticeLabel = new JLabel("");
			noticeLabel.setBounds(10, 75, 274, 14);
			frmBrisanjeKupca.getContentPane().add(noticeLabel);
		
	}
	
	private boolean validirajPolja() {
		String msg = "";
		if (listaKupaca.getSelectedItem()==null) msg="Morate unijeti kupca";
		else msg = Validator.validirajKupca(_sesija, listaKupaca.getSelectedItem().toString());
		if(msg != ""){
			noticeLabel.setForeground(Color.red);
			noticeLabel.setText(msg);
			return false;
		}
		return true;
	}
}
