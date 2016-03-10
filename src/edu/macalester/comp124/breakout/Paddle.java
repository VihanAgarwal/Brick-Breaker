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
    private static final Color PADDLE_COLOR= Color.darkGray;
    private double xPos =0;
    private static final double Y_POS=875;

    public Paddle(){
        super(PADDLE_WIDTH,PADDLE_HEIGHT);
        setColor(PADDLE_COLOR);
        setFilled(true);
        setFillColor(PADDLE_COLOR);
    }

    public void movePaddle(double xPos){
        this.xPos=xPos;
        setLocation(this.xPos,Y_POS);
    }

    public static double getyPos() {
        return Y_POS;
    }
}
