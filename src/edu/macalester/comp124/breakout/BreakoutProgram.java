package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {

    private static final int PAUSE_TIME = 10;
    private int dX=2;
    private int dY=2;
    private BreakoutBall gameBall;
    private Paddle gamePaddle;

    public void init(){

        gameBall = new BreakoutBall();
        add(gameBall.getBall(),getWidth()/2,getHeight()/2);
        gamePaddle = new Paddle();
        addMouseListeners();


    }

    public void run() {
        //these would all be in init, however the screen size doesnt set up in init due to a bug.
        setSize(750,1000);
        gamePaddle.setyPos(getHeight()-75);
        add(gamePaddle.getPaddle(),getWidth()/2 ,getHeight()-75);

        while(true){

            gameBall.setxPos(gameBall.getXLocation() + gameBall.getBallWidth());
            gameBall.setyPos(gameBall.getYLocation() + gameBall.getBallHeight());

            if(gameBall.checkCollision(this.getWidth(),this.getHeight())) {
                if (gameBall.getHitX()) {
                    dX = -1 * dX;
                }
                if (gameBall.getHitY()) {
                    dY = -1 * dY;
                }
            }

            if(gamePaddle.checkCollision(gameBall.getyPos())&& gameBall.checkOverPaddle(gamePaddle.getPaddle())){
                dX = -1 *dX;
                dY= -1 *dY;
            }

            gameBall.moveBall(dX,dY);

            pause(PAUSE_TIME);

        }

    }

    public void mouseMoved(MouseEvent e){
        double xPos = e.getX();
        if(xPos+ gamePaddle.getPaddleWidth()>getWidth()){
            xPos=gamePaddle.getxPos();
        }
        gamePaddle.movePaddle(xPos);
    }

}
