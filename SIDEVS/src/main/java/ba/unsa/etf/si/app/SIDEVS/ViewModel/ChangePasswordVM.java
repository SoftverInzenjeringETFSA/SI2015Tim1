package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.View.Login;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.BrisanjeKupca;

public class ChangePasswordVM {
	final static Logger logger = Logger.getLogger(ChangePasswordVM.class);

	public static boolean ChangePassword(String username, char[] password, char[] newPassword, char[] repeatPassword) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			String p = String.valueOf(password);
			Sessions s = null;
			try{
				s = Sessions.getInstance(username, p);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			Transaction t = s.getSession().beginTransaction();
			Korisnik k = s.getKorisnik();
			if(String.valueOf(newPassword).equals(String.valueOf(repeatPassword)))k.setLozinka(String.valueOf(newPassword));
			else return false;
			s.getSession().save(k);
			t.commit();
			s.ubijSesiju();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;		
	}
	

	

}
