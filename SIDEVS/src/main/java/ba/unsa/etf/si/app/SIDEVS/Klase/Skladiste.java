package ba.unsa.etf.si.app.SIDEVS.Klase;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skladiste  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int broj_skladista;

	public int getBroj_skladista() {
		return broj_skladista;
	}

	public void setBroj_skladista(int broj_skladista) {
		this.broj_skladista = broj_skladista;
	}

}
