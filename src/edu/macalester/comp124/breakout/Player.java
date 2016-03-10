package edu.macalester.comp124.breakout;

/**
 * Created by Vihan on 3/8/2016.
 */
public class Player {
    private int playerLives;
    private int playerScore;


    public Player(){
        playerLives=3;
        playerScore=0;

    }

    public void increaseScore(int score){
        playerScore+=score;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public void reduceLives(){
        playerLives-=1;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    @Override
    public String toString(){
        return "Score: " + playerScore + "  Lives: " +playerLives;
    }

}
