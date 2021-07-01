package mvc.sevice;


import java.sql.SQLException;

import mvc.models.UserDAO;
import mvc.models.UserDAOImpl;
import mvc.models.UserDTO;

public class UserServiceImpl implements UserService {
	private static final UserService userService = new UserServiceImpl();
	private UserDAO userDAO = UserDAOImpl.getInstance();
	
	private UserServiceImpl() {
	}
	public static UserService getInstance() {
		return userService;
	}
	@Override
	public void userLogin(UserDTO user) throws SQLException {
		if(userDAO.userLogin(user)==0) {
			throw new RuntimeException("로그인에 실패했습니다.");
		}
	}
	@Override
	public void userSignup(UserDTO userDTO) throws SQLException {
		userDAO.userSignup(userDTO);
		
	}
}
