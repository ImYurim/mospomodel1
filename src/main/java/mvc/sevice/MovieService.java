package mvc.sevice;

import mvc.models.MovieDTO;

public interface MovieService {
	MovieDTO getMovie(String title) throws Exception;
}
