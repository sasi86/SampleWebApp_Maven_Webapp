package org.sasi.spring.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sasi.spring.mvc.model.City;
import org.sasi.spring.mvc.model.Country;
import org.sasi.spring.mvc.model.State;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class GeoInfoDAOImpl extends JdbcDaoSupport implements GeoInfoDAO{

	@Override
	public List<Country> getCountry() {
		String sql="SELECT * FROM COUNTRY";
		return this.getJdbcTemplate().query(sql, new RowMapper<Country>() {
			public Country mapRow(ResultSet rs, int rowNum) throws SQLException{
				Country country = new Country();
				country.setCountryId(rs.getInt("COUNTRY_ID"));
				country.setCountryName(rs.getString("COUNTRY_NAME"));		
				return country;
			}
		});
	}

	@Override
	public List<State> getState(int countryId) {
		String sql="SELECT * FROM STATE WHERE COUNTRY_ID = ?";
		return this.getJdbcTemplate().query(sql, new Object[]{countryId}, new RowMapper<State>() {
			@Override
			public State mapRow(ResultSet rs, int arg1) throws SQLException {
				State state = new State();
				state.setStateId(rs.getInt("STATE_ID"));
				state.setStateName(rs.getString("STATE_NAME"));
				return state;
			}
		});
	}

	@Override
	public List<City> getCity(int stateId) {
		String sql="SELECT * FROM CITY WHERE STATE_ID = ?";
		return this.getJdbcTemplate().query(sql, new Object[]{stateId}, new RowMapper<City>() {
			@Override
			public City mapRow(ResultSet rs, int arg1) throws SQLException {
				City city = new City();
				city.setCityId(rs.getInt("CITY_ID"));
				city.setCityName(rs.getString("CITY_NAME"));
				return city;
			}
		});
	}

}
