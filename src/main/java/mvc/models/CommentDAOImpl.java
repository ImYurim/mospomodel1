package mvc.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAOImpl implements CommentDAO {
	private DataSource dataSource;
	private static final CommentDAO commentDAO = new CommentDAOImpl();
	private CommentDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcimvc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CommentDAO getInstance() {
		return commentDAO;
	}
	
	@Override
	public int selectComment(CommentDTO commentDTO) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as cnt ");
		sql.append("from moviecomment ");
		sql.append("where member_id =? and movie_id =? and category = ? ");
		
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			System.out.println("category:"+commentDTO.getCategory());
			System.out.println("movieid:"+commentDTO.getMovie_id());
			System.out.println("userid:"+commentDTO.getUser_id());
			ps.setString(1, commentDTO.getUser_id());
			ps.setLong(2, commentDTO.getMovie_id());
			ps.setString(3, commentDTO.getCategory());
			
				
			try (ResultSet rs = ps.executeQuery();) {
				if(rs.next()) {
					if(rs.getLong("cnt")==1) {
						System.out.println("있음");
						return 1;
					}
				}
			}
		} 
		System.out.println("없음");
		return 0;
	}
	
	@Override
	public void insertComment(CommentDTO commentDTO) throws SQLException {

		StringBuffer sql = new StringBuffer();
		

		sql.append("insert into moviecomment(id,content,category,movie_id,member_id) ");
		sql.append("values(sq_moviecomment.nextval,?,?,?,?) ");
		
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			System.out.println("content:"+commentDTO.getContent());
			ps.setString(1, commentDTO.getContent());
			ps.setString(2, commentDTO.getCategory());
			ps.setLong(3, commentDTO.getMovie_id());
			ps.setString(4, commentDTO.getUser_id());
			ps.executeUpdate();
		} 
				
			
		

	}

	@Override
	public List<CommentDTO> getCommentlist(long movieid) throws SQLException {
		System.out.println("commentDAO getCommentlist");
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from  moviecomment ");
		sql.append("where movie_id=? ");
		
		List<CommentDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setLong(1, movieid);
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					CommentDTO commentDTO = new CommentDTO();
					commentDTO.setCategory(rs.getString("category"));
					System.out.println("dtocategory:"+rs.getString("category"));
					commentDTO.setId(rs.getLong("id"));
					commentDTO.setContent(rs.getString("content"));
					System.out.println("commentcontent:"+rs.getString("content"));
					commentDTO.setMovie_id(rs.getLong("movie_id"));
					commentDTO.setUser_id(rs.getString("member_id"));
					list.add(commentDTO);

				}
			}
		} 
		
		return list;
	}


	
}
