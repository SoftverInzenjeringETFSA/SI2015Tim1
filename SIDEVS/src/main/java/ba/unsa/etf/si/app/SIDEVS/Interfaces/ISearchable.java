package ba.unsa.etf.si.app.SIDEVS.Interfaces;

import java.util.Collection;

public interface ISearchable<E, V>{
	public Collection<E> search(V value);
}

	
