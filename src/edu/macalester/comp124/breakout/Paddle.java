package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/6/2016.
 */
public class Paddle extends GRect {

    private static final double PADDLE_WIDTH = 120.0;
    private static final double PADDLE_HEIGHT = 15.0;
    private static final Color PADDLE_COLOR= new Color(111,220,111);
    //Instance variables
    private double xPos =0;
    private static final double Y_POS=875; //distance of the paddle from the top of the screen

    /**
     * Constructor that initializes a new game paddle.
     */
    public Paddle(){
        super(PADDLE_WIDTH,PADDLE_HEIGHT);
        setColor(PADDLE_COLOR);
        setFilled(true);
        setFillColor(PADDLE_COLOR);
    }

    /**
     * Moves the paddle to the specified position
     * @param xPos the x position where the paddle needs to be moved
     */
    public void movePaddle(double xPos){
        this.xPos=xPos;
        setLocation(this.xPos,Y_POS);
    }

    /**
     * Getter for the Y_POS of the paddle
     * @return
     */
    public static double getyPos() {
        return Y_POS;
    }

    /**
     * Getter for the PADDLE_WIDTH
     * @return the original width of the paddle.
     */
    public static double getPaddleWidth() {
        return PADDLE_WIDTH;
    }
}
