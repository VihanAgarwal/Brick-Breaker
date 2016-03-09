package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/5/2016.
 */
public class BreakoutBall extends GOval{

    private static final double BALL_RADIUS = 10.0;
    private static final Color BALL_COLOR=Color.BLACK;;
    private double dX=1;
    private double dY=1;

    public BreakoutBall(){
        super(BALL_RADIUS,BALL_RADIUS);
        setColor(BALL_COLOR);
        setFilled(true);
        setFillColor(BALL_COLOR);
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

    public void checkPaddleCollision(GRect paddle){

        boolean isOverPaddle = false;
        double farX = getX()+getWidth();
        double farY = getY() + getWidth();

        if( (farX>=paddle.getX()) && (getX()<=paddle.getX()+paddle.getWidth())){
         isOverPaddle=true;
        }


        if((farY>=paddle.getY())&& isOverPaddle){
            flipY();
            return;
        }


    }



}
