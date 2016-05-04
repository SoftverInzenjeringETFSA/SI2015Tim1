package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import ba.unsa.etf.si.app.SIDEVS.Model.*;

public final class EvidencijaKupcaVM {

	private Sessions s;
	
	public Sessions getSessions(){
		return this.s;
	}
	
	private EvidencijaKupcaVM kupac;
	
	public EvidencijaKupcaVM getKupac(){
		return kupac;
	}
	
	public void setKupac(EvidencijaKupcaVM kupac){
		this.kupac = kupac;
	}
	
	public EvidencijaKupcaVM(Sessions s) throws Exception{
		this.s = s;	
	}
	
	public Boolean daLiPostojiSesija(){
		if(s == null) 
			return false;
		return true;
	}
	
	public void dodajKupca(Long id, String imeIprezime, String adresa) throws Exception{
		
		if (daLiPostojiSesija()){
			try{
				Kupac k = new Kupac();
				k.setId(id);
				k.setNaziv(imeIprezime);
				k.setAdresa(adresa);
				k.setJmbg("1234567890123"); //provjeriti srs
				if (s.getSession().get(Kupac.class, id) == null){
					s.getSession().save(k);
					s.getTrasaction().commit();
			}
			else
				throw new Exception("ID kupca mora biti jedinstven");
		}
		catch(Exception ex){
			throw ex;
		}
		
	}
	else 
		throw new Exception("Niste prijavljeni!");
	}
	
}
