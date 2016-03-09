package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

/**
 * Created by Vihan on 3/8/2016.
 */
public class BrickWall extends GCompound{

    private static double BRICK_SPACING = 5.0;
    private static int NO_OF_BRICKS_IN_ROW= 10;
    private boolean hitBrickHorizontal;
    private boolean hitBrickVertical;


    public BrickWall(){
        double yPos=0;
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
    }

    public GRect createSingleBrick(){
        Brick tempBrick = new Brick();
        return tempBrick;
    }

    public void addSingleRow(double yPos){
        double xPos=BRICK_SPACING;
        for(int i=0;i<NO_OF_BRICKS_IN_ROW;i++){
            add(createSingleBrick(),xPos,yPos);
            xPos+=(Brick.getBrickWidth()+BRICK_SPACING);
        }
    }

    public void checkBallCollision(GOval ball){
            hitBrickHorizontal = checkSideCollision(ball);
            hitBrickVertical = checkVerticalCollision(ball);
    }

    public boolean checkSideCollision(GOval ball){
        double midY =ball.getY()+(ball.getHeight()/2) ;
        GObject brickLeft = getElementAt(ball.getX(),midY - BreakoutProgram.getyOffset());
        GObject brickRight = getElementAt(ball.getX()+ball.getWidth(),midY- BreakoutProgram.getyOffset());

        if(brickLeft!=null){
            this.remove(brickLeft);
            return true;
        }

        if(brickRight!=null){
            this.remove(brickRight);
            return true;
        }
            return false;
    }
    public boolean checkVerticalCollision(GOval ball){

        double midX = ball.getX()+(ball.getWidth()/2);
        GObject brick = getElementAt(midX,ball.getY()-BreakoutProgram.getyOffset());

        if(brick==null){
            brick = getElementAt(midX,ball.getY()+ball.getHeight()-BreakoutProgram.getyOffset());
        } else {
            this.remove(brick);
            return true;
        }

        if(brick!=null){
            this.remove(brick);
            return true;
        }

        return false;
    }

    public boolean isHitBrickHorizontal() {
        return hitBrickHorizontal;
    }

    public boolean isHitBrickVertical() {
        return hitBrickVertical;
    }
}
