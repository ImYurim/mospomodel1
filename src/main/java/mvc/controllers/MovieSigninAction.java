package mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.fx.AbstractController;
import mvc.fx.ModelAndView;
import mvc.models.MovieDTO;
import mvc.models.UserDTO;
import mvc.sevice.MovieService;
import mvc.sevice.MovieServiceImpl;
import mvc.sevice.UserService;
import mvc.sevice.UserServiceImpl;


public class MovieSigninAction extends AbstractController{	
	UserService userService = UserServiceImpl.getInstance();
	
	@Override
	public ModelAndView handleRquestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String id = request.getParameter("userid");
		String password = request.getParameter("userpassword");
		
		UserDTO user = new UserDTO();
		
		user.setId(id);
		user.setPassword(password);
		
		try {
			userService.userLogin(user);
			session.setAttribute("userid", user.getId());
			System.out.println("session:"+session.getAttribute("userid"));
			return new ModelAndView("/WEB-INF/views/main.jsp");
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("/WEB-INF/views/signin.jsp");
			return mav;
		}
	}

}
