package mvc.sevice;

import java.sql.SQLException;

import mvc.models.UserDTO;

public interface UserService   {
	void userLogin(UserDTO user) throws SQLException;
	void userSignup(UserDTO userDTO) throws SQLException;
}
