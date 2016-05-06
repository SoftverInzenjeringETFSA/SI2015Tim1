package ba.unsa.etf.si.app.SIDEVS.Util.Controls;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.hibernate.Criteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public class AutoCompleteJComboBox extends JComboBox<Object> {

	private static final long serialVersionUID = 1L;

	/*
	 * Konstruktor kontrole Prima sesiju i klasu nad kojom će se vršiti pretraga
	 */
	public AutoCompleteJComboBox(final Sessions session, final Class<?> klasa, final String naziv_kolone) {
		super();
		setEditable(true);

		Component c = getEditor().getEditorComponent();
		if (c instanceof JTextComponent) {
			final JTextComponent tc = (JTextComponent) c;
			tc.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent arg0) {

				}

				public void insertUpdate(DocumentEvent arg0) {
					update();
				}

				public void removeUpdate(DocumentEvent arg0) {
					update();
				}

				public void update() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							try {
								if (!tc.getText().isEmpty()) {
									
									List<String> foundx = null;					
									Criteria criteria = session.getSession().createCriteria(klasa).add(Restrictions.like(naziv_kolone, tc.getText() + "%").ignoreCase()).setProjection(Projections.property(naziv_kolone));						
									List<String> founds = criteria.list();
									Set<String> foundSet = new HashSet<String>();
									if(naziv_kolone=="ime"){
										Criteria criteriax = session.getSession().createCriteria(klasa).add(Restrictions.like(naziv_kolone, tc.getText() + "%").ignoreCase()).setProjection(Projections.property("prezime"));
										foundx = criteriax.list();
									}
									
									for(int i=0; i<founds.size(); i++){
										String s = founds.get(i);
										if(naziv_kolone=="ime"){
											s = s + " " + foundx.get(i);								
										}
										foundSet.add(s.toString().toLowerCase());
									}
									
									Collections.sort(founds);//sortiranje
									setEditable(false);
									removeAllItems();
									// izbjegavanje dodavanja kopija 
									if (!foundSet.contains(tc.getText().toLowerCase())) {
										addItem(tc.getText());
									}
								
									for(int i=0; i<founds.size(); i++){
										String s = founds.get(i);
										if(naziv_kolone=="ime"){
											s = s + " " + foundx.get(i);
										}
										addItem(s);
									}
									
									/*for (String s : founds) {
										addItem(s);
									}*/
									setEditable(true);
									setPopupVisible(true);
									
									tc.requestFocus();
								}
							} catch (Exception e) {
								System.out.println("Greška pri pristupu bazi");
								System.out.println(e.toString());
							}
						}
					});
				}
			});
			tc.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {
					if (tc.getText().length() > 0) {
						setPopupVisible(true);
					}
				}

				public void focusLost(FocusEvent arg0) {

				}
			});
		} else {
			throw new IllegalStateException("Mora biti JTextComponent!");
		}
	}
}
