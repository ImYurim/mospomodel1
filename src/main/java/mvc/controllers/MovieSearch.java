package mvc.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.fx.AbstractController;
import mvc.fx.ModelAndView;
import mvc.models.CommentDTO;
import mvc.models.MovieDTO;
import mvc.sevice.CommentService;
import mvc.sevice.CommentServiceImpl;
import mvc.sevice.MovieService;
import mvc.sevice.MovieServiceImpl;

public class MovieSearch extends AbstractController{
	MovieService movieService = MovieServiceImpl.getInstance();
	CommentService commentService = CommentServiceImpl.getInstance();
	
	@Override
	public ModelAndView handleRquestInternal(HttpServletRequest request, HttpServletResponse response) {
		String title = null;
		
		HttpSession session = request.getSession(true);
		if((String)session.getAttribute("title")==null) {
			 title = request.getParameter("moviename").toString();
			session.setAttribute("title", title);
		}else {
			title = (String) session.getAttribute("title");
		}
		
		try {
			MovieDTO movie = movieService.getMovie(title);
			session.setAttribute("title", movie.getTitle());
			session.setAttribute("movieid", movie.getId());
			System.out.println("movieservice끝");
			List<CommentDTO> comment = commentService.getCommentlist(movie.getId());
			System.out.println("commentService");
			ModelAndView mav = new ModelAndView("/WEB-INF/views/detail.jsp");
			mav.addObject("movie", movie);
			mav.addObject("comment", comment);
			
			return mav;
		}catch(Exception e){
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("/WEB-INF/views/main.jsp");
			return mav;
		}

	}

}
