package mvc.sevice;

import mvc.models.MovieDAO;
import mvc.models.MovieDAOImpl;
import mvc.models.MovieDTO;

public class MovieServiceImpl implements MovieService {
	private static final MovieService movieService = new MovieServiceImpl();
	private MovieDAO movieDAO = MovieDAOImpl.getInstance();
	
	private MovieServiceImpl() {
	}
	public static MovieService getInstance() {
		return movieService;
	}
	@Override
	public MovieDTO getMovie(String title) throws Exception {
		return movieDAO.getMovie(title);
	}
}
