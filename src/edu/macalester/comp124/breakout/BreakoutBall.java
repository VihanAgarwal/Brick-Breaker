package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/5/2016.
 */
public class BreakoutBall {

    private static final double BALL_RADIUS = 15.0;


    private double xPos;
    private double yPos;
    private GOval ball;
    private boolean hitX;
    private boolean hitY;
    private boolean isOverPaddle;

    public BreakoutBall(){
        hitX=false;
        hitY=false;
        isOverPaddle = false;
        ball = new GOval(0,0,BALL_RADIUS,BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.black);


    }

    /**
     * Getter for the ball
     * @return the ball to the breakout program
     */
    public GOval getBall() {
        return ball;
    }

    /**
     * @return the x location of the ball
     */
    public double getXLocation(){
        return ball.getX();
    }
    /**
     * @return the y location of the ball
     */
    public double getYLocation(){
        return ball.getY();
    }

    /**
     * @return the width of the ball
     */
    public double getBallWidth(){
        return ball.getWidth();
    }

    /**
     * @return the height of the ball
     */
    public double getBallHeight(){
        return ball.getHeight();
    }

    /**
     * @return if the ball hit the side walls or not
     */
    public boolean getHitX(){
        return hitX;
    }

    /**
     * @return if the ball hit the top or bottom walls
     */
    public boolean getHitY(){
        return hitY;
    }

    /**
     * Moves the ball by dX and dY;
     * @param dx change in x passed by caller
     * @param dy change in y passed by caller.
     */
    public void moveBall(double dx,double dy){
        ball.move(dx,dy);
    }
    /**
     * Setter for the x value of the ball(considered as to the right of its hitbox ball).
     * @param xPos
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * Seter for the y value of the bottom of the ball.
     * @param yPos
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getxPos() {
        return xPos;
    }

    /**
     * Checks if the ball hit any of the sides of the screen.
     * @param screeWidth width of the game window
     * @param screenHeight height of the game window
     * @return true or false based on if it collided or not.
     */
    public boolean checkCollision(double screeWidth,double screenHeight) {
        hitX=false;
        hitY=false;
        if (xPos > screeWidth|| xPos - ball.getWidth() < 0) {
            hitX=true;
            return true;
        }else if (yPos > screenHeight || yPos - ball.getHeight() < 0) {
            hitY=true;
            return true;
        }else {
            return false;
        }
    }

    public boolean checkOverPaddle(GRect paddle){
        if((paddle.getX()<=getxPos())&&(getxPos()<=paddle.getX()+paddle.getWidth())) {
            return true;
        }
        return false;
    }

}
