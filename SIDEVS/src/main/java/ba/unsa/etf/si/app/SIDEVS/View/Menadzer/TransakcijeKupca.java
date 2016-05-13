package ba.unsa.etf.si.app.SIDEVS.View.Menadzer;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajZaKupcaVM;
import javax.swing.JFormattedTextField;

public class TransakcijeKupca {

	private Sessions s;
	
	private JFrame frmMenadzerTransakcijeKupca;
	private JTable transkacijeKupacaTable;
	private JTable table;
	private JLabel label_obavijest;
	private AutoCompleteJComboBox  listaKupaca;
	
	String [] cols=new String[] {"Naziv lijeka", "Količina","Vrijednost"};
	DefaultTableModel model = new DefaultTableModel(cols, 0);

	
	JFormattedTextField datumOd;
	JFormattedTextField datumDo;
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
		initialize(s);
	}
	
	public TransakcijeKupca(Sessions s) throws Exception{
		this.s = s;
		initialize(s);
		frmMenadzerTransakcijeKupca.setVisible(true);
		if (!s.daLiPostoji())
			throw new Exception("Sesija nije kreirana!");
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @param s 
	 */
	private void initialize(Sessions sesija) {
		
		Transaction t = s.getSession().beginTransaction();
		
		frmMenadzerTransakcijeKupca = new JFrame();
		frmMenadzerTransakcijeKupca.setTitle("Menadzer- Transakcije kupca");
		frmMenadzerTransakcijeKupca.setBounds(100, 100, 450, 300);
		frmMenadzerTransakcijeKupca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMenadzerTransakcijeKupca.getContentPane().setLayout(null);
		frmMenadzerTransakcijeKupca.setLocationRelativeTo(null);
		
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
		
		JLabel lblOd = new JLabel("Od");
		lblOd.setBounds(10, 14, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do");
		lblDo.setBounds(234, 14, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblDo);
		
		JLabel lblOdaberiKupca = new JLabel("Odaberi kupca");
		lblOdaberiKupca.setBounds(10, 47, 89, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblOdaberiKupca);
		listaKupaca = new AutoCompleteJComboBox(s, Kupac.class, "naziv");
		listaKupaca.setBounds(10, 72, 191, 20);
		frmMenadzerTransakcijeKupca.getContentPane().add(listaKupaca);
		

		datumOd = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		
		datumOd.setBounds(66, 11, 131, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(datumOd);
		
		datumDo = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		datumDo.setBounds(263, 11, 131, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(datumDo);

		table = new JTable(model);
		table.setEnabled(false);
		
		scrollPane.setViewportView(table);
		
		JButton btnPretraga = new JButton("Pretraga");
		btnPretraga.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				clearTable();
				
				if (validirajPolja()){
					String kupac = listaKupaca.getSelectedItem().toString();
					
					String datum_od = datumOd.getText();
					System.out.println(datum_od);
					String datum_do = datumDo.getText();
					System.out.println(datum_do);
					IzvjestajZaKupcaVM iz = new IzvjestajZaKupcaVM(s); 
					
					List<Kupac> k = s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", kupac)).list();
						
					
					List<FakturaLot> fakture = iz.vratiFaktureKupca(k.get(0), datum_od, datum_do);
					
					List<Lijek> lijekovi = iz.vratiLijekoveKupca(fakture);
					
					List<Integer> kolicine = iz.vratiKolicineLijekova(fakture, lijekovi);
					
					List<Integer> cijene = iz.vratiCijene(fakture, kolicine);
					
					int i=0;
					while (i < lijekovi.size()){
						Object[] row = { lijekovi.get(i).getNaziv(), kolicine.get(i), cijene.get(i) };
						model.addRow(row);
						i++;
					}
					
				}
				
				
			}
		});
		btnPretraga.setBounds(267, 69, 157, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(btnPretraga);
		
		
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 193, 46, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(lblTotal);
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi izvjestaj");
		btnGenerisiIzvjestaj.setBounds(10, 218, 414, 23);
		frmMenadzerTransakcijeKupca.getContentPane().add(btnGenerisiIzvjestaj);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 247, 341, 14);
		frmMenadzerTransakcijeKupca.getContentPane().add(label_obavijest);
	}
	
	private boolean validirajPolja() {
		String msg = "";
		label_obavijest.setForeground(Color.RED);
		
		if ( !Validator.isDateValid( datumOd.getText()) ) msg="Prvi datum nije ispravan";
		else if ( !Validator.isDateValid( datumDo.getText()) ) msg="Drugi datum nije ispravan";
		else if (!Validator.veciStringDatum(datumOd.getText() , datumDo.getText()) ) msg="Drugi datum mora biti veci od prvog";
		else if (listaKupaca.getSelectedItem()==null) msg="Morate unijeti kupca";
		else msg = Validator.validirajKupca(s, listaKupaca.getSelectedItem().toString());
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
		frmMenadzerTransakcijeKupca.setVisible(true);	
	}
}
