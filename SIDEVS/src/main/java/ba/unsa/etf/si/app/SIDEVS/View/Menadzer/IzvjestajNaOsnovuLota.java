package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.GlavneMetode;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzvjestajNaOsnovuLota {
	final static Logger logger = Logger.getLogger(IzvjestajNaOsnovuLota.class);
	private Sessions _sesija;
	private IzvjestajNaOsnovuLotaVM vm;
	public JFrame frmMenadzerIzvjestajNa;
	private JTable table;
	private AutoCompleteJComboBox  listaLotova;
	
    String [] cols=new String[] {"Datum", "Kolicina", "Tip"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
    private JLabel noticeLabel;
    
    private JLabel label_obavijest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajNaOsnovuLota window = new IzvjestajNaOsnovuLota();
					window.frmMenadzerIzvjestajNa.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajNaOsnovuLota() {
		initialize(_sesija);
	}
	
	public IzvjestajNaOsnovuLota(Sessions s) throws Exception {
		_sesija = s;
		vm = new IzvjestajNaOsnovuLotaVM(s);
		initialize(_sesija);
		frmMenadzerIzvjestajNa.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}
	
	public void ugasi(){
		frmMenadzerIzvjestajNa.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Sessions s) {
		
		frmMenadzerIzvjestajNa = new JFrame();
		frmMenadzerIzvjestajNa.setTitle("Izvjestaj na osnovu lota");
		frmMenadzerIzvjestajNa.setBounds(100, 100, 450, 251);
		frmMenadzerIzvjestajNa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestajNa.getContentPane().setLayout(null);
		frmMenadzerIzvjestajNa.setLocationRelativeTo(null);
		frmMenadzerIzvjestajNa.setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 414, 84);
		frmMenadzerIzvjestajNa.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		table.setEnabled(false);
		
		scrollPane.setViewportView(table);
		
		listaLotova = new AutoCompleteJComboBox(s, Lot.class, "broj_lota");
		//JComboBox listaLotova = new JComboBox();
		listaLotova.setBounds(10, 26, 217, 20);
		frmMenadzerIzvjestajNa.getContentPane().add(listaLotova);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(249, 25, 175, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnPretraga);
		btnPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					clearTable();
					if (validirajPolja()){
						 
						   //novi kod:
						
						
						List<Lot> sviLotovi = GlavneMetode.vratiSveLotove(_sesija);
						   List<Lot> otpisaniLotovi = GlavneMetode.vratiOtpisaneLotove(_sesija);
						  
						   Lot lotic = (Lot) _sesija.getSession().createCriteria(Lot.class).add(Restrictions.like("broj_lota", listaLotova.getSelectedItem().toString())).list().get(0);

						   //ulazi
						   for (Lot l: sviLotovi){
							   if (l.getBroj_lota() == lotic.getBroj_lota()){
								   System.out.println("ovdje");
								   String[] s = {Conversions.dateToString(l.getDatum_ulaza()),GlavneMetode.vratiKolicinuUlaza(l).toString(),"ulaz"};
								   Object[] row = {Conversions.dateToString(l.getDatum_ulaza()),GlavneMetode.vratiKolicinuUlaza(l).toString(),"ulaz"};
								   model.addRow(row);
								   vm.dodaj(s); 
							   }
						   }
						   
						   //izlazi
						   for (Lot l: sviLotovi){
							   if (l.getBroj_lota() == lotic.getBroj_lota()){
								   List<String> datumiIzlaza = vm.lista_datumi_izlaza(_sesija, l.getBroj_lota());
								   for (String d: datumiIzlaza){
									   String[] s = {d,GlavneMetode.vratiKolicinuIzlaza(l).toString(),"izlaz"};
									   Object[] row = {d,GlavneMetode.vratiKolicinuIzlaza(l).toString(),"izlaz"};
									   model.addRow(row);
									   vm.dodaj(s);
								   }
							   }
							  
						   }

						   //otpis	
						   for (Lot l: otpisaniLotovi){
							   if (l.getBroj_lota() == lotic.getBroj_lota()){
								   String[] s = {Conversions.dateToString(l.getDatum_otpisa()),GlavneMetode.vratiKolicinuOtpisanog(l).toString(),"otpis"};
								   Object[] row = {Conversions.dateToString(l.getDatum_otpisa()),GlavneMetode.vratiKolicinuOtpisanog(l).toString(),"otpis"};
								   model.addRow(row);
								   vm.dodaj(s);
							   }   
						   }
						   
						   if (model.getRowCount()==0) 
								label_obavijest.setText("Nema podataka za taj vremenski period.");
					}
				}
				
				catch(Exception ex){
					logger.error(ex);
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
				}
			}
		});
		
		JButton btnGenerisiIzvjestaj = new JButton("Generiši izvještaj");
		btnGenerisiIzvjestaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (model.getRowCount()==0)  {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText("Nije moguce generisati PDF jer nema podataka.");
				}
				else{
					vm.createPDF(listaLotova.getSelectedItem().toString());
					frmMenadzerIzvjestajNa.dispose();
				}
			}
		});
		btnGenerisiIzvjestaj.setBounds(10, 154, 414, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnGenerisiIzvjestaj);
		
		JLabel lblOdaberiLot = new JLabel("Odaberi lot");
		lblOdaberiLot.setBounds(10, 11, 132, 14);
		frmMenadzerIzvjestajNa.getContentPane().add(lblOdaberiLot);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 187, 386, 14);
		frmMenadzerIzvjestajNa.getContentPane().add(label_obavijest);
	}
	
	private boolean validirajPolja() {
		String msg = "";
		label_obavijest.setForeground(Color.RED);
		if (listaLotova.getSelectedIndex()==-1) msg="Morate unijeti lot";
		else msg = Validator.validirajLot(_sesija, listaLotova.getSelectedItem().toString());
		
		if(msg != ""){
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}
	private void clearTable() {
		while(model.getRowCount()>0){
			model.removeRow(0);
		}
	}
}
