package mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.fx.AbstractController;
import mvc.fx.ModelAndView;
import mvc.models.UserDTO;
import mvc.sevice.MovieService;
import mvc.sevice.MovieServiceImpl;
import mvc.sevice.UserService;
import mvc.sevice.UserServiceImpl;

public class MovieSignupAction extends AbstractController{
	UserService userService = UserServiceImpl.getInstance();
	
	@Override
	public ModelAndView handleRquestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String id = request.getParameter("userid");
		String password = request.getParameter("userpassword");
		String name = request.getParameter("username");
		
		UserDTO user = new UserDTO();
		
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		
		try {
			userService.userSignup(user);
			return new ModelAndView("/WEB-INF/views/signin.jsp");
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/WEB-INF/views/result.jsp");
			mav.addObject("msg","회원가입에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
	}
	

}
