package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Faktura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private double izlazna_cijena;
	@ManyToOne
	@JoinColumn(name="kupac_id")
	private Kupac kupac;
	@ManyToOne
	@JoinColumn(name="korisnik_id")
	private Korisnik korisnik;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "fakture_lot", 
			joinColumns = { @JoinColumn(name = "faktura_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "lot_id")})
	private Set<Lot> lotovi = new HashSet<Lot>(0);
	
	
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
	public Set<Lot> getLotovi() {
		return lotovi;
	}
	public void setLotovi(Set<Lot> lotovi) {
		this.lotovi = lotovi;
	}
}
