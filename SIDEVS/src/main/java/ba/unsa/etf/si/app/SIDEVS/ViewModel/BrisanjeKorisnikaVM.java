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

	public static boolean BrisiKorisnika(Sessions ses, String ime, String prezime) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			Korisnik a = ses.getKorisnik();
			if(a.getIme() == ime && a.getPrezime() == prezime) return false;
			
			Transaction t = ses.getSession().beginTransaction();
			Criteria criteria = ses.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", ime).ignoreCase()).add(Restrictions.like("prezime", prezime).ignoreCase());
			
			List<Korisnik> lista = criteria.list();
			Korisnik k = lista.get(0);
			
			ses.getSession().delete(k);
			t.commit();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;		
	}
}

