package edu.macalester.comp124.breakout;

/**
 * Created by Vihan on 3/8/2016.
 */
public class Player {

    //Instance variables
    private int playerLives;
    private int playerScore;

    /**
     * Constructor to initialize the player lives and player score.
     */
    public Player(){
        playerLives=3;
        playerScore=0;

    }

    /**
     * Increases the player score by something
     * @param score the points by which the score needs to be increased by
     */
    public void increaseScore(int score){
        playerScore+=score;
    }

    /**
     *
     * @return the player's lives that he has left
     */
    public int getPlayerLives() {
        return playerLives;
    }

    /**
     * reduces the player lives by 1
     */
    public void reduceLives(){
        playerLives-=1;
    }

    /**
     * @return the players score at a particular point in the game
     *
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Overides the toString method.
     * @return the score and the lives the player has left
     */
    @Override
    public String toString(){
        return "Score: " + playerScore + "  Lives: " +playerLives;
    }

}
