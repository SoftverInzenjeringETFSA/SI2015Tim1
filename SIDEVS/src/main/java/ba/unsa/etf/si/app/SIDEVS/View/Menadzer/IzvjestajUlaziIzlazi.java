package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.ObrisanLot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajUlaziIzlaziVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaKupcaVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzvjestajUlaziIzlazi {
	private Sessions sesija;
	private JFrame frmMenadzerIzvjestaO;
	private JTable table;
	String [] cols=new String[] {"Datum", "Tip","Lot","Količina","Trenutno stanje"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
	private JFormattedTextField datumOd;
	private JFormattedTextField datumDo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajUlaziIzlazi window = new IzvjestajUlaziIzlazi();
					window.frmMenadzerIzvjestaO.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajUlaziIzlazi() {
		initialize(sesija);
	}
	public IzvjestajUlaziIzlazi(Sessions sesija) throws Exception{
		this.sesija = sesija;
		initialize(sesija);
		frmMenadzerIzvjestaO.setVisible(true);
		if (!sesija.daLiPostoji())
			throw new Exception("Sesija nije kreirana!");
	}

	/**
	 * Initialize the contents of the frame.
	 * @param sesija 
	 */
	private void initialize(final Sessions sesija) {
		frmMenadzerIzvjestaO = new JFrame();
		frmMenadzerIzvjestaO.setTitle("Izvjestaj o ulazima i izlazima");
		frmMenadzerIzvjestaO.setBounds(100, 100, 450, 300);
		frmMenadzerIzvjestaO.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestaO.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 134, 414, 73);
		frmMenadzerIzvjestaO.getContentPane().add(scrollPane);
		
		
		/*
		 * 
		 * transkacijeKupacaTable = new JTable();
		transkacijeKupacaTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Naziv lijeka", "Vrijednost", "Kolicina"
			}
		));
		 * */
		table = new JTable(model);
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Datum", "Tip", "LOT", "Kolicina", "Trenutno stanje"
			}
		));*/
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Od");
		label.setBounds(32, 60, 27, 14);
		frmMenadzerIzvjestaO.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Do");
		label_1.setBounds(241, 60, 19, 14);
		frmMenadzerIzvjestaO.getContentPane().add(label_1);
		
		JLabel lblOdaberiLijek = new JLabel("Odaberi lijek");
		lblOdaberiLijek.setBounds(10, 88, 89, 14);
		frmMenadzerIzvjestaO.getContentPane().add(lblOdaberiLijek);
		
		JButton btnPretraga = new JButton("Pretraga");
		
		btnPretraga.setBounds(267, 100, 157, 23);
		frmMenadzerIzvjestaO.getContentPane().add(btnPretraga);
		
		final AutoCompleteJComboBox listaLijekova = new AutoCompleteJComboBox(sesija, Lijek.class, "naziv");
		listaLijekova.setBounds(10, 103, 191, 20);
		frmMenadzerIzvjestaO.getContentPane().add(listaLijekova);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 218, 414, 23);
		frmMenadzerIzvjestaO.getContentPane().add(btnGenerisiIzvjestaj);
		
		JLabel lblOdaberiSkladiste = new JLabel("Odaberi skladište");
		lblOdaberiSkladiste.setBounds(10, 11, 191, 14);
		frmMenadzerIzvjestaO.getContentPane().add(lblOdaberiSkladiste);
		
		MaskFormatter maska=new MaskFormatter();
		try {
			maska = new MaskFormatter("##.##.####");
			maska.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		datumOd = new JFormattedTextField(maska);
		datumOd.setBounds(59, 56, 119, 23);
		frmMenadzerIzvjestaO.getContentPane().add(datumOd);
		
		datumDo = new JFormattedTextField(maska);
		datumDo.setBounds(266, 57, 131, 23);
		frmMenadzerIzvjestaO.getContentPane().add(datumDo);
	
				
		final JComboBox listaSkladista = new JComboBox(SkladisteVM.dajSvaSkladista(sesija));
		listaSkladista.setBounds(10, 25, 414, 23);
		frmMenadzerIzvjestaO.getContentPane().add(listaSkladista);
		
		btnPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Lijek odabraniLijek = (Lijek) sesija.getSession().createCriteria(Lijek.class).
						add(Restrictions.eq("naziv",  listaLijekova.getSelectedItem().toString())).list().get(0);
				
				Skladiste odabranoSkladiste = sesija.getSession().get(Skladiste.class,
						Integer.parseInt(listaSkladista.getSelectedItem().toString()));
				
				String datum_od = datumOd.getText();
				String datum_do = datumDo.getText();
					
				IzvjestajUlaziIzlaziVM iz = new IzvjestajUlaziIzlaziVM(sesija);			
				List<Lot> sviLotovi= iz.vratiSveLotove(odabraniLijek, odabranoSkladiste);	
				List<Lot> ulazniLotovi = iz.vratiUlazneLotove(sviLotovi, datum_od, datum_do);		
				List<Lot> izlazniLotovi = iz.vratiIzlazneLotove(sviLotovi, datum_od, datum_do);			
				List<ObrisanLot> otpisaniLotovi = iz.vratiOtpisaneLotove(sviLotovi, datum_od, datum_do);
				
				List<Integer> kolicineUlaznih = iz.vratiKolicine(ulazniLotovi, true);
				List<Integer> kolicineIzlaznih = iz.vratiKolicine(izlazniLotovi, false);
				
				int i=0;
				while (i < ulazniLotovi.size()){
					int stanje = iz.vratiTrenutnoStanje(ulazniLotovi.get(i));
					Object[] row = {iz.vratiDatum(ulazniLotovi.get(i),true).toString(), "ulaz", ulazniLotovi.get(i).getBroj_lota(), kolicineUlaznih.get(i), stanje};
					model.addRow(row);
					i++;
				}
				
				i=0;
				while (i < izlazniLotovi.size()){
					int stanje = iz.vratiTrenutnoStanje(izlazniLotovi.get(i));
					
					int j=0;
					List<String> datumi = iz.vratiDatum(izlazniLotovi.get(i),false);
					while(j<datumi.size()){
						Object[] row = {datumi.get(j), "izlaz", izlazniLotovi.get(i).getBroj_lota(), kolicineIzlaznih.get(i), stanje };
						model.addRow(row);
						j++;
					}
					i++;
				}

				i=0;
				while (i < otpisaniLotovi.size()){
					int suma = iz.vratiKolicinuOtpisanog(otpisaniLotovi.get(i));
					Object[] row = { otpisaniLotovi.get(i).getDatum_otpisa(), "otpis", otpisaniLotovi.get(i).getBroj_lota(), 
							suma , 0 };
					model.addRow(row);
					i++;
				}
			}
		});
		
	}
	

	public void prikazi() {
		// TODO Auto-generated method stub
		frmMenadzerIzvjestaO.setVisible(true);
	}
}


