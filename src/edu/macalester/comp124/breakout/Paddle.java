package edu.macalester.comp124.breakout;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/6/2016.
 */
public class Paddle {

    private static final double PADDLE_WIDTH = 120.0;
    private static final double PADDLE_HEIGHT = 12.0;

    private double xPos;
    private double yPos;
    private GRect paddle;

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public GRect getPaddle() {
        return paddle;
    }

    public Paddle(){
        paddle = new GRect(0,0,PADDLE_WIDTH,PADDLE_HEIGHT);
        paddle.setColor(Color.gray);
        paddle.setFilled(true);
        paddle.setFillColor(Color.gray);
    }

    public boolean checkCollision(double yPos){
        if(yPos>paddle.getY()){
            return true;
        }
        return false;
    }

}
