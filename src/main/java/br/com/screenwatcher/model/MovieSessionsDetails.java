package br.com.screenwatcher.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieSessionsDetails(@JsonAlias("Season") String Season,
                                    @JsonAlias("totalSeasons") Integer totalSeasons,
                                    @JsonAlias("Episodes") List<MovieEpisodiesDetails> Episodes) {
}
