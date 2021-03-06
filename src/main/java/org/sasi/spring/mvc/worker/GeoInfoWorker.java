package org.sasi.spring.mvc.worker;

import java.util.List;

import org.sasi.spring.mvc.model.City;
import org.sasi.spring.mvc.model.Country;
import org.sasi.spring.mvc.model.State;

public interface GeoInfoWorker {
	public List<Country> getCountry();
	public List<State> getState(int countryId);
	public List<City> getCity(int stateId);
}
