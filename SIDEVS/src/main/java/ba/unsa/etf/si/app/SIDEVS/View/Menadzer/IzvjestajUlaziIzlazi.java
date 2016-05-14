package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.Color;
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

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import Exceptions.WrongInputException;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajUlaziIzlaziVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaKupcaVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaOdredjeniPeriodVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzvjestajUlaziIzlazi {
	final static Logger logger = Logger.getLogger(IzvjestajUlaziIzlazi.class);
	private Sessions sesija;
	private IzvjestajUlaziIzlaziVM iz;
	private JFrame frmMenadzerIzvjestaO;
	private JTable table;
	String [] cols=new String[] {"Datum", "Tip","Lot","Količina","Trenutno stanje"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
	private JFormattedTextField datumOd;
	private JFormattedTextField datumDo;
	private JLabel label_obavijest;
	private AutoCompleteJComboBox listaLijekova;

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
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws WrongInputException 
	 */
	public IzvjestajUlaziIzlazi() throws WrongInputException {
		initialize(sesija);
	}
	public IzvjestajUlaziIzlazi(Sessions sesija) throws Exception{
		this.sesija = sesija;
		iz = new IzvjestajUlaziIzlaziVM(sesija);
		initialize(sesija);
		frmMenadzerIzvjestaO.setVisible(true);
		if (!sesija.daLiPostoji())
			throw new Exception("Sesija nije kreirana!");
	}
	
	public void ugasi(){
		frmMenadzerIzvjestaO.dispose();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param sesija 
	 * @throws WrongInputException 
	 */
	private void initialize(final Sessions sesija) throws WrongInputException {
		frmMenadzerIzvjestaO = new JFrame();
		frmMenadzerIzvjestaO.setTitle("Izvjestaj o ulazima i izlazima");
		frmMenadzerIzvjestaO.setBounds(100, 100, 450, 308);
		frmMenadzerIzvjestaO.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestaO.getContentPane().setLayout(null);
		frmMenadzerIzvjestaO.setLocationRelativeTo(null);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 134, 414, 73);
		frmMenadzerIzvjestaO.getContentPane().add(scrollPane);

		table = new JTable(model);
		table.setEnabled(false);

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
		
		listaLijekova = new AutoCompleteJComboBox(sesija, Lijek.class, "naziv");
		listaLijekova.setBounds(10, 103, 191, 20);
		frmMenadzerIzvjestaO.getContentPane().add(listaLijekova);
		
		
		
		JLabel lblOdaberiSkladiste = new JLabel("Odaberi skladište");
		lblOdaberiSkladiste.setBounds(10, 11, 191, 14);
		frmMenadzerIzvjestaO.getContentPane().add(lblOdaberiSkladiste);
			
		datumOd = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumOd.setBounds(59, 56, 119, 23);
		frmMenadzerIzvjestaO.getContentPane().add(datumOd);
		
		datumDo = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumDo.setBounds(266, 57, 131, 23);
		frmMenadzerIzvjestaO.getContentPane().add(datumDo);
	
				
		final JComboBox listaSkladista = new JComboBox(SkladisteVM.dajSvaSkladista(sesija));
		listaSkladista.setBounds(10, 25, 414, 23);
		frmMenadzerIzvjestaO.getContentPane().add(listaSkladista);
		
		btnPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearTable();
				try {
					if(validirajPolja()){
						label_obavijest.setText("");
						Lijek odabraniLijek = (Lijek) sesija.getSession().createCriteria(Lijek.class).
								add(Restrictions.eq("naziv",  listaLijekova.getSelectedItem().toString())).list().get(0);
						
						Skladiste odabranoSkladiste = sesija.getSession().get(Skladiste.class,
								Integer.parseInt(listaSkladista.getSelectedItem().toString()));
						
						String datum_od = datumOd.getText();
						String datum_do = datumDo.getText();
								
						List<Lot> sviLotovi= iz.vratiSveLotove(odabraniLijek, odabranoSkladiste);	
						List<Lot> ulazniLotovi = iz.vratiUlazneLotove(sviLotovi, datum_od, datum_do);		
						List<Lot> izlazniLotovi = iz.vratiIzlazneLotove(sviLotovi, datum_od, datum_do);			
						List<Lot> otpisaniLotovi = iz.vratiOtpisaneLotove(sviLotovi, datum_od, datum_do);//obrisan
						
						List<Integer> kolicineUlaznih = iz.vratiKolicine(ulazniLotovi, true);
						List<Integer> kolicineIzlaznih = iz.vratiKolicine(izlazniLotovi, false);
						
						int i=0;
						while (i < ulazniLotovi.size()){
							int stanje = iz.vratiTrenutnoStanje(ulazniLotovi.get(i));
							Object[] row = {iz.vratiDatum(ulazniLotovi.get(i),true).toString(), "ulaz", 
									ulazniLotovi.get(i).getBroj_lota(), kolicineUlaznih.get(i), stanje};
							model.addRow(row);
							String[] all = {iz.vratiDatum(ulazniLotovi.get(i),true).toString(), "ulaz", 
									ulazniLotovi.get(i).getBroj_lota(), Integer.toString(kolicineUlaznih.get(i)), Integer.toString(stanje)};
							iz.dodaj(all);
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
								String[] all = {datumi.get(j), "izlaz",
										izlazniLotovi.get(i).getBroj_lota(), Integer.toString(kolicineIzlaznih.get(i)), Integer.toString(stanje)};
								iz.dodaj(all);
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
							String[] all = {Conversions.dateToString(otpisaniLotovi.get(i).getDatum_otpisa()), "otpis", 
									otpisaniLotovi.get(i).getBroj_lota(), Integer.toString(suma), "0"};
							iz.dodaj(all);
							i++;
						}
						if (model.getRowCount()==0) 
							label_obavijest.setText("Nema podataka za taj vremenski period.");
					}
				} catch (NumberFormatException e) {
					logger.error(e);
				} catch (HibernateException e) {
					logger.error(e);
				} catch (WrongInputException e) {
					logger.error(e);
					label_obavijest.setText(e.getMessage());
				} 
				
				
			}
		});
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				
				if (model.getRowCount()==0)  {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText("Nije moguce generisati PDF jer nema podataka.");
				}
				else{
				
				iz.createPDF(datumOd.getText(), datumDo.getText());
				frmMenadzerIzvjestaO.dispose();}
			}
		});
		btnGenerisiIzvjestaj.setBounds(10, 218, 414, 23);
		frmMenadzerIzvjestaO.getContentPane().add(btnGenerisiIzvjestaj);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 252, 357, 14);
		frmMenadzerIzvjestaO.getContentPane().add(label_obavijest);
		
	}
	
	private boolean validirajPolja() throws WrongInputException {
		String msg = "";
		label_obavijest.setForeground(Color.RED);		
		if ( !Validator.isDateValid( datumOd.getText()) ) msg="Prvi datum nije ispravan";
		else if ( !Validator.isDateValid( datumDo.getText()) ) msg="Drugi datum nije ispravan";
		else if (!Validator.veciStringDatum(datumOd.getText() , datumDo.getText()) ) msg="Drugi datum mora biti veci od prvog";
		else if (listaLijekova.getSelectedItem()==null) msg="Morate unijeti lijek";
		else msg = Validator.validirajLijek(sesija, listaLijekova.getSelectedItem().toString());
		
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

	public void prikazi() {
		// TODO Auto-generated method stub
		frmMenadzerIzvjestaO.setVisible(true);
	}
}


