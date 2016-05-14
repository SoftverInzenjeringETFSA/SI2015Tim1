package ba.unsa.etf.si.app.SIDEVS.Validation;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class EmailFormatter extends AbstractFormatter {
	
	final private Pattern regexp =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);;
	
    @Override public Object stringToValue(String string) throws ParseException {
        Matcher matcher = regexp.matcher(string);
        if (matcher.matches())
            return string;
        throw new ParseException("Neispravan email", 0);
    }

    @Override public String valueToString(Object value) {
        return (String) value;
    }

    
}