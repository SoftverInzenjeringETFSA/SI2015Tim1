package ba.unsa.etf.si.app.SIDEVS.View;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public final class Masks {
	
	public static MaskFormatter vratiMaskuZaDatum(){
		MaskFormatter maska=new MaskFormatter();
		try {
			maska = new MaskFormatter("##.##.####");
			maska.setPlaceholderCharacter('_');
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return maska;
	}
	
	public static MaskFormatter vratiMaskuZaLot(){
		MaskFormatter maska=new MaskFormatter();
		try {
			maska = new MaskFormatter("AAAAAA");
			maska.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maska;
	}
	
	public static MaskFormatter vratiMaskuZaJMBG(){
		MaskFormatter maskaJMBG = new MaskFormatter();
		try {
			maskaJMBG = new MaskFormatter("#############");
			maskaJMBG.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maskaJMBG;
	}
	
}
