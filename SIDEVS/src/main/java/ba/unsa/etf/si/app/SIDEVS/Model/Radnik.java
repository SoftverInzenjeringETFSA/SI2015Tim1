package ba.unsa.etf.si.app.SIDEVS.Model;

import java.security.NoSuchAlgorithmException;
import javax.persistence.Entity;

@Entity
public class Radnik extends Korisnik {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Radnik() throws NoSuchAlgorithmException{
		super();
	}
}
