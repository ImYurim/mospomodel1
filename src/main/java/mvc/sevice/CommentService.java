package mvc.sevice;

import java.util.List;

import mvc.models.CommentDTO;

public interface CommentService {
	int selectComment(CommentDTO commentDTO) throws Exception;
	void insertComment(CommentDTO commentDTO) throws Exception;
	List<CommentDTO> getCommentlist(long movieid) throws Exception;
}
