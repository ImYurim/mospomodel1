package mvc.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentDTO implements Serializable {
	private long id;
	private String user_id;
	private String content;
	private String category;
	private long movie_id;
}
