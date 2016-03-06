package edu.macalester.comp124.breakout;

import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 *
 */
public class BreakoutProgram extends GraphicsProgram {

    private static final int PAUSE_TIME = 5;
    private static final int SCREEN_WIDTH =750;
    private static final int SCREEN_HEIGHT=1000;
    private static final int NO_OF_BRICKS=50;
    private double BrickX=0;
    private double BrickY=0;
    private int dX=1;
    private int dY=1;
    private BreakoutBall gameBall;
    private Paddle gamePaddle;
    private Brick gameBricks[];

    public void init(){

        gameBall = new BreakoutBall();
        add(gameBall.getBall(),SCREEN_WIDTH/2,SCREEN_HEIGHT/2);

        gamePaddle = new Paddle();
        gamePaddle.setyPos(SCREEN_HEIGHT-125);
        add(gamePaddle.getPaddle(),SCREEN_WIDTH/2 ,SCREEN_HEIGHT-125);

        gameBricks = new Brick[NO_OF_BRICKS];

        for(int i=0;i<gameBricks.length;i++){
            gameBricks[i] = new Brick();
            if(BrickX>getWidth()-gameBricks[i].getBrickWidth()){
                BrickY+=gameBricks[i].getBrickHeight();
                BrickX=0;
            }
            add(gameBricks[i].getBrick(),BrickX,BrickY);

            BrickX+=gameBricks[i].getBrickWidth();
        }

        addMouseListeners();


    }

    public void run() {
        //these would all be in init, however the screen size doesnt set up in init due to a bug.
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);


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
