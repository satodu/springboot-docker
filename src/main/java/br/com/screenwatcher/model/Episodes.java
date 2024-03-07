package br.com.screenwatcher.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodes {
    private String numberSession;
    private Integer numberEpisode;
    private String title;
    private Double rating;
    private LocalDate released;

    public Episodes(String numberSession, MovieEpisodiesDetails movieEpisodiesDetails) {
        this.numberSession = numberSession;
        this.numberEpisode = movieEpisodiesDetails.number();
        this.title = movieEpisodiesDetails.title();

        try {
            this.rating = Double.valueOf(movieEpisodiesDetails.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

        try {
            this.released = LocalDate.parse(movieEpisodiesDetails.released());
        } catch (DateTimeParseException ex) {
            this.released = null;
        }

    }

    public String getNumberSession() {
        return numberSession;
    }

    public void setNumberSession(String numberSession) {
        this.numberSession = numberSession;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return  "numberSession='" + numberSession + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", released=" + released;
    }
}


