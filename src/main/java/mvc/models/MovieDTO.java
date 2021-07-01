package mvc.models;

import java.io.Serializable;
import java.sql.Blob;

import lombok.Data;

@Data
public class MovieDTO implements Serializable {
	private long id;
	private String title;
	private String imagepath;
	private Blob posterimage;
	
}
