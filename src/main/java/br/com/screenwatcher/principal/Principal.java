package br.com.screenwatcher.principal;

import br.com.screenwatcher.model.Episodes;
import br.com.screenwatcher.model.MovieDetails;
import br.com.screenwatcher.model.MovieSessionsDetails;
import br.com.screenwatcher.service.MoviesService;
import br.com.screenwatcher.util.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Principal {

    public void run() throws JsonProcessingException {
        var seriesName = "the boys";
        var ano = "2022";
        LocalDate searchDate = LocalDate.of(Integer.parseInt(ano), 1, 1);

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


        List<Episodes> episodes = movieSessionsDetailsList.stream()
                .flatMap(t -> t.Episodes().stream().map(d -> new Episodes(t.Season(), d))
                ).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // SearchByDate
        episodes.stream().filter(e -> e.getReleased() != null && e.getReleased().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Seasson : " + e.getNumberSession() +
                                " Episode : " + e.getTitle() +
                                " Released : " + e.getReleased().format(formatter)
                ));

        // episodes.forEach(System.out::println);

//		System.out.println(movieSessionsDetailsList.size());

//		for(int i = 0; i < movieSessionsDetails.totalSeasons(); i++) {
//			List<MovieEpisodiesDetails> movieSessionsDetailsArray = movieSessionsDetailsList.get(i).Episodes();
//			for(int j = 0; j < movieSessionsDetailsArray.size(); j++){
//				var temporadaNumber = i + 1;
//				 System.out.println("Temporada - " + temporadaNumber + " Episode : " + movieSessionsDetailsArray.get(j).title());
//			}
//		}

//		movieSessionsDetailsList.forEach(t -> t.Episodes().forEach(e -> System.out.println(e.title())));

//		List<MovieEpisodiesDetails> movieEpisodiesDetailsList = movieSessionsDetailsList.stream()
//				.flatMap(t -> t.Episodes().stream())
//				.collect(Collectors.toList());
//
//		System.out.println("\n Top 5 Episódios");
//		movieEpisodiesDetailsList.stream()
//				.filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//				.sorted(Comparator.comparing(MovieEpisodiesDetails::rating).reversed())
//				.limit(5)
//				.forEach(System.out::println);
    }
}
