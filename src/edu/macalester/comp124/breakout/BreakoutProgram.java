package edu.macalester.comp124.breakout;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {

    private static final double PAUSE_TIME = 2.5;
    private static final double Y_OFFSET =75;
    private static final int SCREEN_WIDTH =750;
    private static final int SCREEN_HEIGHT=1000;
    private static final int SCORE_PER_BRICK = 10;
    private static final Color BG_COLOR = new Color(240,255,255);
    private BreakoutBall gameBall;
    private Paddle gamePaddle;
    private BrickWall gameBricks;
    private GRect gameBG;
    private Player gamePlayer;
    private GLabel playerStats;


    public void init(){
        gamePlayer = new Player();
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

        playerStats = new GLabel(gamePlayer.toString());
        playerStats.setFont(new Font("Helvetica",Font.BOLD, 18));
        add(playerStats,20,925);

        addMouseListeners();


    }




    public void run() {
        //these would all be in init, however the screen size doesnt set up in init due to a bug.
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);

        while(gamePlayer.getPlayerLives()>=0){

            gameBall.moveBall();
            gameBall.checkWallCollision(getWidth(),getHeight());
            gameBall.checkPaddleCollision(gamePaddle);
            gameBricks.checkBallCollision(gameBall);

            if(gameBricks.isHitBrickHorizontal()){
                gamePlayer.increaseScore(SCORE_PER_BRICK);
                gameBall.flipX();
            }

            if(gameBricks.isHitBrickVertical()){
                gamePlayer.increaseScore(SCORE_PER_BRICK);
                gameBall.flipY();
            }

            if(gameBall.getY()>gamePaddle.getY()+gamePaddle.getHeight()){
                resetBall();
                add(gameBall,getWidth()/2,getHeight()/2);
            }


            playerStats.setLabel(gamePlayer.toString());
            pause(PAUSE_TIME);

            if(testForWin()){
                break;
            }
        }

        if(testForWin()){
            removeAll();
            GLabel endLabel = new GLabel("You Win!");
            endLabel.setFont(new Font("Helvetica",Font.BOLD,20));
            add(endLabel,getWidth()/2 - endLabel.getWidth()/2,getHeight()/2);
        }else{
            removeAll();
            GLabel endLabel = new GLabel("You Lose!");
            endLabel.setFont(new Font("Helvetica",Font.BOLD,20));
            add(endLabel,getWidth()/2 - endLabel.getWidth()/2,getHeight()/2);
        }

        pause(5000);
        System.exit(-1);

    }

    public void resetBall(){
        remove(gameBall);
        GLabel reset = new GLabel("!!");
        reset.setFont(new Font("Helvetica", Font.BOLD, 28));
        gamePlayer.reduceLives();
        add(reset,getWidth()/2,getHeight()/2);
        pause(2000);
        if(gamePlayer.getPlayerLives()>=0) {
            for (int i = 3; i > 0; i--) {
                reset.setLabel(Integer.toString(i));
                pause(1000);
            }
        }
        remove(reset);

    }
    public void mouseMoved(MouseEvent e){
        double xPos = e.getX();
        if(xPos+ gamePaddle.getWidth()>getWidth()){
            xPos=gamePaddle.getX();
        }
        gamePaddle.movePaddle(xPos);
    }

    public boolean testForWin() {
        if(gameBricks.getElementCount()==0){
            return true;
        }
        return false;
    }
    public double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public double getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static double getyOffset() {
        return Y_OFFSET;
    }
}
