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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
									Criteria criteria = session.getSession().createCriteria(klasa).add(Restrictions.like(naziv_kolone, tc.getText() + "%").ignoreCase()).setProjection(Projections.property(naziv_kolone));
									List<String> founds = criteria.list();
									Set<String> foundSet = new HashSet<String>();
									for (String s : founds) {
										foundSet.add(s.toString().toLowerCase());
									}
									//Collections.sort(founds);//sortiranje
									setEditable(false);
									removeAllItems();
									// izbjegavanje dodavanja kopija 
									if (!foundSet.contains(tc.getText().toLowerCase())) {
										addItem(tc.getText());
									}
									for (String s : founds) {
										addItem(s);
									}
									setEditable(true);
									setPopupVisible(true);
									
									tc.requestFocus();
								}
							} catch (Exception e) {
								System.out.println("Greska u AutoCompleteJComboBox");
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
