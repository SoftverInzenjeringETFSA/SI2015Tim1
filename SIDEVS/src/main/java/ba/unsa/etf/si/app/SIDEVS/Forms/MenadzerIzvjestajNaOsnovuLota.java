package ba.unsa.etf.si.app.SIDEVS.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenadzerIzvjestajNaOsnovuLota {

	private JFrame frmMenadzerIzvjestajNa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenadzerIzvjestajNaOsnovuLota window = new MenadzerIzvjestajNaOsnovuLota();
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
	public MenadzerIzvjestajNaOsnovuLota() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenadzerIzvjestajNa = new JFrame();
		frmMenadzerIzvjestajNa.setTitle("Menadzer- Izvjestaj na osnovu lota");
		frmMenadzerIzvjestajNa.setBounds(100, 100, 450, 251);
		frmMenadzerIzvjestajNa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenadzerIzvjestajNa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 414, 73);
		frmMenadzerIzvjestajNa.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Datum ulaza", "Datum izlaza", "Kolicina ulaza", "Kolicina izlaza"
			}
		));
		scrollPane.setViewportView(table);
		
		JComboBox listaLotova = new JComboBox();
		listaLotova.setBounds(10, 26, 217, 20);
		frmMenadzerIzvjestajNa.getContentPane().add(listaLotova);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(249, 25, 175, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnPretraga);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 162, 414, 23);
		frmMenadzerIzvjestajNa.getContentPane().add(btnGenerisiIzvjestaj);
		
		JLabel lblOdaberiLot = new JLabel("Odaberi lot");
		lblOdaberiLot.setBounds(10, 11, 132, 14);
		frmMenadzerIzvjestajNa.getContentPane().add(lblOdaberiLot);
	}

}
