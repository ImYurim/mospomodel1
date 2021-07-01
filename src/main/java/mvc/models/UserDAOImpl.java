package mvc.models;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO{
	private DataSource dataSource;
	
	private static final UserDAO userDAO = new UserDAOImpl();
	
	private UserDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcimvc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserDAO getInstance() {
		return userDAO;
	}

	@Override
	public int userLogin(UserDTO user) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) ");
		sql.append("from  member ");
		sql.append("where id=? and password=? ");

		

		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					if(rs.getInt(1)==1) {
						return 1;
					}

				}
			}
		}
		return 0; 
		
		

		
	}

	@Override
	public void userSignup(UserDTO userDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		

		sql.append("insert into member(id,password,name) ");
		sql.append("values(?,?,?) ");
		
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){

			ps.setString(1, userDTO.getId());
			ps.setString(2, userDTO.getPassword());
			ps.setString(3, userDTO.getName());

			ps.executeUpdate();
		} 
			
		
	}

}
