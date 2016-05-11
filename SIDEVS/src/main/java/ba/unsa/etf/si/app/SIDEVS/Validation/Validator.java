package ba.unsa.etf.si.app.SIDEVS.Validation;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
	private static Pattern VALID_TEXT = Pattern.compile("^[\\p{L}0-9]*$");
	
	public static Boolean validirajString(String tekst){
		if(tekst.isEmpty()) return false;
		Matcher m = VALID_TEXT.matcher(tekst);
		return m.find();
	}
	public static Boolean validirajBrojPozitivan(String broj){
		try{
			Double d = Double.parseDouble(broj);
			if(d <= 0) return false;
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	public static Boolean validirajDatum(String dan, String mjesec, String godina){
		try {
			int d = Integer.parseInt(dan);
			int m = Integer.parseInt(mjesec);
			int g = Integer.parseInt(godina);
			if(d < 1 || d > 31) return false;
			if (m < 1 || m > 12) return false;
			if(g < 1900) return false;
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean daLiJeBroj(char c){
		if (!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) 
			return true;
		return false;
	}

}
