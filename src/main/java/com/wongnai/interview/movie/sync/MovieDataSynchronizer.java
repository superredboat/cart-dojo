package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.Utils;
import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync() {
		//TODO: implement this to sync movie into repository
		MoviesResponse moviesResponse = movieDataService.fetchAll();

		List<Movie> movieList = new ArrayList<>();
		moviesResponse.stream().forEach(movieData -> movieList.add(new Movie(movieData.getTitle(), movieData.getCast())));
		movieRepository.saveAll(movieList);
	}
}
