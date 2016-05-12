package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

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

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaOdredjeniPeriodVM;
import ba.unsa.etf.si.app.SIDEVS.Model.*;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzvjestajZaOdredjeniPeriod {
	private Sessions sesija;
	private JFrame frmMenadzerIzvjestajZa;
	private JTable IzvjestajZaodredjeniPeriodTable;
	String [] cols=new String[] {"Proizvod", "Lot","Skladište","Ulazi","Izlazi/Otpisi"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajZaOdredjeniPeriod() {
		initialize(sesija);
	}
	public IzvjestajZaOdredjeniPeriod(Sessions sesija) throws Exception{
		this.sesija = sesija;
		initialize(sesija);
		frmMenadzerIzvjestajZa.setVisible(true);
		if (!sesija.daLiPostoji())
			throw new Exception("Sesija nije kreirana!");
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Sessions sesija) {
		frmMenadzerIzvjestajZa = new JFrame();
		frmMenadzerIzvjestajZa.setTitle("Izvjestaj za odredjeni vremenski period");
		frmMenadzerIzvjestajZa.setBounds(100, 100, 571, 268);
		frmMenadzerIzvjestajZa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerIzvjestajZa.getContentPane().setLayout(null);
		frmMenadzerIzvjestajZa.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 522, 110);
		frmMenadzerIzvjestajZa.getContentPane().add(scrollPane);
		
		IzvjestajZaodredjeniPeriodTable = new JTable(model);
		
		scrollPane.setViewportView(IzvjestajZaodredjeniPeriodTable);
		
		JLabel label = new JLabel("Od");
		label.setBounds(21, 44, 23, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Do");
		label_1.setBounds(179, 44, 23, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label_1);
				
		final JFormattedTextField datumOd = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumOd.setBounds(54, 40, 95, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(datumOd);
		
		final JFormattedTextField datumDo = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumDo.setBounds(212, 40, 95, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(datumDo);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				IzvjestajZaOdredjeniPeriodVM iz = new IzvjestajZaOdredjeniPeriodVM(sesija);
				List<Lot> ulazniLotovi = iz.vratiUlazneLotove(datumOd.getText(), datumDo.getText());
				
				for (Lot lot: ulazniLotovi){
					for (Skladiste s: iz.vratiSkladista(lot)){
						Object[] row = { lot.getLijek().getNaziv(), 
								lot.getBroj_lota(), 
								s.getBroj_skladista(), 
								iz.vratiUkupniUlaz(lot, s),
								iz.vratiUkupniIzlaz(lot, s, datumOd.getText(), datumDo.getText())
						};
						model.addRow(row);
					}
				}
			}
		});
		btnPretraga.setBounds(341, 40, 190, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnPretraga);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IzvjestajZaOdredjeniPeriodVM iz = new IzvjestajZaOdredjeniPeriodVM(sesija);
				List<Lot> lotovi = iz.vratiUlazneLotove(datumOd.getText(), datumDo.getText());
				iz.createPDF(lotovi, datumOd.getText(), datumDo.getText());
				frmMenadzerIzvjestajZa.dispose();
			}
		});
		btnGenerisiIzvjestaj.setBounds(144, 195, 282, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnGenerisiIzvjestaj);	
	}

	public void prikazi() {
		frmMenadzerIzvjestajZa.setVisible(true);
	}

}
