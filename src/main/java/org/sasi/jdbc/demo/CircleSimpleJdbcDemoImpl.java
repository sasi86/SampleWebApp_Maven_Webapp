package org.sasi.jdbc.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Component;
@Component
public class CircleSimpleJdbcDemoImpl extends SimpleJdbcDaoSupport {
	
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

	public int getCircleCount(){
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}
	
	public String getCircleName(int id){
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return this.getSimpleJdbcTemplate().queryForObject(sql,String.class,id);
	}
	
	public Integer getCircleID(String circleName){
		String sql = "SELECT ID FROM CIRCLE WHERE NAME = ?";
		return this.getSimpleJdbcTemplate().queryForObject(sql,Integer.class, circleName);
	}
	
	public Circle getCircleInfo(int circleId){
		String sql="SELECT ID , NAME FROM CIRCLE WHERE ID = ?";
		return this.getSimpleJdbcTemplate().queryForObject(sql,new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}},circleId);
	}
	
	public List<Circle> getAllCircle(){		
		String sql = "SELECT * FROM CIRCLE";
		return this.getSimpleJdbcTemplate().query(sql, new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}});
	}

}
