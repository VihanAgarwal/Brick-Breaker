package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.awt.*;


/**
 * Created by Vihan on 3/9/2016.
 */
public class PowerUp extends GCompound {

    //Named Constants
    private static final Color WRAPPER_COLOR = new Color(238,221,130);
    private static final Color BALL_COLOR = new Color(255,69,0);
    private static final Color PADDLE_COLOR = new Color(111,220,111);
    private static final  double BALL_DIAMETER=40;
    private static double INNER_BALL_DIAMETER = 15;
    private static double BIG_PADDLE_HEIGHT=10;
    private static double BIG_PADDLE_WIDTH=30;
    private static double SMALL_PADDLE_HEIGHT=5;
    private static double SMALL_PADDLE_WIDTH=15;

    //Instance Variables
    private RandomGenerator rgen = new RandomGenerator();
    private String tag;

    /**
     * Constructor to create a new powerUp
     */
    public PowerUp(){

        int powerUpType = rgen.nextInt(1,3);

        switch(powerUpType){

            case 1:
                add(createFireBall());
                this.tag = "FireBall";
                break;
            case 2:
                add(createShortenPaddle());
                this.tag = "ShortenPaddle" ;
                break;
            case 3:
                add(createLengthenPaddle());
                this.tag = "LengthenPaddle";
                break;
            default:
                System.out.println("PowerUp Error!");
        }
    }

    /**
     * Creates the fireball type of powerUp
     * @return the Fireball Powerup
     */
    public GCompound createFireBall(){
        GCompound power = new GCompound();
        power.add(createBall(BALL_DIAMETER, WRAPPER_COLOR));
        power.add(createBall(INNER_BALL_DIAMETER,BALL_COLOR),12.5,12.5);
        return power;
    }
    /**
     * Creates the shorten paddle type of powerUp
     * @return the ShortenPaddle Powerup
     */
    public GCompound createShortenPaddle(){
        GCompound power = new GCompound();
        power.add(createBall(BALL_DIAMETER,WRAPPER_COLOR));
        power.add(createRect(SMALL_PADDLE_WIDTH,SMALL_PADDLE_HEIGHT,PADDLE_COLOR),12.5,22.5);
        return power;

    }
    /**
     * Creates the lengthen paddle type of powerUp
     * @return the LengthenPaddle Powerup
     */
    public GCompound createLengthenPaddle(){
        GCompound power = new GCompound();
        power.add(createBall(BALL_DIAMETER,WRAPPER_COLOR));
        power.add(createRect(BIG_PADDLE_WIDTH,BIG_PADDLE_HEIGHT,PADDLE_COLOR),5,15);

        return power;
    }

    /**
     * Creates a circle
     * @param radius the radius of the circle
     * @param color the color of the circle
     * @return the created circle
     */
    public GOval createBall(double radius, Color color){
        GOval wrapper = new GOval(radius,radius);
        wrapper.setFilled(true);
        wrapper.setColor(color);
        wrapper.setFillColor(color);

        return wrapper;
    }

    /**
     * Creates a rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @return the created rectangle
     */
    public GRect createRect(double width, double height, Color color){
        GRect rect = new GRect(width,height);
        rect.setFilled(true);
        rect.setFillColor(color);
        rect.setColor(color);

        return rect;

    }

    /**
     * Getter for the tag, i.e, the type of powerup
     * @return tag
     */
    public String getTag() {
        return tag;
    }

}
