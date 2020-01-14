/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Lukas Bjornvad
 */
public class ScoreR {
    //Rotten tomatoes
    private String title;
    private String source;
    public Viewer viewer;
    public Critic critic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Critic getCritic() {
        return critic;
    }

    public void setCritic(Critic critic) {
        this.critic = critic;
    }
  
public class Viewer{
private double rating;
private int numreviews;
private double meter;

    public double getRating() {
        return rating;
    }
    public int getNumreviews() {
        return numreviews;
    }
    public double getMeter() {
        return meter;
    }
}

public class Critic{
private double rating;
private int numreviews;
private double meter;

    public double getRating() {
        return rating;
    }
    public int getNumreviews() {
        return numreviews;
    }
    public double getMeter() {
        return meter;
    } 
}  
}
