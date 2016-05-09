package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public final class EvidencijaKupca {
	

	
	private Sessions s;
	
	public Sessions getSessions(){
		return this.s;
	}
	
	public EvidencijaKupca(Sessions s)throws Exception{
		this.s = s;
	}
	
	public Boolean daLiPostojiSesija(){
		if (s == null){
			return false;
		}
			return true;
	}
	
	
	public void dodajKupca(String naziv, String adresa) throws Exception{
		if (daLiPostojiSesija()){
			try{
				Kupac noviKupac = new Kupac();
				noviKupac.setNaziv(naziv);
				noviKupac.setAdresa(adresa);
				//provjera da li postoji u bazi?
				if (true)
						/*s.getSession().get(Kupac.class, naziv) == null &&
					s.getSession().get(Kupac.class, adresa) == null)*/
						{
						
					s.getSession().beginTransaction();
					s.getSession().save(noviKupac);
					s.getTrasaction().commit();
				}
				else throw new Exception("Kupac kojeg ste unijeli već postoji");
			}
			catch(Exception e){
				throw e;
			}
		}
	}
}
