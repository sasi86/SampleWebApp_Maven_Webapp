package org.sasi.spring.mvc.manager;

import java.util.List;

import org.sasi.spring.mvc.dao.GeoInfoDAO;
import org.sasi.spring.mvc.model.City;
import org.sasi.spring.mvc.model.Country;
import org.sasi.spring.mvc.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class GeoInfoManagerImpl implements GeoInfoManager {
	
	@Autowired
	private GeoInfoDAO geoInfoDAOImpl;
	

	@Override
	public List<Country> getCountry() {
		return geoInfoDAOImpl.getCountry();
	}


	


	@Override
	public List<State> getState(int countryId) {
		return geoInfoDAOImpl.getState(countryId);
	}





	@Override
	public List<City> getCity(int stateId) {
		return geoInfoDAOImpl.getCity(stateId);
	}

}
