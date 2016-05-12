package ba.unsa.etf.si.app.SIDEVS.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Conversions {

	public static Date stringToDate(String input){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		Date date = new Date();
		try {
			date = sdf.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String dateToString(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
		String s = sdf.format(date);
		
		return s;
	}
	
	
	public static Date dajDatumZaBazu(String input){
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("dd.mm.yyyy");
		
		java.util.Date date = new Date();
		try {
			date = sdf1.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.text.SimpleDateFormat sdf2 = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String s = sdf2.format(date);
		
		Date d = new Date();
		try {
			d = sdf2.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
	}
	
}
