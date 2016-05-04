package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public final class LijekVM {

	private Sessions s;

	public Sessions getSessions() {
		return this.s;
	}

	private LijekVM lijek;

	public LijekVM getLijek() {
		return lijek;
	}

	public void setLijek(LijekVM lijek) {
		this.lijek = lijek;
	}

	public LijekVM(Sessions s) throws Exception {
		this.s = s;
	}

	public Boolean daLiPostojiSesija() {
		if (s == null)
			return false;
		return true;
	}

	public void dodajLijek(Long id, String naziv, String proizvodjac) throws Exception {
		if (daLiPostojiSesija()) {
			try {
				Lijek l = new Lijek();
				l.setId(id);
				l.setNaziv(naziv);
				l.setProizvodjac(proizvodjac);
				if (s.getSession().get(Lijek.class, id) == null) {
					s.getSession().save(l);
					s.getTrasaction().commit();
				} else
					throw new Exception("Lijek sa ovim ID već postoji");
			} catch (Exception ex) {
				throw ex;
			}

		} else
			throw new Exception("Niste prijavljeni!");
	}
}
