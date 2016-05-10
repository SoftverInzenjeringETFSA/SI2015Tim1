package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.text.DateFormat;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;
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
			Lijek lijek, Skladiste skladiste, int kolicina_pakovanja) throws Exception {
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
				if(s.getSession().get(Lot.class, broj_lota) == null){
					Pakovanje p = new Pakovanje();
					p.setLot(l);
					p.setSkladiste(skladiste);
					p.setKolicina(kolicina_pakovanja);
					s.getSession().beginTransaction();
					s.getSession().save(p);
					s.getTrasaction().commit();
				}
				else {
					//Pronadjemo sva pakovanja sa ovim lotom
					Criteria criteria = s.getSession().createCriteria(Pakovanje.class).add(Restrictions.eq("lot", l));
					List<Pakovanje> pakovanja = criteria.list();
					for (Pakovanje pakovanje : pakovanja) {
						if(pakovanje.getSkladiste() == skladiste) {
							throw new Exception("Lot sa ovim brojem već postoji u skladištu");
						}
					}
					kreirajPakovanje(l, skladiste, kolicina_pakovanja);
				}
			} catch (Exception e) {
				throw e;
			}
		}
		else throw new Exception("Niste prijavljeni");

	}

	public String dodajUPaket(Lot lot, Skladiste skladiste, int kolicina) throws Exception {
		if(lot == null) throw new Exception ("Lot ne postoji");
		if(skladiste == null) throw new Exception ("Skladiste ne postoji");
		if(kolicina <= 0) throw new Exception("Kolicina mora biti pozitivna");
		Criteria criteria = s.getSession().createCriteria(Pakovanje.class).add(Restrictions.eq("lot", lot));
		List<Pakovanje> pakovanja = criteria.list();
		for (Pakovanje pakovanje : pakovanja) {
			if(pakovanje.getSkladiste() == skladiste) {
				pakovanje.setKolicina(pakovanje.getKolicina() + kolicina);
				s.getSession().beginTransaction();
				s.getSession().saveOrUpdate(pakovanje);
				s.getTrasaction().commit();
				return "Uspješno ste ažurirali stanje u skladištu";
			}
		}
		//Ako ne postoji ovo pakovanje kreiraj novo
		kreirajPakovanje(lot, skladiste, kolicina);
		return "Uspješni ste dodali lot u skladište";
	}
	
	private void kreirajPakovanje(Lot l, Skladiste skladiste, int kolicina){
		Pakovanje p = new Pakovanje();
		p.setLot(l);
		p.setSkladiste(skladiste);
		p.setKolicina(kolicina);
		s.getSession().beginTransaction();
		s.getSession().save(p);
		s.getTrasaction().commit();
	}
}
