package com.wongnai.interview.movie.search;

import com.wongnai.interview.Utils;
import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
    @Autowired
    private MovieDataService movieDataService;

    @Override
    public List<Movie> search(String queryText) {
        //TODO: Step 2 => Implement this method by using data from MovieDataService
        // All test in SimpleMovieSearchServiceIntegrationTest must pass.
        // Please do not change @Component annotation on this class
        List<Movie> movieList = new ArrayList<>();
        MoviesResponse moviesResponse = movieDataService.fetchAll();
        if (Utils.isStringNotEmpty(queryText)) {
			String pattern = "(.*)\\b" + queryText.toLowerCase() + "\\b(.*)";
			moviesResponse.stream().filter(movieData -> Utils.getStringEmpty(movieData.getTitle()).toLowerCase().matches(pattern)
					&& !movieData.getTitle().toLowerCase().equals(queryText.toLowerCase()))
					.forEach(movieData -> movieList.add(new Movie(movieData.getTitle(), movieData.getCast())));
		}
        return movieList;
    }

}
