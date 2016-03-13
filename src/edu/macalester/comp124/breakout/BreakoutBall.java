package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/5/2016.
 */
public class BreakoutBall extends GOval{

    private static final double BALL_RADIUS = 10.0;
    private static final Color BALL_COLOR=new Color(32,178,170);

    //Instance variables
    private double dX=1;
    private double dY=1;
    private boolean isOnFire; //if the ball is on fire

    /**
     * Constructor to initialize the ball to a new GOval.
     */
    public BreakoutBall(){
        super(BALL_RADIUS,BALL_RADIUS);
        setColor(BALL_COLOR);
        setFilled(true);
        setFillColor(BALL_COLOR);
        isOnFire=false;
    }


    /**
     * Moves the ball by dX and dY
     */
    public void moveBall(){
        move(this.dX,this.dY);
    }

    /**
     * Flips the x direction of the ball
     */
    public void flipX(){ dX = (-1 * dX); }

    /**
     * Flips the y direction of the ball
     */
    public void flipY(){ dY = (-1*dY); }

    /**
     * Checks if the ball hit any of the walls and rebounds it respectively.
     */
    public void checkWallCollision(double screenWidth, double screenHeight) {

        if (getX()+ BALL_RADIUS > screenWidth|| getX()< 0) {
            flipX();
        }else if (getY() + BALL_RADIUS > screenHeight || getY() < 0) {
            flipY();
        }
    }

    /**
     * Checks if the ball has collided with a specific paddle.
     * @param paddle gamePaddle.
     */
    public void checkPaddleCollision(GRect paddle){

        boolean isWithinPaddle = false;
        double farX = getX()+getWidth();
        double farY = getY() + getWidth();

        if( (farX>=paddle.getX()) && (getX()<=paddle.getX()+paddle.getWidth())){
         isWithinPaddle=true;
        }

        if((isWithinPaddle)&& (farY>paddle.getY()) && (farY< paddle.getY()+paddle.getHeight())){
            setLocation(getX(),paddle.getY()-getHeight());
            flipY();
            return;
        }

        if((farY>=paddle.getY())&& isWithinPaddle){
            flipY();
        }


    }

    /**
     *
     * @return If the ball is on fire or not.
     */
    public boolean isOnFire() {
        return isOnFire;
    }

    /**
     * Setter for on fire
     * @param onFire if the ball is on fire or not.
     */
    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
    }

    /**
     *
     * @return Returns the color of the ball so that the game can be restored.
     */
    public static Color getBallColor() {
        return BALL_COLOR;
    }

}
