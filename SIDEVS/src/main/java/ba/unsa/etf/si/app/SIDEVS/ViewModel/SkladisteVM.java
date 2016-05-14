package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public final class SkladisteVM {
	
	final static Logger logger = Logger.getLogger(SkladisteVM.class);
	
	public static Object[] dajSvaSkladista(Sessions s){
		Criteria criteria = s.getSession().createCriteria(Skladiste.class).setProjection(Projections.property("broj_skladista"));
		List<String> list_skladista = criteria.list();
		return list_skladista.toArray();
	}
}
