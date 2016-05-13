package ba.unsa.etf.si.app.SIDEVS.Validation;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.criterion.Restrictions;

import Exceptions.WrongInputException;
import ba.unsa.etf.si.app.SIDEVS.Model.*;

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
	
	
	
	public static Boolean validateStringDate(String date){
		try {
			int d = Integer.parseInt(date.substring(0,2));			
			int m = Integer.parseInt(date.substring(3,5));
			int g = Integer.parseInt(date.substring(6,10));
			if(d < 1 || d > 31) return false;
			if (m < 1 || m > 12) return false;
			if(g < 1900) return false;
		} catch (Exception e) {
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
	


	public static boolean isDateValid(String date) throws WrongInputException 
	{
	        try {
	            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (Exception e) {
	            throw new WrongInputException("Datum nije unešen ispravno");
	        }
	}
	
	public static Boolean daLiJeBroj(char c){
		if (!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) 
			return true;
		return false;
	}
	
	public static String validirajLot(Sessions sesija, String brojLota){
		String msg="";
		
		if (brojLota.length()==0) msg = "Morate unijeti broj lota";
		else if(brojLota.length() < 6 && brojLota.length()>15) msg = "Broj lota mora biti između 6 i 15";
		else {
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).
				add(Restrictions.eq("broj_lota", brojLota)).add(Restrictions.isNull("datum_otpisa")).list();

		if (lotovi.size()==0) msg = "Uneseni broj lota ne postoji u sistemu";
		}
		return msg;
	}
	public static String validirajLijek(Sessions sesija, String lijek) {
		String msg="";
		if(lijek.length() == 0) msg = "Morate unijeti lijek";
		else {
		List<Lot> lijekovi = sesija.getSession().createCriteria(Lijek.class).
				add(Restrictions.eq("naziv", lijek)).list();

		if (lijekovi.size()==0) msg = "Uneseni naziv lijeka ne postoji u sistemu";
		}
		return msg;
	}
	public static boolean veciStringDatum(String datum1, String datum2) {
		Date d1 = Conversions.stringToDate(datum1);
		Date d2 = Conversions.stringToDate(datum2);
		if (d2.after(d1)) return true;
		return false;
	}
	public static String validirajKupca(Sessions s, String kupac) {
		String msg="";
		if(kupac.length() == 0) msg = "Morate unijeti lijek";
		else {
		List<Lot> lijekovi = s.getSession().createCriteria(Kupac.class).
				add(Restrictions.eq("naziv", kupac)).list();

		if (lijekovi.size()==0) msg = "Uneseni naziv kupca ne postoji u sistemu";
		}
		return msg;
	}
	public static boolean validirajCijenu(String cijena) throws WrongInputException {
		
		try{
			Double.parseDouble(cijena);
		}
		catch ( NumberFormatException e){
			throw new WrongInputException("Cijena nije u ispravnom formatu");
		}
		
		return true;
	}

}
