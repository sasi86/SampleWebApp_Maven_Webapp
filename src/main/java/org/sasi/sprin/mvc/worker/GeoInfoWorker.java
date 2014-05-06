package org.sasi.sprin.mvc.worker;

import java.util.List;

import org.sasi.sprin.mvc.model.City;
import org.sasi.sprin.mvc.model.Country;
import org.sasi.sprin.mvc.model.State;

public interface GeoInfoWorker {
	public List<Country> getCountry();
	public List<State> getState(int countryId);
	public List<City> getCity(int stateId);
}
