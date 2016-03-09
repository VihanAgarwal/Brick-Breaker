package edu.macalester.comp124.breakout;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {

    private static final int PAUSE_TIME = 5;
    private static final double Y_OFFSET =75;
    private static final int SCREEN_WIDTH =750;
    private static final int SCREEN_HEIGHT=1000;
    private static final Color BG_COLOR = Color.lightGray;
    private BreakoutBall gameBall;
    private Paddle gamePaddle;
    private BrickWall gameBricks;
    private GRect gameBG;

    public void init(){
        gameBG = new GRect(SCREEN_WIDTH,SCREEN_HEIGHT);
        gameBG.setFilled(true);
        gameBG.setColor(BG_COLOR);
        gameBG.setFillColor(BG_COLOR);
        add(gameBG,0,0);
        gameBricks= new BrickWall();
        add(gameBricks,0,Y_OFFSET);
        gamePaddle=new Paddle();
        add(gamePaddle,getWidth()/2,gamePaddle.getyPos());
        gameBall=new BreakoutBall();
        add(gameBall,getWidth()/2,getHeight()/2);


        addMouseListeners();


    }




    public void run() {
        //these would all be in init, however the screen size doesnt set up in init due to a bug.
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);

        while(true){
            gameBall.moveBall();
            gameBall.checkWallCollision(getWidth(),getHeight());
            gameBall.checkPaddleColision(gamePaddle);
            pause(PAUSE_TIME);
        }


    }

    public void mouseMoved(MouseEvent e){
        double xPos = e.getX();
        if(xPos+ gamePaddle.getWidth()>getWidth()){
            xPos=gamePaddle.getX();
        }
        gamePaddle.movePaddle(xPos);
    }

    public double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public double getScreenHeight() {
        return SCREEN_HEIGHT;
    }
}
