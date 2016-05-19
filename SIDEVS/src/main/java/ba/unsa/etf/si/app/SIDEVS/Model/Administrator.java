package ba.unsa.etf.si.app.SIDEVS.Model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

@Entity
public class Administrator extends Korisnik{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrator() throws NoSuchAlgorithmException{
		super();
	}
}
