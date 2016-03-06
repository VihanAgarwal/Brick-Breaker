package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {

    private static final int PAUSE_TIME = 10;
    private int dX=1;
    private int dY=1;
    private BreakoutBall gameBall;
    private Paddle gamePaddle;

    public void init(){

        gameBall = new BreakoutBall();
        add(gameBall.getBall(),getWidth()/2,getHeight()/2);
        gamePaddle = new Paddle();


    }

    public void run() {
        setSize(750,1000);
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

}
