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
public class ScoreM {
    private String title;
    private String source;
    private double metacritic;

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

    public double getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(double metacritic) {
        this.metacritic = metacritic;
    }
    
}
