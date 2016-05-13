package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Validation.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.OtpisLijekovaVM;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class OtpisLijeka {

	private Sessions sesija;
	public JFrame frmOtpisLijeka;
	private JTextField kolicinaTextField;
	private Sessions _sesija;
	private JLabel noticeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtpisLijeka window = new OtpisLijeka();
					window.frmOtpisLijeka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OtpisLijeka() {
		initialize(sesija);
	} 
	
	public OtpisLijeka(Sessions s) throws Exception {
		sesija = s;
		initialize(sesija);
		frmOtpisLijeka.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Sessions sesija) {
		
		frmOtpisLijeka = new JFrame();
		frmOtpisLijeka.setTitle("Otpis lijeka");
		frmOtpisLijeka.setBounds(100, 100, 278, 277);
		frmOtpisLijeka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtpisLijeka.getContentPane().setLayout(null);
		frmOtpisLijeka.setLocationRelativeTo(null);
		
		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 11, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblSkladite);
		
		JLabel lblProizvod = new JLabel("Proizvod:");
		lblProizvod.setBounds(10, 36, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblProizvod);
		
		JLabel lblLot = new JLabel("LOT:");
		lblLot.setBounds(10, 106, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblLot);
		
		final JComboBox listaSkladista = new JComboBox();
		listaSkladista.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		listaSkladista.setBounds(101, 8, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaSkladista);
		
		final AutoCompleteJComboBox  listaLijekova = new AutoCompleteJComboBox(sesija, Lijek.class, "naziv");
		listaLijekova.setBounds(101, 33, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaLijekova);
		
		final JComboBox listaLotova = new JComboBox();
		listaLotova.setBounds(101, 103, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaLotova);
		
		JLabel label = new JLabel("________________________________________");
		label.setBounds(10, 131, 272, 14);
		frmOtpisLijeka.getContentPane().add(label);
		
		JLabel lblKoliina = new JLabel("Količina (kom):");
		lblKoliina.setBounds(10, 156, 118, 14);
		frmOtpisLijeka.getContentPane().add(lblKoliina);
		
		kolicinaTextField = new JTextField();
		kolicinaTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (Validator.daLiJeBroj(evt.getKeyChar()))
					evt.consume();
					
			}
		});
		kolicinaTextField.setBounds(138, 153, 110, 20);
		frmOtpisLijeka.getContentPane().add(kolicinaTextField);
		kolicinaTextField.setColumns(10);
		
		JButton btnOtpisi = new JButton("Otpiši");
		btnOtpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
                   String lijek = listaLijekova.getSelectedItem().toString();
                   String skladiste = listaSkladista.getSelectedItem().toString();
                   String lot = listaLotova.getSelectedItem().toString();
                   
                   double d = Double.parseDouble(kolicinaTextField.getText());
                   Integer kolicina = (int)d;
					
                   OtpisLijekovaVM vm = new OtpisLijekovaVM(sesija);
                   boolean  otpisano = vm.otpisLijeka(lijek, lot, skladiste);
                   if(!otpisano) throw new Exception();
                   
               	noticeLabel.setForeground(Color.GREEN);
				noticeLabel.setText("Lijek je uspješno otpisan");
					
				}
				catch(Exception ex){
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
				}
			}
		});
		btnOtpisi.setBounds(10, 184, 238, 23);
		frmOtpisLijeka.getContentPane().add(btnOtpisi);
		
		JButton btnOdaberi = new JButton("Odaberi");
		btnOdaberi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
                   String lijek = listaLijekova.getSelectedItem().toString();
                   String skladiste = listaSkladista.getSelectedItem().toString();
                   
                   Lijek odabraniLijek = (Lijek) sesija.getSession().createCriteria(Lijek.class).
   						add(Restrictions.eq("naziv",  listaLijekova.getSelectedItem().toString())).list().get(0);
   				
   					Skladiste odabranoSkladiste = sesija.getSession().get(Skladiste.class,
   						Integer.parseInt(listaSkladista.getSelectedItem().toString()));
                    		   
                   OtpisLijekovaVM vm = new OtpisLijekovaVM(sesija);
                   List<String> nizLotova = vm.vracaLotove(odabraniLijek, odabranoSkladiste);
                   
                   Object[] l = nizLotova.toArray();
                   
                   for(int i=0;i<l.length;i++){
                	   listaLotova.addItem(l[i]);
                   }
				}
				catch(Exception ex){
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText("Greška, lijek ne postoji");
				}
			}
		});
		btnOdaberi.setBounds(10, 61, 246, 23);
		frmOtpisLijeka.getContentPane().add(btnOdaberi);
		
	    noticeLabel = new JLabel("");
		noticeLabel.setBounds(10, 218, 238, 14);
		frmOtpisLijeka.getContentPane().add(noticeLabel);
	}
}
