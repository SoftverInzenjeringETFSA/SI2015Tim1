package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public class OtpisLijekovaVM {
	final static Logger logger = Logger.getLogger(OtpisLijekovaVM.class);
	private static Sessions sesija;
	
	public OtpisLijekovaVM(Sessions sesija){
		this.sesija=sesija;
	}
	
	public static boolean otpisLijeka(String naziv,String broj_lota,String broj_skladista) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			
			List<Lijek> lijekovi = sesija.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv)).list();
			Lijek lijek = lijekovi.get(0);
			
            List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.like("broj_lota", broj_lota)).list();
			Lot brisiLot = lotovi.get(0);
			
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			brisiLot.setDatum_otpisa(date);
			
			sesija.getSession().beginTransaction();
			sesija.getSession().update(brisiLot);
			sesija.getTrasaction().commit();
			
			
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}
	
	public static void otpisiLijek(String lotBrisanje){

		Transaction t = sesija.getSession().beginTransaction();
		//Lot
		List<Lot> lotovi = sesija.getSession().createCriteria(Lot.class).add(Restrictions.like("broj_lota", lotBrisanje)).list();
		Lot lot = lotovi.get(0);		
		
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		lot.setDatum_otpisa(date);
		
		sesija.getSession().beginTransaction();
		sesija.getSession().update(lot);
		sesija.getTrasaction().commit();
		
		/*
		l.setBroj_lota(lot.getBroj_lota());
		l.setDatum_ulaza(lot.getDatum_ulaza());
		l.setFaktureLotovi(lot.getFaktureLotovi());
		l.setKolicina_tableta(l.getKolicina_tableta());
		l.setLijek(lot.getLijek());
		l.setPakovanja(lot.getPakovanja());
		l.setRok_trajanja(lot.getRok_trajanja());
		l.setTezina(lot.getTezina());
		l.setUlazna_cijena(lot.getUlazna_cijena());
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		l.setDatum_otpisa(date  );

		sesija.getSession().delete(lot);
		t.commit();
		t = sesija.getSession().beginTransaction();
		sesija.getSession().save(l);
		
		t.commit();
		*/
	}


public static List<String> vracaLotove(Lijek lijek, Skladiste skladiste){

	List<Lot> listaLotova = sesija.getSession().createCriteria(Lot.class).list();
	
	List<String> lotovi = new ArrayList<String>();
	
	for (Lot lot: listaLotova){
		Set<Pakovanje> pakovanja = lot.getPakovanja();
		for (Pakovanje p: pakovanja){
			if (p.getSkladiste()== skladiste && lot.getLijek() == lijek)	
				lotovi.add(lot.getBroj_lota());
		}
	}

	return lotovi;
}
}
