package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Faktura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private double izlazna_cijena;
	private Date datum_kreiranja;
	@ManyToOne
	@JoinColumn(name="kupac_id")
	private Kupac kupac;
	@ManyToOne
	@JoinColumn(name="korisnik_id")
	private Korisnik korisnik;
	@OneToMany(mappedBy = "faktura")
	private Set<FakturaLot> fakture_lotovi = new HashSet<FakturaLot>(0);
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getIzlazna_cijena() {
		return izlazna_cijena;
	}
	public void setIzlazna_cijena(double izlazna_cijena) {
		this.izlazna_cijena = izlazna_cijena;
	}
	public Date getDatum_kreiranja() {
		return datum_kreiranja;
	}
	public void setDatum_kreiranja(Date datum_kreiranja) {
		this.datum_kreiranja = datum_kreiranja;
	}
	public Kupac getKupac() {
		return kupac;
	}
	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Set<FakturaLot> getFaktureLotovi() {
		return fakture_lotovi;
	}
	public void setFaktureLotovi(Set<FakturaLot> fakture_lotovi) {
		this.fakture_lotovi = fakture_lotovi;
	}
}
