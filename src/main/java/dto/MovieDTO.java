/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.LinkedList;

/**
 *
 * @author Lukas Bjornvad
 */
public class MovieDTO {

    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private ScoreI imdb;
    private ScoreR rotten;
    private ScoreM meta;

    public MovieDTO(Movie mov) {
        this.title = mov.getTitle();
        this.year = mov.getYear();
        this.plot = mov.getPlot();
        this.directors = mov.getPlot();
        this.genres = mov.getGenres();
        this.cast = mov.getCast();
        this.poster = mov.getPoster();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public ScoreR getRotten() {
        return rotten;
    }

    public void setRotten(ScoreR rotten) {
        this.rotten = rotten;
    }

    public ScoreI getImdb() {
        return imdb;
    }

    public void setImdb(ScoreI imdb) {
        this.imdb = imdb;
    }

    public ScoreM getMeta() {
        return meta;
    }

    public void setMeta(ScoreM meta) {
        this.meta = meta;
    }

}
