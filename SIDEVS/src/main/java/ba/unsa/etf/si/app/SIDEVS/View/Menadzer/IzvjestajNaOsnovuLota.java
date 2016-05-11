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

import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

public class IzvjestajNaOsnovuLota {

	public JFrame frmMenadzerIzvjestajNa;
	private JTable table;
	
	private Sessions _sesija;
    String [] cols=new String[] {"Datum", "Kolicina", "Tip"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
    private JLabel noticeLabel;

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
					e.printStackTrace();
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
		initialize(_sesija);
		frmMenadzerIzvjestajNa.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		Transaction t = s.getSession().beginTransaction();
		
		frmMenadzerIzvjestajNa = new JFrame();
		frmMenadzerIzvjestajNa.setTitle("Izvjestaj na osnovu lota");
		frmMenadzerIzvjestajNa.setBounds(100, 100, 450, 251);
		frmMenadzerIzvjestajNa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestajNa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 414, 73);
		frmMenadzerIzvjestajNa.getContentPane().add(scrollPane);
		
		table = new JTable(model);
		
		scrollPane.setViewportView(table);
		
		final AutoCompleteJComboBox  listaLotova = new AutoCompleteJComboBox(s, Lot.class, "broj_lota");
		//JComboBox listaLotova = new JComboBox();
		listaLotova.setBounds(10, 26, 217, 20);
		frmMenadzerIzvjestajNa.getContentPane().add(listaLotova);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(249, 25, 175, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnPretraga);
		btnPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
                   String lot = listaLotova.getSelectedItem().toString();
                   //ulaz
                   String datum_ulaza = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.datum_ulaza(_sesija, lot);
                   String kolicina_ulaza = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.kolicina_ulaza(_sesija, lot);
				   if (datum_ulaza!="-")
				   {   
					   Object[] row = {datum_ulaza,kolicina_ulaza,"ulaz"};
					   model.addRow(row);
				   }
				   //izlazi
				   List<String> lista_Datuma_izlaza = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.lista_datumi_izlaza(_sesija, lot);
				   List<String> lista_Kolicina_izlaza = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.lista_kolicine_izlaza(_sesija, lot);
				   if (lista_Datuma_izlaza.size()!=0 && lista_Datuma_izlaza.size()==lista_Kolicina_izlaza.size())
				   {
					   for (int i=0; i<lista_Datuma_izlaza.size();i++)
					   {
						   Object[] row1={lista_Datuma_izlaza.get(i),lista_Kolicina_izlaza.get(i),"izlaz"};
						   model.addRow(row1);
					   }
				   }
				   
				   //otpis
				   String kolicina_otpisa=ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.kolicina_otpisa(_sesija,lot);
				   String datum_otpisa=ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.datum_otpisa(_sesija, lot);
				   if (datum_otpisa!="-")
				   {
					   Object[] row2={datum_otpisa, kolicina_otpisa, "otpis"};
					   model.addRow(row2);
				   }
				}
				catch(Exception ex){
					noticeLabel.setForeground(Color.RED);
					noticeLabel.setText(ex.getMessage());
				}
			}
		});
		
		JButton btnGenerisiIzvjestaj = new JButton("Generiši izvještaj");
		btnGenerisiIzvjestaj.setBounds(10, 162, 414, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnGenerisiIzvjestaj);
		
		JLabel lblOdaberiLot = new JLabel("Odaberi lot");
		lblOdaberiLot.setBounds(10, 11, 132, 14);
		frmMenadzerIzvjestajNa.getContentPane().add(lblOdaberiLot);
	}

}
