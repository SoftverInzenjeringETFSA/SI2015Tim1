package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Lot;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;

public class PretragaLijekovaVM {
  
	public static long kolicinaLijeka(Sessions ses,String naziv,String broj_skladista) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			long suma=0;
			
			//kompletna lista lijekova
			Criteria criteria = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("lijek"));
			List<Lijek> listaLijekova = criteria.list();
			
			//id oznacenog lijeka
			Criteria criteria1 = ses.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv).ignoreCase());
			List<Lijek> listaLijekova1 = criteria1.list();	
			long id=listaLijekova1.get(0).getId();
            
			//lista kolicine svakog lijeka
			Criteria criteria2 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("kolicina_tableta"));
			List<Integer> listaKolicine = criteria2.list();
			
			JOptionPane.showMessageDialog(null,listaKolicine, "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
			//lista skladista
			Criteria criteria3 = ses.getSession().createCriteria(Lot.class).setProjection(Projections.property("skladiste"));
			List<Skladiste> listaSkladista = criteria3.list();
			
			
			
			for(int i=0;i<listaLijekova.size();i++)
			{   
				JOptionPane.showMessageDialog(null,listaLijekova.get(i).getId()+" skladiste "+listaSkladista.get(i).getBroj_skladista()+" kolicina"+listaKolicine.get(i), "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
				
				if(listaLijekova.get(i).getId()==id && Integer.toString(listaSkladista.get(i).getBroj_skladista()).equals(broj_skladista))
				{	
					suma=suma+listaKolicine.get(i);
				}
			}
		
			
			return suma;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return 0;	
		
	}
	
}
