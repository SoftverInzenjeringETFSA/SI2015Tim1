package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Pakovanje;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public class PretragaLijekovaVM {
	private static Sessions s;
	
	public PretragaLijekovaVM(Sessions s){
		this.s = s;
	}
	
	public static Map<Integer, Integer> dajKolicinuLijekaUSkladistu(String naziv_lijeka) throws Exception{
		Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		//Lijek
		Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", naziv_lijeka))
				.uniqueResult();
		
		if(lijek == null) throw new Exception("Lijek ne postoji");
		
		//Lot
		Criteria criteria = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("lijek", lijek));
		List<Lot> lotovi = criteria.list();
		
		for (Lot lot : lotovi) {
			criteria = s.getSession().createCriteria(Pakovanje.class).add(Restrictions.eq("lot", lot));
			List<Pakovanje> pakovanja = criteria.list();
			for (Pakovanje pakovanje : pakovanja) {
				if(mapa.containsKey(pakovanje.getSkladiste().getBroj_skladista())){
					int temp = mapa.get(pakovanje.getSkladiste().getBroj_skladista()).intValue();
					mapa.put(pakovanje.getSkladiste().getBroj_skladista(), temp + pakovanje.getKolicina());
				}
				else{
					mapa.put(pakovanje.getSkladiste().getBroj_skladista(), pakovanje.getKolicina());
				}
			}
		}
		return mapa;
	}
}
