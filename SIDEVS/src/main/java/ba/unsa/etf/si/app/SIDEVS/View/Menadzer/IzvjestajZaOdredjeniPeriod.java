package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

import Exceptions.WrongInputException;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import ba.unsa.etf.si.app.SIDEVS.View.Admin.BrisanjeKorisnika;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.GlavneMetode;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaOdredjeniPeriodVM;
import ba.unsa.etf.si.app.SIDEVS.Model.*;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzvjestajZaOdredjeniPeriod {
	final static Logger logger = Logger.getLogger(IzvjestajZaOdredjeniPeriod.class);
	private Sessions sesija;
	private JFrame frmMenadzerIzvjestajZa;
	private JTable IzvjestajZaodredjeniPeriodTable;
	String [] cols=new String[] {"Proizvod", "Lot","Skladište","Ulazi","Izlazi/Otpisi"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);
	private JLabel label_obavijest;
	private JFormattedTextField datumOd;
	private JFormattedTextField datumDo;
	private IzvjestajZaOdredjeniPeriodVM iz;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajZaOdredjeniPeriod window = new IzvjestajZaOdredjeniPeriod();
					window.frmMenadzerIzvjestajZa.setVisible(true);
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
	public IzvjestajZaOdredjeniPeriod() throws WrongInputException {
		initialize(sesija);
	}
	public IzvjestajZaOdredjeniPeriod(Sessions sesija) throws Exception{
		this.sesija = sesija;
		initialize(sesija);
		frmMenadzerIzvjestajZa.setVisible(true);
		if (!sesija.daLiPostoji())
			throw new Exception("Sesija nije kreirana!");
	}
	
	public void ugasi(){
		frmMenadzerIzvjestajZa.dispose();
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws WrongInputException 
	 */
	private void initialize(final Sessions sesija) throws WrongInputException {
		
		frmMenadzerIzvjestajZa = new JFrame();
		frmMenadzerIzvjestajZa.setTitle("Izvjestaj za odredjeni vremenski period");
		frmMenadzerIzvjestajZa.setBounds(100, 100, 571, 268);
		frmMenadzerIzvjestajZa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestajZa.getContentPane().setLayout(null);
		frmMenadzerIzvjestajZa.setLocationRelativeTo(null);
		frmMenadzerIzvjestajZa.setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 522, 100);
		frmMenadzerIzvjestajZa.getContentPane().add(scrollPane);
		
		IzvjestajZaodredjeniPeriodTable = new JTable(model);
		IzvjestajZaodredjeniPeriodTable.setEnabled(false);
		
		scrollPane.setViewportView(IzvjestajZaodredjeniPeriodTable);
		
		JLabel label = new JLabel("Od");
		label.setBounds(21, 44, 23, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Do");
		label_1.setBounds(179, 44, 23, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label_1);
				
		datumOd = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumOd.setBounds(54, 40, 95, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(datumOd);
		
		datumDo = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumDo.setBounds(212, 40, 95, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(datumDo);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				 clearTable();
				 
				try {
					if (validirajPolja()){
						//novi izvjestaj:
						
						
						label_obavijest.setText("");
						iz = new IzvjestajZaOdredjeniPeriodVM(sesija);
						
						List<Lot> ulazniLotovi = GlavneMetode.vratiSveLotoveOdDo(sesija, datumOd.getText(), datumDo.getText());
						
						for (Lot lot: ulazniLotovi){
							for (Skladiste s: iz.vratiSkladista(lot)){
								Integer izlaz = GlavneMetode.vratiKolicinuIzlazaOdDo(lot, s, datumOd.getText(), datumDo.getText());
								izlaz += GlavneMetode.vratiKolicinuOtpisanogOdDo(lot, s, datumOd.getText(), datumDo.getText());
								Object[] row = {lot.getLijek().getNaziv(), 
												lot.getBroj_lota(), 
												s.getBroj_skladista(), 
												GlavneMetode.vratiKolicinuUlaza(lot, s).toString(),
												izlaz.toString()
								};
								model.addRow(row);
								String[] celije = {lot.getLijek().getNaziv(), 
										lot.getBroj_lota(), 
										Integer.toString(s.getBroj_skladista()), 
										GlavneMetode.vratiKolicinuUlaza(lot, s).toString(),
										izlaz.toString()
										};
								iz.dodaj(celije);
							}
						}
						if (model.getRowCount()==0) 
							label_obavijest.setText("Nema podataka za taj vremenski period.");
						
					}
				} catch (WrongInputException e) {
					logger.error(e);
					label_obavijest.setText(e.getMessage());
				} 
				
			}
		});
		btnPretraga.setBounds(341, 40, 190, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnPretraga);
		
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
				while (model.getRowCount()>0){
					model.removeRow(0);
				}
				datumDo.setValue(null);
				datumOd.setValue(null);
				frmMenadzerIzvjestajZa.dispose();
				}
			}
		});
		btnGenerisiIzvjestaj.setBounds(144, 185, 282, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnGenerisiIzvjestaj);	
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 215, 384, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label_obavijest);
	}

	private boolean validirajPolja() throws WrongInputException {
		String msg = "";
		label_obavijest.setForeground(Color.RED);
		
		if ( !Validator.isDateValid( datumOd.getText()) ) msg="Prvi datum nije ispravan";
		else if ( !Validator.isDateValid( datumDo.getText()) ) msg="Drugi datum nije ispravan";
		else if (!Validator.veciStringDatum(datumOd.getText() , datumDo.getText()) ) msg="Drugi datum mora biti veci od prvog";
		
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
		frmMenadzerIzvjestajZa.setVisible(true);
	}

}
