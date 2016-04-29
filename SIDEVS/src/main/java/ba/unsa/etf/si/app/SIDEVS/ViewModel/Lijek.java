package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public class Lijek {

	private Sessions s;
	public Sessions getSessions(){
		return this.s;
	}
	private Lijek lijek;
	public Lijek getLijek() {
		return lijek;
	}
	public void setLijek(Lijek lijek) {
		this.lijek = lijek;
	}
	public Lijek(Sessions s) throws Exception{
		this.s = s;
	}
	public Boolean daLiPostojiSesija(){
		if(s == null) return false;
		return true;
	}
}
