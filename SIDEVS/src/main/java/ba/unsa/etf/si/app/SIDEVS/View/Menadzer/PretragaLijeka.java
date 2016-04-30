package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PretragaLijeka {

	private JFrame frmPretragaLijeka;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PretragaLijeka window = new PretragaLijeka();
					window.frmPretragaLijeka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PretragaLijeka() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPretragaLijeka = new JFrame();
		frmPretragaLijeka.setTitle("Pretraga lijeka");
		frmPretragaLijeka.setBounds(100, 100, 450, 300);
		frmPretragaLijeka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPretragaLijeka.getContentPane().setLayout(null);
		
		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setBounds(10, 11, 123, 14);
		frmPretragaLijeka.getContentPane().add(lblNazivProizvoda);
		
		textField = new JTextField();
		textField.setBounds(143, 8, 166, 20);
		frmPretragaLijeka.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPretrai = new JButton("Pretraži");
		btnPretrai.setBounds(143, 39, 166, 23);
		frmPretragaLijeka.getContentPane().add(btnPretrai);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 299, 171);
		frmPretragaLijeka.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Skladi\u0161te", "Koli\u010Dina"
			}
		));
		scrollPane.setViewportView(table);
	}
}
