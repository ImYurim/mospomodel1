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

public class ArticleDAOImpl implements ArticleDAO {

	private DataSource dataSource;
	private static final ArticleDAO memberDAO = new ArticleDAOImpl();
	private ArticleDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xcimvc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArticleDAO getInstance() {
		return memberDAO;
	}

	@Override
	public void insertArticle(ArticleDTO articleDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO t_board(no, title, name, password, content) ");
		sql.append("VALUES(seq_board.nextval, ?, ?, ?, ?) ");

		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setString(1, articleDTO.getTitle());
			ps.setString(2, articleDTO.getName());
			ps.setString(3, articleDTO.getPassword());
			ps.setString(4, articleDTO.getContent());
			ps.executeUpdate();			
		}
	}

	@Override
	public List<ArticleDTO> getArticleList() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select B.* ");
		sql.append("from  (select rownum as rnum, A.* ");
		sql.append("       from  (select no, title, name, regdate, readcount from t_board ");
		sql.append("              order by no desc) A) B ");
		sql.append("where rnum between 1 and 10 ");

		List<ArticleDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					ArticleDTO articleDTO = new ArticleDTO();
					articleDTO.setNo(rs.getLong("no"));
					articleDTO.setTitle(rs.getString("title"));
					articleDTO.setName(rs.getString("name"));
					articleDTO.setRegdate(rs.getDate("regdate"));
					articleDTO.setReadcount(rs.getInt("readcount"));
					list.add(articleDTO);
				}
			}
		} 
		return list;
	}

	
	@Override
	public ArticleDTO getDetail(long no) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select no, title, name, regdate, readcount, content ");
		sql.append("from   t_board ");
		sql.append("where  no=? ");

		ArticleDTO articleDTO = null;
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			ps.setLong(1, no);
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					articleDTO = new ArticleDTO();
					articleDTO.setNo(rs.getLong("no"));
					articleDTO.setTitle(rs.getString("title"));
					articleDTO.setName(rs.getString("name"));
					articleDTO.setRegdate(rs.getDate("regdate"));
					articleDTO.setReadcount(rs.getInt("readcount"));
					articleDTO.setContent(rs.getString("content"));
				}
			}
		} 
		return articleDTO;
	}
	
	@Override
	public void updateReadcount(long no) throws SQLException {
		
	}
	
	@Override
	public int updateArticle(ArticleDTO articleDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_board SET ");
		sql.append("       title=?     ");
		sql.append("      ,name=?      ");
		sql.append("      ,content=?   ");
		sql.append("WHERE  no=? AND password=? ");

		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, articleDTO.getTitle());
			pstmt.setString(2, articleDTO.getName());
			pstmt.setString(3, articleDTO.getContent());
			pstmt.setLong(4, articleDTO.getNo());
			pstmt.setString(5, articleDTO.getPassword());
			return pstmt.executeUpdate();
		}		
	}
}
