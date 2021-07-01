package mvc.sevice;

import java.util.List;

import mvc.models.CommentDAO;

import mvc.models.CommentDAOImpl;
import mvc.models.CommentDTO;

public class CommentServiceImpl implements CommentService{
	private static final CommentService commentService = new CommentServiceImpl();
	private CommentDAO commentDAO = CommentDAOImpl.getInstance();
	
	private CommentServiceImpl() {
		
	}
	public static CommentService getInstance() {
		return commentService;
	}
	@Override
	public int selectComment(CommentDTO commentDTO) throws Exception {
		System.out.println("여기는 service");
		return commentDAO.selectComment(commentDTO);
	}
	@Override
	public void insertComment(CommentDTO commentDTO) throws Exception {

		commentDAO.insertComment(commentDTO);
	}
	@Override
	public List<CommentDTO> getCommentlist(long movieid) throws Exception {
		System.out.println("comment serviceimpl");
		List<CommentDTO> comment = commentDAO.getCommentlist(movieid);
		return comment;
	}

	
}
