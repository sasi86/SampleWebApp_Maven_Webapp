package org.sasi.jdbc.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
@Component
public class CircleNamedParameterJdbcDemoImpl extends NamedParameterJdbcDaoSupport {
	
	//private DataSource dataSource;
	//private JdbcTemplate jdbcTemplate=null;

	/*public Circle getCircle(int id){
		Circle c=null;
		Connection conn=null;
		try{
			
			String driver = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db;create=true;");
			conn = dataSource.getConnection();
			PreparedStatement st = conn.prepareStatement("select * from circle where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				c= new Circle(id, rs.getString("name"));
			rs.close();
			st.close();
			return c;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
		
	}*/
	
	/*public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new  JdbcTemplate(dataSource);
	}*/

	/*public int getCircleCount(){
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}*/
	
	public String getCircleName(int id){
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = :id";
		return this.getNamedParameterJdbcTemplate().queryForObject(sql,param,String.class);
	}
	
	public Integer getCircleID(String circleName){
		SqlParameterSource  param= new MapSqlParameterSource("name", circleName);
		String sql = "SELECT ID FROM CIRCLE WHERE NAME = :name";
		return this.getNamedParameterJdbcTemplate().queryForObject(sql,param, Integer.class);
	}
	
	public Circle getCircleInfo(int circleId){
		SqlParameterSource  param= new MapSqlParameterSource("id", circleId);
		String sql="SELECT ID , NAME FROM CIRCLE WHERE ID = :id";
		return this.getNamedParameterJdbcTemplate().queryForObject(sql,param,new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}});
	}
	
	public List<Circle> getAllCircle(){		
		SqlParameterSource  param= new MapSqlParameterSource("where", "1");
		String sql = "SELECT * FROM CIRCLE WHERE 1 = :where";
		return this.getNamedParameterJdbcTemplate().query(sql, param, new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}});
	}

}
