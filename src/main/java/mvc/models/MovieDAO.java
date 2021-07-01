package mvc.models;

import java.io.IOException;
import java.sql.SQLException;

public interface MovieDAO {
	public MovieDTO getMovie(String title) throws SQLException, IOException;
}
