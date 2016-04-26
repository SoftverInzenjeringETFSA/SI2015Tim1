package ba.unsa.etf.si.app.SIDEVS.Model;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class ObrisanLot extends Lot{

	private static final long serialVersionUID = 1L;
	
	private Date datum_otpisa;

	public Date getDatum_otpisa() {
		return datum_otpisa;
	}

	public void setDatum_otpisa(Date datum_otpisa) {
		this.datum_otpisa = datum_otpisa;
	}
	
}
