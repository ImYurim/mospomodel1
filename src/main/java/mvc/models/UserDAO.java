package mvc.models;

import java.sql.SQLException;

public interface UserDAO {
	public int userLogin(UserDTO user) throws SQLException;

	public void userSignup(UserDTO userDTO) throws SQLException;
}
