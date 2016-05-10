package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.sql.Date;
import java.text.DateFormat;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public class LotVM {

	private Sessions s;

	public Sessions getSessions() {
		return this.s;
	}

	public LotVM(Sessions s) throws Exception {
		this.s = s;
	}

	public Boolean daLiPostojiSesija() {
		if (s == null)
			return false;
		return true;
	}

	public void dodajLot(String broj_lota, Double tezina, Double ulazna_cijena, Date rok_trajanja, int kolicina,
			Lijek lijek, Skladiste skladiste) throws Exception {
		if (daLiPostojiSesija()) {
			try {
				Lot l = new Lot();
				l.setBroj_lota(broj_lota);
				l.setTezina(tezina);
				l.setUlazna_cijena(ulazna_cijena);
				l.setDatum_ulaza(new java.util.Date());
				
				if (kolicina != 0)
					l.setKolicina_tableta(kolicina);
				l.setRok_trajanja(rok_trajanja);
				l.setLijek(lijek);
				l.setSkladiste(skladiste);
				if(s.getSession().get(Lot.class, broj_lota) == null){
					s.getSession().beginTransaction();
					s.getSession().save(l);
					s.getTrasaction().commit();
				}
				else throw new Exception("Lot sa ovim brojem već postoji");
			} catch (Exception e) {
				throw e;
			}
		}
		else throw new Exception("Niste prijavljeni");

	}
}
