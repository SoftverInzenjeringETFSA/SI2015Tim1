package ba.unsa.etf.si.app.SIDEVS.Model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

@Entity
public class Administrator extends Korisnik{
	public Administrator() throws NoSuchAlgorithmException{
		super();
	}
}
