package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Validation.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.OtpisLijekovaVM;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class OtpisLijeka {
	final static Logger logger = Logger.getLogger(OtpisLijeka.class);

	private Sessions sesija;
	public JFrame frmOtpisLijeka;
	private JLabel noticeLabel;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel() {

	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
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
					logger.error(e);
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
	
	public void ugasi(){
		frmOtpisLijeka.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Sessions sesija) {
		
		frmOtpisLijeka = new JFrame();
		frmOtpisLijeka.setTitle("Otpis lijeka");
		frmOtpisLijeka.setBounds(100, 100, 278, 395);
		frmOtpisLijeka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtpisLijeka.getContentPane().setLayout(null);
		frmOtpisLijeka.setLocationRelativeTo(null);
		frmOtpisLijeka.setResizable(false);
		JLabel lblSkladite = new JLabel("Skladište:");
		lblSkladite.setBounds(10, 11, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblSkladite);
		
		JLabel lblProizvod = new JLabel("Proizvod:");
		lblProizvod.setBounds(10, 36, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblProizvod);
		
		JLabel lblLot = new JLabel("LOT:");
		lblLot.setBounds(10, 98, 81, 14);
		frmOtpisLijeka.getContentPane().add(lblLot);
		
		final JComboBox listaSkladista = new JComboBox();
		listaSkladista.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		listaSkladista.setBounds(101, 8, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaSkladista);
		
		final AutoCompleteJComboBox  listaLijekova = new AutoCompleteJComboBox(sesija, Lijek.class, "naziv");
		listaLijekova.setBounds(101, 33, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaLijekova);
		
		final JComboBox listaLotova = new JComboBox();
		listaLotova.setBounds(101, 95, 147, 20);
		frmOtpisLijeka.getContentPane().add(listaLotova);
		
		JLabel label = new JLabel("________________________________________");
		label.setBounds(10, 116, 272, 14);
		frmOtpisLijeka.getContentPane().add(label);
		
		JButton btnOdaberi = new JButton("Odaberi");
		btnOdaberi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{		
					
				   
					
                   String lijek = listaLijekova.getSelectedItem().toString();
                   String skladiste = listaSkladista.getSelectedItem().toString();
                   
                   Lijek odabraniLijek = (Lijek) sesija.getSession().createCriteria(Lijek.class).
   						add(Restrictions.eq("naziv",  listaLijekova.getSelectedItem().toString())).list().get(0);

   					Skladiste odabranoSkladiste = (Skladiste) sesija.getSession().createCriteria(Skladiste.class).
   	   						add(Restrictions.eq("broj_skladista",  Integer.parseInt(listaSkladista.getSelectedItem().toString()) )).list().get(0);

                   OtpisLijekovaVM vm = new OtpisLijekovaVM(sesija);
                   List<String> nizLotova = vm.vracaLotove(odabraniLijek, odabranoSkladiste);
                   
                   Object[] l = nizLotova.toArray();
                   
                   refreshCombo();
                   
                   for(int i=0;i<l.length;i++){
                	   listaLotova.addItem(l[i]);
                   }
				}
				catch(Exception ex){
					logger.error(ex);
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText("Greška, lijek ne postoji");
				}
			}

			private void refreshCombo() {
				listaLotova.removeAllItems();
				
			}
		});
		btnOdaberi.setBounds(10, 61, 246, 23);
		frmOtpisLijeka.getContentPane().add(btnOdaberi);
		
	    noticeLabel = new JLabel("");
		noticeLabel.setBounds(10, 340, 238, 14);
		frmOtpisLijeka.getContentPane().add(noticeLabel);
		
		table = new JTable(model);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(10, 176, 238, 118);
		model.addColumn("Lijek"); 
		model.addColumn("Lot");
		popuniTabelu();
		table.setSelectionModel(new ForcedListSelectionModel());
		frmOtpisLijeka.getContentPane().add(table);
		
		JLabel lblLotoviKojimaIstie = new JLabel("Lotovi kojima ističe ili je istekao rok:");
		lblLotoviKojimaIstie.setBounds(10, 156, 238, 16);
		frmOtpisLijeka.getContentPane().add(lblLotoviKojimaIstie);
		
		JButton btnOtpisTabela = new JButton("Otpiši");
		btnOtpisTabela.setBounds(10, 305, 238, 25);
		btnOtpisTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{	
					OtpisLijekovaVM vm = new OtpisLijekovaVM(sesija);
					int a = table.getSelectedRow();
					int	b =	table.getSelectedColumn();
					if(a==-1){
						
						 String lijek = listaLijekova.getSelectedItem().toString();
		                   String skladiste = listaSkladista.getSelectedItem().toString();
		                   String lot = listaLotova.getSelectedItem().toString();
						
		                   boolean  otpisano = vm.otpisLijeka(lijek, lot, skladiste);
		                   if(!otpisano) throw new Exception();
					}
					else
					{				
					if(b==0)b=1;
										
					vm.otpisiLijek(table.getValueAt(a, b).toString());
					
					//Osvježi tabelu
	                popuniTabelu();
					}
					noticeLabel.setForeground(Color.GREEN);
					noticeLabel.setText("Lijek je uspješno otpisan");
					
				}
				catch(Exception ex){
					logger.error(ex);
					System.out.println(ex.getMessage());
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
				}
			}
		});
		frmOtpisLijeka.getContentPane().add(btnOtpisTabela);
		
		JLabel lblIliOdaberiteLot = new JLabel("Ili odaberite lot iz tabele:");
		lblIliOdaberiteLot.setBounds(64, 131, 147, 14);
		frmOtpisLijeka.getContentPane().add(lblIliOdaberiteLot);
	}
	
	public void popuniTabelu(){
		model.setRowCount(0);  
		model.addRow(new Object[]{"Lijek", "Lot"});
		Transaction t = sesija.getSession().beginTransaction();
		Date myDate = new Date();
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.lt("rok_trajanja",myDate)).list();
		for(int i = 0; i < lotovi.size(); i++){
			Lijek tmp = lotovi.get(i).getLijek();
			List<Lijek> lijek =  sesija.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", tmp.getNaziv())).list();	
			model.addRow(new Object[]{lijek.get(0).getNaziv(), lotovi.get(i).getBroj_lota()});
		}
	}
	public class ForcedListSelectionModel extends DefaultListSelectionModel {
	    public ForcedListSelectionModel () {
	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    }

	    @Override
	    public void clearSelection() {
	    }

	    @Override
	    public void removeSelectionInterval(int index0, int index1) {
	    }
	}
}
