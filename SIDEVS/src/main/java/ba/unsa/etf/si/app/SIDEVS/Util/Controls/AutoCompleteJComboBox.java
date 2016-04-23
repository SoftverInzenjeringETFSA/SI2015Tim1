package ba.unsa.etf.si.app.SIDEVS.Util.Controls;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.hibernate.Session;

public class AutoCompleteJComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	/*
	 * Konstruktor kontrole
	 * Prima sesiju i klasu nad kojom će se vršiti pretraga
	 */
	public AutoCompleteJComboBox(Session session, Class search_class){
		super();
		setEditable(true);
		
		Component c = getEditor().getEditorComponent();
		if (c instanceof JTextComponent){
			final JTextComponent tc = (JTextComponent)c;
			tc.getDocument().addDocumentListener(new DocumentListener(){
				public void changedUpdate(DocumentEvent arg0) {
					
				}
				public void insertUpdate(DocumentEvent arg0) {
					update();
				}
				public void removeUpdate(DocumentEvent arg0) {
					update();
				}
				public void update(){
					SwingUtilities.invokeLater(new Runnable(){
						public void run() {
							List<String> founds = new ArrayList<String>(); //= new ArrayList<String>(searchable.search(tc.getText()));
							Set<String> foundSet = new HashSet<String>();
							for ( String s : founds ){
								foundSet.add(s.toLowerCase());
							}
							Collections.sort(founds);//sortiranje
							setEditable(false);
							removeAllItems();
							//if founds contains the search text, then only add once.
							if ( !foundSet.contains( tc.getText().toLowerCase()) ){
								addItem( tc.getText() );
							}					
							for (String s : founds) {
								addItem(s);
							}
							setEditable(true);
							setPopupVisible(true);
						}
					});
				}
			});
			tc.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent arg0) {
					if ( tc.getText().length() > 0 ){
						setPopupVisible(true);
					}
				}
				public void focusLost(FocusEvent arg0) {						

				}
			});
		}
		else{
			throw new IllegalStateException("Mora biti JTextComponent!");
		}
	}
}
