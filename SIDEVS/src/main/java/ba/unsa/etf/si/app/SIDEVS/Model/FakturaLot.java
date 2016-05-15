package ba.unsa.etf.si.app.SIDEVS.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "faktura_lot")
public class FakturaLot implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	private long id;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faktura_id")  
	private Faktura faktura;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id")
	private Lot lot;
	@ManyToOne
    @JoinColumn(name="skladiste_id")
	private Skladiste skladiste;
	private int kolicina;
	private double cijena;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Faktura getFaktura() {
		return faktura;
	}
	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
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
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	
	
}
