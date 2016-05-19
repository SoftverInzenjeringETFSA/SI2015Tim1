package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.BrisanjeKupca;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;

public final class EvidencijaKupca {
	final static Logger logger = Logger.getLogger(EvidencijaKupca.class);

	private static Sessions s;
	private static Kupac noviKupac;

	public Sessions getSessions() {
		return this.s;
	}

	public EvidencijaKupca(Sessions s) throws Exception {
		this.s = s;
	}

	public static Boolean daLiPostojiSesija() {
		if (s == null) {
			return false;
		}
		return true;
	}

	public static void dodajKupca(String naziv, String adresa) throws Exception {
		if (daLiPostojiSesija()) {
			try {
				Kupac stari = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", naziv))
						.uniqueResult();
				// Ako postoji kupac vrati ga
				if (stari != null) {
					if (stari.getObrisan()) {
						stari.setObrisan(false);
						s.getSession().beginTransaction();
						s.getSession().update(stari);
						s.getTrasaction().commit();
					} else
						throw new Exception("Kupac kojeg ste unijeli već postoji");
				} else {
					noviKupac = new Kupac();
					noviKupac.setNaziv(naziv);
					noviKupac.setAdresa(adresa);
					noviKupac.setObrisan(false);
					s.getSession().beginTransaction();
					s.getSession().save(noviKupac);
					s.getTrasaction().commit();
				}
			} catch (Exception e) {
				logger.error(e);
				throw e;
			}
		}
	}
}
