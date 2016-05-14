package ba.unsa.etf.si.app.SIDEVS.View;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

import Exceptions.WrongInputException;

public final class Masks {
	final static Logger logger = Logger.getLogger(Masks.class);
	
	public static MaskFormatter vratiMaskuZaDatum() throws WrongInputException{
		MaskFormatter maska=new MaskFormatter();
		try {
			maska = new MaskFormatter("##.##.####");
			maska.setPlaceholderCharacter('_');
			
		} catch (ParseException e) {
			logger.error(e);
			throw new WrongInputException("Datum nije unešen");
		}
		return maska;
	}
	
	public static MaskFormatter vratiMaskuZaLot(){
		MaskFormatter maska=new MaskFormatter();
		try {
			maska = new MaskFormatter("###############");
			maska.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			logger.error(e);
		}
		return maska;
	}
	
	public static MaskFormatter vratiMaskuZaJMBG(){
		MaskFormatter maskaJMBG = new MaskFormatter();
		try {
			maskaJMBG = new MaskFormatter("#############");
			maskaJMBG.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			logger.error(e);
		}
		return maskaJMBG;
	}
	
	public static MaskFormatter vratiMaskuZaTelefon(){
		MaskFormatter maskaJMBG = new MaskFormatter();
		try {
			maskaJMBG = new MaskFormatter("###/###-###");
			maskaJMBG.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			logger.error(e);
		}
		return maskaJMBG;
	}
}
