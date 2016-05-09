package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public class OtpisLijekovaVM {
	public static boolean otpisLijeka(Sessions ses,String naziv,String broj_lota,String broj_skladista, Integer kolicina_tableta) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			Transaction t = ses.getSession().beginTransaction();
			
			Integer novaKolicina=0;
			//kompletna lista lijekova
			Criteria criteria = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("lijek"));
			List<Lijek> listaLijekova = criteria.list();
			
			//id oznacenog lijeka
			Criteria criteria1 = ses.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			List<Lijek> listaLijekova1 = criteria1.list();
			long id=listaLijekova1.get(0).getId();
            
			//komplenta lista lotova
			Criteria criteria4 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("broj_lota"));
			List<String> listaLotova = criteria4.list();
						
			//lista skladista
			Criteria criteria3 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("skladiste"));
			List<Skladiste> listaSkladista = criteria3.list();
			
			//lista kolicine svakog lijeka
			Criteria criteria5 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("kolicina_tableta"));
			List<Integer> listaKolicine = criteria5.list();
		    
			
			for(int i=0;i<listaLijekova.size();i++)
			{   
			
				if(listaLijekova.get(i).getId()==id && (Integer.toString(listaSkladista.get(i).getBroj_skladista()).equals(broj_skladista)) && (listaLotova.get(i).equals(broj_lota)))
				{	
					//ovdje ispisujem da vidim jel fakat za selektovani lot umanjuje vrijednost, fali samo da se update baza
					novaKolicina=listaKolicine.get(i)-kolicina_tableta;
					JOptionPane.showMessageDialog(null, novaKolicina, "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
					listaKolicine.set(i, novaKolicina);
					JOptionPane.showMessageDialog(null, listaKolicine.get(i), "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		//FALI OVDJE UPDATE U BAZI
			t.commit();	
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


public static Object[] vracaLotove(Sessions ses,String naziv,String broj_skladista){
	//kompletna lista lijekova
	Criteria criteria = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("lijek"));
	List<Lijek> listaLijekova = criteria.list();
	
	//id oznacenog lijeka
	Criteria criteria1 = ses.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv).ignoreCase());
	List<Lijek> listaLijekova1 = criteria1.list();
	long id=listaLijekova1.get(0).getId();
	
	//lista skladista
	Criteria criteria3 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("skladiste"));
	List<Skladiste> listaSkladista = criteria3.list();
	
	//komplenta lista lotova
	Criteria criteria4 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("broj_lota"));
	List<String> listaLotova = criteria4.list();
	
	List<String> lotovi = new ArrayList<String>();
	
	for(int i=0;i<listaLijekova.size();i++)
	{
		if(listaLijekova.get(i).getId()==id && Integer.toString(listaSkladista.get(i).getBroj_skladista()).equals(broj_skladista))
		{	
			lotovi.add(listaLotova.get(i));
		}
	}
	
	return lotovi.toArray();
}
}
