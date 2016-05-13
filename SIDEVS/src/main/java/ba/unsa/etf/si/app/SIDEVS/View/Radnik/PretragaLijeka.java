package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.PretragaLijekovaVM;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.SkladisteVM;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class PretragaLijeka {

	public JFrame frmPretragaLijeka;
	private JTable table;
	private Sessions _sesija;
	private JLabel label_obavijest;

	/**
	 * Create the application.
	 */
	public PretragaLijeka(Sessions s) throws Exception {
		_sesija = s;
		initialize(_sesija);
		frmPretragaLijeka.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		frmPretragaLijeka = new JFrame();
		frmPretragaLijeka.setTitle("Pretraga lijeka");
		frmPretragaLijeka.setBounds(100, 100, 403, 322);
		frmPretragaLijeka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPretragaLijeka.getContentPane().setLayout(null);
		frmPretragaLijeka.setLocationRelativeTo(null);

		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setBounds(21, 11, 123, 14);
		frmPretragaLijeka.getContentPane().add(lblNazivProizvoda);

		final AutoCompleteJComboBox listaProizvoda = new AutoCompleteJComboBox(s, Lijek.class, "naziv");
		listaProizvoda.setBounds(20, 24, 162, 20);
		frmPretragaLijeka.getContentPane().add(listaProizvoda);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 57, 347, 203);
		frmPretragaLijeka.getContentPane().add(scrollPane);

		label_obavijest = new JLabel("");
		label_obavijest.setBounds(21, 261, 346, 14);
		frmPretragaLijeka.getContentPane().add(label_obavijest);

		JButton btnPretrazi = new JButton("Pretraži");
		btnPretrazi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					PretragaLijekovaVM pl = new PretragaLijekovaVM(_sesija);
					Map<Integer, Integer> mapa = pl
							.dajKolicinuLijekaUSkladistu(listaProizvoda.getSelectedItem().toString());
					
					table = new JTable(toTableModel(mapa));
					scrollPane.setViewportView(table);

				} catch (Exception ex) {
					label_obavijest.setForeground(Color.RED);
					label_obavijest.setText(ex.getMessage());
				}
			}
		});
		btnPretrazi.setBounds(199, 23, 168, 23);
		frmPretragaLijeka.getContentPane().add(btnPretrazi);

	}
	
	private TableModel toTableModel(Map<Integer, Integer> mapa) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Skladište", "Količina" }, 0
	    );
	    for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	    }
	    return model;
	}
}
