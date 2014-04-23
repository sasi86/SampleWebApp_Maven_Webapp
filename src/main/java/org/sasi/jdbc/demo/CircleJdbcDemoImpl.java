package org.sasi.jdbc.demo;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
@Component
@Scope("session")
//
public class CircleJdbcDemoImpl extends JdbcDaoSupport {
	
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
		return this.getJdbcTemplate().queryForInt(sql);
	}
	
	public String getCircleName(int id){
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return this.getJdbcTemplate().queryForObject(sql,new Object[]{id} ,String.class);
	}
	
	public Integer getCircleID(String circleName){
		String sql = "SELECT ID FROM CIRCLE WHERE NAME = ?";
		return this.getJdbcTemplate().queryForObject(sql,new Object[]{circleName} ,Integer.class);
	}
	
	public Circle getCircleInfo(int circleId){
		String sql="SELECT ID , NAME FROM CIRCLE WHERE ID = ?";
		return this.getJdbcTemplate().queryForObject(sql,new Object[]{circleId},new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}});
	}
	
	public List<Circle> getAllCircle(){		
		String sql = "SELECT * FROM CIRCLE";
		return this.getJdbcTemplate().query(sql, new RowMapper<Circle>(){
			@Override
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();				
				circle.setId(rs.getInt("ID"));
				circle.setName(rs.getString("NAME"));
				return circle;
			}});
	}
	
	public void insertNewCircle(Circle circle){
		String sql="INSERT INTO CIRCLE(ID, NAME) VALUES(?,?)";
		this.getJdbcTemplate().update(sql, circle.getId(), circle.getName());
				
	}
	
	public void createNewTable(){
		String sql="CREATE TABLE LABEL (LABELNAME VARCHAR(75), VAL  CLOB(1024 K))";
		this.getJdbcTemplate().execute(sql);
	}
	
	public void insertIntoLable(){
		String sql="INSERT INTO LABEL (LABELNAME, VAL) VALUES(?,?)";
		this.getJdbcTemplate().update(sql, "fName","First Name");
	}
	
	public String getLabel(String Val){
		String sql="SELECT VAL FROM LABEL WHERE  LABELNAME = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{Val}, String.class);
	}
	
	public List<Label> getAllLabel(){
		String sql="SELECT * FROM LABEL";
		return this.getJdbcTemplate().query(sql,new RowMapper<Label>() {
			@Override
			public Label mapRow(ResultSet rs, int rowNum) throws SQLException {
				Label label = new Label();
				label.setLableName(rs.getString("LABELNAME"));
				Clob clobVal = rs.getClob("VAL");
				label.setVal(clobVal.getSubString(1, (int) clobVal.length()));
				return label;
			}
			
		});
	}

}
