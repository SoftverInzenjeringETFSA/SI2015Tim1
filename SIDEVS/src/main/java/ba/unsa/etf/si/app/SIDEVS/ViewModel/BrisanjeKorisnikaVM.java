package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.View.Login;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.BrisanjeKupca;

public class BrisanjeKorisnikaVM {
	
	final static Logger logger = Logger.getLogger(BrisanjeKorisnikaVM.class);
	private Sessions s;
	
	public BrisanjeKorisnikaVM(Sessions s){
		this.s = s;
	}

	public boolean BrisiKorisnika(String email) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			if(s.getKorisnik().getEmail() == email) return false;
			
			Korisnik k = (Korisnik) s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("email", email).ignoreCase()).uniqueResult();
			
			s.getSession().beginTransaction();
			s.getSession().delete(k);
			s.getTrasaction().commit();
			
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;		
	}
}

