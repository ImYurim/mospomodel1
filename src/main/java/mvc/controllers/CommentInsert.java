package mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.fx.AbstractController;
import mvc.fx.ModelAndView;
import mvc.models.CommentDTO;
import mvc.sevice.CommentService;
import mvc.sevice.CommentServiceImpl;

public class CommentInsert extends AbstractController{

	CommentService commentService = CommentServiceImpl.getInstance();
	
	@Override
	public ModelAndView handleRquestInternal(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("userid");
		System.out.println(userid);
		String content = request.getParameter("content");
		System.out.println(content);
		String category = request.getParameter("category");
		System.out.println(category);
		long movieid = (Long)session.getAttribute("movieid");
		System.out.println(movieid);

		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setCategory(category);
		commentDTO.setContent(content);
		commentDTO.setUser_id(userid);
		commentDTO.setMovie_id(movieid);
		
		ModelAndView mav = new ModelAndView();
		
		try {
			if(commentService.selectComment(commentDTO)==0) {
				commentService.insertComment(commentDTO);
				
				mav.setViewName("redirect:searchAction");
				
			}else {
				mav.setViewName("/WEB-INF/views/result.jsp");
				mav.addObject("msg","이미 글이 등록되어있습니다.");
				mav.addObject("url", "javascript:history.back();");
			}



		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("/WEB-INF/views/result.jsp");
			mav.addObject("msg","글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		
		
		return mav;
	}

}
