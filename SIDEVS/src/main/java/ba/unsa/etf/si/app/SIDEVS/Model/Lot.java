package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lot implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private int broj_lota;
	@ManyToOne
    @JoinColumn(name="lijek_id")
	private Lijek lijek;
	private Date rok_trajanja;
	private double tezina;
	private double ulazna_cijena;
	private int kolicina_tableta;
	@ManyToOne
    @JoinColumn(name="skladiste_id")
	private Skladiste skladiste;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "lotovi")
	private Set<Faktura> fakture = new HashSet<Faktura>(0);
	
	
	public int getBroj_lota() {
		return broj_lota;
	}
	public void setBroj_lota(int broj_lota) {
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
	public Skladiste getSkladiste() {
		return skladiste;
	}
	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}
	public Set<Faktura> getFakture() {
		return fakture;
	}
	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}
}
