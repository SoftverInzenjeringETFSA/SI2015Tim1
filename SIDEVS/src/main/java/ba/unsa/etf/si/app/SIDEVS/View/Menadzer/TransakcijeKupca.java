package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TransakcijeKupca {

	private JFrame frmMenadzerTransakcijeKupca;
	private JTable transkacijeKupacaTable;
	private JTextField datumOd;
	private JTextField datumDo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransakcijeKupca window = new TransakcijeKupca();
					window.frmMenadzerTransakcijeKupca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransakcijeKupca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenadzerTransakcijeKupca = new JFrame();
		frmMenadzerTransakcijeKupca.setTitle("Menadzer- Transakcije kupca");
		frmMenadzerTransakcijeKupca.setBounds(100, 100, 450, 300);
		frmMenadzerTransakcijeKupca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenadzerTransakcijeKupca.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 414, 73);
		frmMenadzerTransakcijeKupca.getContentPane().add(scrollPane);
		
		transkacijeKupacaTable = new JTable();
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
		scrollPane.setViewportView(transkacijeKupacaTable);
		
		datumOd = new JTextField();
		datumOd.setBounds(44, 11, 157, 20);
		frmMenadzerTransakcijeKupca.getContentPane().add(datumOd);
		datumOd.setColumns(10);
		
		JLabel lblOd = new JLabel("Od");
		lblOd.setBounds(10, 14, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do");
		lblDo.setBounds(234, 14, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblDo);
		
		datumDo = new JTextField();
		datumDo.setBounds(267, 11, 157, 20);
		frmMenadzerTransakcijeKupca.getContentPane().add(datumDo);
		datumDo.setColumns(10);
		
		JLabel lblOdaberiKupca = new JLabel("Odaberi kupca");
		lblOdaberiKupca.setBounds(10, 42, 89, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblOdaberiKupca);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(267, 69, 157, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(btnPretraga);
		
		JComboBox listaKupaca = new JComboBox();
		listaKupaca.setBounds(10, 72, 191, 20);
		frmMenadzerTransakcijeKupca.getContentPane().add(listaKupaca);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 193, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblTotal);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 218, 414, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(btnGenerisiIzvjestaj);
	}
}
