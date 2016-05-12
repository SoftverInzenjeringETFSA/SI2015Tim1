package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=6)
	private String broj_lota;
	@ManyToOne
    @JoinColumn(name="lijek_id")
	private Lijek lijek;
	private Date rok_trajanja;
	private double tezina;
	private double ulazna_cijena;
	private int kolicina_tableta;
	private Date datum_ulaza;
	//private Date datum_otpisa;
	@OneToMany(mappedBy = "lot")
	private Set<FakturaLot> fakture_lotovi = new HashSet<FakturaLot>(0);
	@OneToMany(mappedBy = "lot")
	private Set<Pakovanje> pakovanja = new HashSet<Pakovanje>(0);
	
	public String getBroj_lota() {
		return broj_lota;
	}
	public void setBroj_lota(String broj_lota) {
		this.broj_lota = broj_lota;
	}
	public Lijek getLijek() {
		return lijek;
	}
	public void setLijek(Lijek lijek) {
		this.lijek = lijek;
	}
	public Date getRok_trajanja() {
		return rok_trajanja;
	}
	public void setRok_trajanja(Date rok_trajanja) {
		this.rok_trajanja = rok_trajanja;
	}
	public double getTezina() {
		return tezina;
	}
	public void setTezina(double tezina) {
		this.tezina = tezina;
	}
	public double getUlazna_cijena() {
		return ulazna_cijena;
	}
	public void setUlazna_cijena(double ulazna_cijena) {
		this.ulazna_cijena = ulazna_cijena;
	}
	public int getKolicina_tableta() {
		return kolicina_tableta;
	}
	public void setKolicina_tableta(int kolicina_tableta) {
		this.kolicina_tableta = kolicina_tableta;
	}
	public Date getDatum_ulaza() {
		return datum_ulaza;
	}
	public void setDatum_ulaza(Date date) {
		this.datum_ulaza = date;
	}

	public Set<FakturaLot> getFaktureLotovi() {
		return fakture_lotovi;
	}
	public void setFaktureLotovi(Set<FakturaLot> fakture_lotovi) {
		this.fakture_lotovi = fakture_lotovi;
	}
	public Set<Pakovanje> getPakovanja() {
		return pakovanja;
	}
	public void setPakovanja(Set<Pakovanje> pakovanja) {
		this.pakovanja = pakovanja;
	}
}
