package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IzvjestajZaOdredjeniPeriod {

	private JFrame frmMenadzerIzvjestajZa;
	private JTable IzvjestajZaodredjeniPeriodTable;
	private JTextField datumOd;
	private JTextField datumDo;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenadzerIzvjestajZa = new JFrame();
		frmMenadzerIzvjestajZa.setTitle("Menadzer- Izvjestaj za odredjeni vremenski period");
		frmMenadzerIzvjestajZa.setBounds(100, 100, 450, 255);
		frmMenadzerIzvjestajZa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenadzerIzvjestajZa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 414, 73);
		frmMenadzerIzvjestajZa.getContentPane().add(scrollPane);
		
		IzvjestajZaodredjeniPeriodTable = new JTable();
		IzvjestajZaodredjeniPeriodTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Proizvod", "LOT", "Skladiste", "Ulaz", "Izlaz"
			}
		));
		scrollPane.setViewportView(IzvjestajZaodredjeniPeriodTable);
		
		JLabel label = new JLabel("Od");
		label.setBounds(10, 14, 46, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Do");
		label_1.setBounds(234, 14, 46, 14);
		frmMenadzerIzvjestajZa.getContentPane().add(label_1);
		
		datumOd = new JTextField();
		datumOd.setBounds(35, 11, 157, 20);
		frmMenadzerIzvjestajZa.getContentPane().add(datumOd);
		datumOd.setColumns(10);
		
		datumDo = new JTextField();
		datumDo.setBounds(264, 11, 160, 20);
		frmMenadzerIzvjestajZa.getContentPane().add(datumDo);
		datumDo.setColumns(10);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(10, 54, 414, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnPretraga);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 172, 414, 23);
		frmMenadzerIzvjestajZa.getContentPane().add(btnGenerisiIzvjestaj);
	}

}
