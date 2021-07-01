package mvc.models;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import oracle.sql.BLOB;

public class MovieDAOImpl implements MovieDAO{
	private DataSource dataSource;
	private static final MovieDAO movieDAO = new MovieDAOImpl();
	
	private MovieDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcimvc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MovieDAO getInstance() {
		return movieDAO;
	}

	@Override
	public MovieDTO getMovie(String title) throws SQLException, IOException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from  movie ");
		sql.append("where title=? ");
		
		System.out.println("dao:"+title);
		
		MovieDTO movie = null;
		
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setString(1, title);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					movie = new MovieDTO();
					movie.setId(rs.getLong("id"));
					movie.setTitle(rs.getString("title"));
					movie.setImagepath(rs.getString("imagepath"));
					
					System.out.println("dao:"+movie.getTitle());

				}
			}
		} 
		
		return movie;
	}
	
	
}
