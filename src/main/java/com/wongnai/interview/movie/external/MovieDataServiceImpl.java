package com.wongnai.interview.movie.external;

import com.google.gson.Gson;
import com.wongnai.interview.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

@Component
public class MovieDataServiceImpl implements MovieDataService {
    public static final String MOVIE_DATA_URL
            = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public MoviesResponse fetchAll() {
        //TODO:
        // Step 1 => Implement this method to download data from MOVIE_DATA_URL and fix any error you may found.
        // Please noted that you must only read data remotely and only from given source,
        // do not download and use local file or put the file anywhere else.
        MoviesResponse moviesResponse = new MoviesResponse();
        try {
            String jsonText = Utils.readJsonFromUrl(MOVIE_DATA_URL);
            moviesResponse = new Gson().fromJson(jsonText, MoviesResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesResponse;
    }

}
