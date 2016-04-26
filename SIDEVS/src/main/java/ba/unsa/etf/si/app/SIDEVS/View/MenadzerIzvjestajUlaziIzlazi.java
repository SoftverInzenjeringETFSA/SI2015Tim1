package ba.unsa.etf.si.app.SIDEVS.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenadzerIzvjestajUlaziIzlazi {

	private JFrame frmMenadzerIzvjestaO;
	private JTextField datumOd;
	private JTextField datumDo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenadzerIzvjestajUlaziIzlazi window = new MenadzerIzvjestajUlaziIzlazi();
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
	public MenadzerIzvjestajUlaziIzlazi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenadzerIzvjestaO = new JFrame();
		frmMenadzerIzvjestaO.setTitle("Menadzer- Izvjesta o ulazima i izlazima");
		frmMenadzerIzvjestaO.setBounds(100, 100, 450, 300);
		frmMenadzerIzvjestaO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenadzerIzvjestaO.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 134, 414, 73);
		frmMenadzerIzvjestaO.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Datum", "Tip", "LOT", "Kolicina", "Trenutno stanje"
			}
		));
		scrollPane.setViewportView(table);
		
		datumOd = new JTextField();
		datumOd.setColumns(10);
		datumOd.setBounds(44, 57, 157, 20);
		frmMenadzerIzvjestaO.getContentPane().add(datumOd);
		
		JLabel label = new JLabel("Od");
		label.setBounds(10, 60, 46, 14);
		frmMenadzerIzvjestaO.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Do");
		label_1.setBounds(234, 60, 46, 14);
		frmMenadzerIzvjestaO.getContentPane().add(label_1);
		
		datumDo = new JTextField();
		datumDo.setColumns(10);
		datumDo.setBounds(267, 57, 157, 20);
		frmMenadzerIzvjestaO.getContentPane().add(datumDo);
		
		JLabel lblOdaberiLijek = new JLabel("Odaberi lijek");
		lblOdaberiLijek.setBounds(10, 88, 89, 14);
		frmMenadzerIzvjestaO.getContentPane().add(lblOdaberiLijek);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.setBounds(267, 100, 157, 23);
		frmMenadzerIzvjestaO.getContentPane().add(btnPretraga);
		
		JComboBox listaLijekova = new JComboBox();
		listaLijekova.setBounds(10, 103, 191, 20);
		frmMenadzerIzvjestaO.getContentPane().add(listaLijekova);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 218, 414, 23);
		frmMenadzerIzvjestaO.getContentPane().add(btnGenerisiIzvjestaj);
		
		JComboBox lisaSkladista = new JComboBox();
		lisaSkladista.setBounds(10, 26, 414, 20);
		frmMenadzerIzvjestaO.getContentPane().add(lisaSkladista);
		
		JLabel lblOdaberiSkladiste = new JLabel("Odaberi skladiste");
		lblOdaberiSkladiste.setBounds(10, 0, 191, 14);
		frmMenadzerIzvjestaO.getContentPane().add(lblOdaberiSkladiste);
	}

}
