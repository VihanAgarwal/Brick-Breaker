package edu.macalester.comp124.breakout;

import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Vihan on 3/6/2016.
 */
public class Brick {

    private static final double BRICK_HEIGHT=25;
    private static final double BRICK_WIDTH=72.5;

    private GRect brick;

    public Brick(){
        brick = new GRect(BRICK_WIDTH,BRICK_HEIGHT);
        brick.setColor(Color.black);
        brick.setFilled(true);
        brick.setFillColor(Color.blue);
    }

    public GRect getBrick(){
        return brick;
    }

    public double getBrickHeight(){
        return BRICK_HEIGHT;
    }

    public double getBrickWidth(){
        return BRICK_WIDTH;
    }

    public double getXLocation(){
        return brick.getX();
    }

    public double getYLocation(){
        return brick.getY();
    }
}
