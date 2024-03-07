package br.com.screenwatcher.service;

import br.com.screenwatcher.model.MovieDetails;
import br.com.screenwatcher.util.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MoviesService {
    private String apiKey = "5c2f71f1";
    private String domain = "https://www.omdbapi.com/";

    public Object getMovieByName(String movieName) throws JsonProcessingException {
        System.out.println("Pesquisado : " + this.domain + "?t=" + movieName.replace(" ", "+") + "&apikey=" + this.apiKey);
        var JSONString =  HttpHelper.getDataAPI(this.domain + "?t=" + this.formatMovieName(movieName) + "&apikey=" + this.apiKey);
        return HttpHelper.convertData(JSONString, MovieDetails.class);
    }

    public String getMovieBySeason(String movieName, String season) {
        return HttpHelper.getDataAPI(this.domain + "?t=" + this.formatMovieName(movieName) + "&Season=" + season + "&apikey=" + this.apiKey);
    }

    public String getMovieByEpisode(String movieName, String season, String episode) {
        return HttpHelper.getDataAPI(this.domain + "?t=" + this.formatMovieName(movieName) + "&season=" + season + "&episode=" + episode + "&apikey=" + this.apiKey);
    }

    private String formatMovieName(String movieName) {
        return movieName.replace(" ", "+");
    }

}
