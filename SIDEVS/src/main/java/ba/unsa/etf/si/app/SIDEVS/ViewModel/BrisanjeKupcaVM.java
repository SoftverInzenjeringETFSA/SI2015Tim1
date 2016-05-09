package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public class BrisanjeKupcaVM {
	
	public static boolean BrisiKupca(Sessions ses, String naziv) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			Transaction t = ses.getSession().beginTransaction();
			Criteria criteria = ses.getSession().createCriteria(Kupac.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			
			List<Kupac> lista = criteria.list();
			Kupac k = lista.get(0);
			
			ses.getSession().delete(k);
			t.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	}


