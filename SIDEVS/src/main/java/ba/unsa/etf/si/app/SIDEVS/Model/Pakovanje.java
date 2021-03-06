package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pakovanje implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private int kolicina;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id")
	private Lot lot;
	@ManyToOne
	@JoinColumn(name="skladiste_id")
	private Skladiste skladiste;
	private Date datum_nabavke;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	public Skladiste getSkladiste() {
		return skladiste;
	}
	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}
	public Date setDatum_Nabavke() {
		return datum_nabavke;
	}
	public void setDatum_Nabavke(Date datum_nabavke) {
		this.datum_nabavke = datum_nabavke;
	}
}
