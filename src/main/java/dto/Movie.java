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
public class Movie {
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
   private String poster;

    public Movie() {
    }

    public Movie(MovieDTO dto) {
        this.title = dto.getTitle();
        this.plot = dto.getPlot();
        this.directors = dto.getDirectors();
        this.genres = dto.getGenres();
        this.cast = dto.getCast();
        this.poster = dto.getPoster();
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
    

    
    
}
