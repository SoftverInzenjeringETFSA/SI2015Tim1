package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.hibernate.Transaction;

import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.*;


import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;

public class DodavanjeKorisnikaVMTest {

	@Test
	public void KreiranjeKorisnikaTest() {
	
		try
		{
			
			Sessions ses=new  Sessions("admin", "password");
			String ime="Adnela";
			String prezime="Cavcic"; 
			String maticniBroj="1702992175069"; 
			String brojTelefona="38761111111"; 
			String email="mala_slatka@si.etf.unsa.ba";
			String radnoMjesto="Menadzer"; 
			String datumPocetkaRada="04.05.2013.";
			String adresa="Adresa bb"; 
			String tipKorisnika="Menadzer";
			
			Boolean rezultat= ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(ses, ime, prezime, maticniBroj, brojTelefona, email, radnoMjesto, datumPocetkaRada, adresa, tipKorisnika);
			assertTrue(rezultat);
			
			
		}
		catch(Exception e)
		{
			assertTrue(false);
			
		}
		
		
	
	}
}

