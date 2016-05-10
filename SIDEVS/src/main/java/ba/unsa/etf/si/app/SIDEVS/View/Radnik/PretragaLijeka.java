package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PretragaLijeka {

	public JFrame frmPretragaLijeka;
	private JTable table;
	private Sessions _sesija;
    String [] cols=new String[] {"Skladi\u0161te", "Koli\u010Dina"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
    private JLabel noticeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PretragaLijeka window = new PretragaLijeka();
					window.frmPretragaLijeka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PretragaLijeka() {
		initialize(_sesija);
	}
    
	public PretragaLijeka(Sessions s) throws Exception {
		_sesija = s;
		initialize(_sesija);
		frmPretragaLijeka.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		Transaction t = s.getSession().beginTransaction();
		
		frmPretragaLijeka = new JFrame();
		frmPretragaLijeka.setTitle("Pretraga lijeka");
		frmPretragaLijeka.setBounds(100, 100, 335, 322);
		frmPretragaLijeka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPretragaLijeka.getContentPane().setLayout(null);
		frmPretragaLijeka.setLocationRelativeTo(null);
		
		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setBounds(10, 0, 123, 14);
		frmPretragaLijeka.getContentPane().add(lblNazivProizvoda);
		
		final AutoCompleteJComboBox  listaProizvoda = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		//JComboBox listaProizvoda = new JComboBox();
		listaProizvoda.setBounds(10, 14, 145, 20);
		frmPretragaLijeka.getContentPane().add(listaProizvoda);
		
		final JComboBox listaSkladista = new JComboBox();
		listaSkladista.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		listaSkladista.setBounds(184, 14, 125, 20);
		frmPretragaLijeka.getContentPane().add(listaSkladista);
		
		JButton btnPretrazi = new JButton("Pretraži");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
                   String lijek = listaProizvoda.getSelectedItem().toString();
                   String skladiste = listaSkladista.getSelectedItem().toString();
									   
					long kolicina = ba.unsa.etf.si.app.SIDEVS.ViewModel.PretragaLijekovaVM.kolicinaLijeka(_sesija,lijek,skladiste);
					
					Object[] row = {skladiste,kolicina};
					model.addRow(row);
				}
				catch(Exception ex){
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
				}
			}
		});
		btnPretrazi.setBounds(10, 45, 299, 23);
		frmPretragaLijeka.getContentPane().add(btnPretrazi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 299, 171);
		frmPretragaLijeka.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblSkladiste = new JLabel("Skladiste");
		lblSkladiste.setBounds(184, 0, 46, 14);
		frmPretragaLijeka.getContentPane().add(lblSkladiste);
		
		noticeLabel = new JLabel("");
		noticeLabel.setBounds(10, 261, 299, 14);
		frmPretragaLijeka.getContentPane().add(noticeLabel);
		
		
		
	}
}
