package br.com.screenwatcher.controller;

import br.com.screenwatcher.model.MovieDetails;
import br.com.screenwatcher.model.MovieSessionsDetails;
import br.com.screenwatcher.service.MoviesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.screenwatcher.util.HttpHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class BasicController {
    @GetMapping("/data")
    public ResponseEntity<List<MovieSessionsDetails>> getData() throws JsonProcessingException {
        var seriesName = "the boys";
        var ano = "2022";


        MoviesService moviesService = new MoviesService();
        MovieDetails movieDetails = (MovieDetails) moviesService.getMovieByName(seriesName);

        List<MovieSessionsDetails> movieSessionsDetailsList = IntStream.rangeClosed(1, movieDetails.totalSeason())
                .mapToObj(i -> {
                    try {
                        String movieSeasonDetailsJsonString = moviesService.getMovieBySeason(movieDetails.titulo(), String.valueOf(i));
                        return HttpHelper.convertData(movieSeasonDetailsJsonString, MovieSessionsDetails.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e); // Ou qualquer outra forma de manipulação de erro que você considere adequada
                    }
                })
                .collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(movieSessionsDetailsList, headers, HttpStatus.OK);
    }
}