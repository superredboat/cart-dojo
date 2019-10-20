package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.Utils;
import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.*;
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
		Iterable<Movie> movies = movieRepository.saveAll(movieList);

		// Inverted Index
		Map<String, Set<Long>> map = new HashMap<>();
        for (Movie movie : movies) {
            String[] nameArr = movie.getName().split(" ");
            for (String name : nameArr) {
                if (Utils.isStringNotEmpty(name)) {
                    String word = name.trim().toLowerCase();
                    if (map.get(word) == null) {
                        map.put(word, new HashSet<>());
                    }
                    map.get(word).add(movie.getId());
                }
            }
        }
		Utils.map = map;
    }
}
