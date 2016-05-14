package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public final class LijekVM {
	final static Logger logger = Logger.getLogger(LijekVM.class);

	private static Sessions s;

	public Sessions getSessions() {
		return this.s;
	}
	public LijekVM(Sessions s) throws Exception {
		this.s = s;
	}

	public static Boolean daLiPostojiSesija() {
		if (s == null)
			return false;
		return true;
	}

	public static void dodajLijek(Long id, String naziv, String proizvodjac) throws Exception {
		if (daLiPostojiSesija()) {
			try {
				Lijek l = new Lijek();
				l.setId(id);
				l.setNaziv(naziv);
				l.setProizvodjac(proizvodjac);
				if (s.getSession().get(Lijek.class, id) == null && s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", naziv)) .setProjection(Projections.property("naziv")).uniqueResult() == null) {
					s.getSession().beginTransaction();
					s.getSession().save(l);
					s.getTrasaction().commit();
				} else
					throw new Exception("Lijek sa ovim ID ili nazivom već postoji");
			} catch (Exception ex) {
				logger.error(ex);
				throw ex;
			}

		} else
			throw new Exception("Niste prijavljeni!");
	}
}
