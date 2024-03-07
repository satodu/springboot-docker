package br.com.screenwatcher.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDetails(@JsonAlias("Title") String titulo,
                           @JsonAlias("totalSeasons") Integer totalSeason,
                           @JsonAlias("imdbRating") String rating) {
}
