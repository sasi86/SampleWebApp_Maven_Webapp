package org.sasi.spring.mvc.worker;

import java.util.List;

import org.sasi.spring.mvc.manager.GeoInfoManager;
import org.sasi.spring.mvc.model.City;
import org.sasi.spring.mvc.model.Country;
import org.sasi.spring.mvc.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeoInfoWorkerImpl implements GeoInfoWorker {
	
	@Autowired
	private GeoInfoManager geoInfoManagerImpl;
	
	

	@Override
	public List<Country> getCountry() {
		return geoInfoManagerImpl.getCountry();
	}


	


	@Override
	public List<State> getState(int countryId) {
		return geoInfoManagerImpl.getState(countryId);
	}





	@Override
	public List<City> getCity(int stateId) {
		return geoInfoManagerImpl.getCity(stateId);
	}
	
}
