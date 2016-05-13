package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.*;

public class IzvjestajNaOsnovuLotaVM {
	public static String datum_ulaza (Sessions ses, String broj)throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		//izabrani lot
		Criteria criteria = ses.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", broj));
		List<Lot> listaLotova = criteria.list();
		Date datum_ulaza = listaLotova.get(0).getDatum_ulaza();
		
		//konverzija date to string
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String datum_ulaza_string=formatter.format(datum_ulaza);
		return datum_ulaza_string;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "-";
	}
	
	public static String kolicina_ulaza (Sessions ses, String broj)throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		//izabrani lot
		Criteria criteria = ses.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", broj));
		List<Lot> listaLotova = criteria.list();
		int kolicina_ulaza=listaLotova.get(0).getKolicina_tableta();
		return String.valueOf(kolicina_ulaza);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static String datum_otpisa (Sessions ses, String broj_lota) throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		//izabrani lot
		Criteria criteria = ses.getSession().createCriteria(ObrisanLot.class).add(Restrictions.eq("broj_lota", broj_lota));
		List<ObrisanLot> listaObrisanihLotova = criteria.list();
		if (listaObrisanihLotova.size()!=0) {
		Date datum_otpisa = listaObrisanihLotova.get(0).getDatum_otpisa();
		
		//konverzija date to string
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String datum_otpisa_string=formatter.format(datum_otpisa);
		return datum_otpisa_string;}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "-";
	}
	
	public static String kolicina_otpisa (Sessions ses, String broj_lota) throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		Criteria criteria = ses.getSession().createCriteria(ObrisanLot.class).add(Restrictions.eq("broj_lota", broj_lota));
		List<ObrisanLot> listaObrisanihLotova = criteria.list();
		if (listaObrisanihLotova.size()==0) return "0";
		int kolicina_otpisa = listaObrisanihLotova.get(0).getKolicina_tableta();
		return String.valueOf(kolicina_otpisa);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static List<String> lista_datumi_izlaza (Sessions ses, String broj_lota)throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		Criteria criteria = ses.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", broj_lota));
		List<Lot> listaLotova = criteria.list();
		List<FakturaLot> listaFakturaLot = new ArrayList<FakturaLot> (listaLotova.get(0).getFaktureLotovi());
		List<String> lista_datuma_izlaza = new ArrayList<String>();
		for (int i=0;i<listaFakturaLot.size();i++)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String datum_izlaza_string=formatter.format(listaFakturaLot.get(i).getFaktura().getDatum_kreiranja());
			lista_datuma_izlaza.add(datum_izlaza_string);
		}
		return lista_datuma_izlaza;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static List<String> lista_kolicine_izlaza (Sessions ses, String broj_lota)throws NoSuchAlgorithmException,InvalidKeySpecException
	{
		try
		{
		Criteria criteria = ses.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", broj_lota));
		List<Lot> listaLotova = criteria.list();
		List<FakturaLot> listaFakturaLot = new ArrayList<FakturaLot> (listaLotova.get(0).getFaktureLotovi());
		List<String> lista_kolicine_izlaza = new ArrayList<String>();
		for (int i=0;i<listaFakturaLot.size();i++)
		{
			String kolicina_izlaza_string=String.valueOf(listaFakturaLot.get(i).getKolicina());
			lista_kolicine_izlaza.add(kolicina_izlaza_string);
		}
		return lista_kolicine_izlaza;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
}
