package mvc.fx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
	urlPatterns = {"/movie/*"},
	loadOnStartup = 10)
public class DispatcherServlet extends HttpServlet {
	
	private Map<String, AbstractController> controllerMap = new HashMap<>();
	@Override
	public void init() throws ServletException {
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(this.getClass().getResource("dispatcher-servlet.properties").getPath()));
			for(Object oKey : prop.keySet()) {
				String key = ((String)oKey).trim();
				Class<?> className = null;
				try {
					String property_value = prop.getProperty(key).trim();
					className = Class.forName(property_value);
					controllerMap.put(key, (AbstractController) className.getConstructor().newInstance());
					System.out.println("++++" + key + ":" + property_value);
				} catch(Exception e) {
					System.out.println("----" + className);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String action = requestURI.substring(contextPath.length());
		System.out.println(action);
		
		AbstractController controller = controllerMap.get(action);
		System.out.println(action + ":" + controllerMap.get(action));
		ModelAndView mav = controller.handleRquestInternal(request, response);
		
		if (mav != null) {
			
			String viewName = mav.getViewName();
			if (viewName.startsWith("redirect:searchAction")) {
				response.sendRedirect(viewName.substring(9));
			} else {
				Map<String, Object> model = mav.getModel();
				for(String key : model.keySet()) {
					request.setAttribute(key, model.get(key));
				}				
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("RequestDispatcher에서 길을 잃었다네~");
		}
	}
}
