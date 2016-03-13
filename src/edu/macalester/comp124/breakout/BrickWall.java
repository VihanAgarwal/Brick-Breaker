package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

/**
 * Created by Vihan on 3/8/2016.
 */
public class BrickWall extends GCompound{

    private static double BRICK_SPACING = 5.0;
    private static int NO_OF_BRICKS_IN_ROW= 10;

    //Instance variables
    private boolean hitBrickHorizontal; //if the brick was hit along its height
    private boolean hitBrickVertical;   // if the brick was hit along its width
    private RandomGenerator rgen = new RandomGenerator();


    /**
     * Constructor that adds all the bricks to the screen.
     */
    public BrickWall(){
        double yPos=0;
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
        yPos += BRICK_SPACING+Brick.getBrickHeight();
        addSingleRow(yPos);
    }

    /**
     * Creates a single Brick object
     * @return a brick
     */
    public GRect createSingleBrick(){
        Brick tempBrick = new Brick();
        return tempBrick;
    }

    /**
     * Adds a single row of bricks and the specified yPos
     * @param yPos the top left y position of the row to be added
     */
    public void addSingleRow(double yPos){
        double xPos=BRICK_SPACING;
        for(int i=0;i<NO_OF_BRICKS_IN_ROW;i++){
            add(createSingleBrick(),xPos,yPos);
            xPos+=(Brick.getBrickWidth()+BRICK_SPACING);
        }
    }

    /**
     * Checks if a ball has collided with any of the bricks
     * @param ball gameBall
     */
    public void checkBallCollision(GOval ball){
            hitBrickHorizontal = checkSideCollision(ball);
            hitBrickVertical = checkVerticalCollision(ball);
    }

    /**
     * Checks to see if the gameball hit the side of a brick
     * @param ball gameBall
     * @return returns true if the ball collided with the side of the brick
     */
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

    /**
     * Checks to see if the gameball hit the top or bottom of a brick
     * @param ball gameBall
     * @return true if the  ball collided with the top or down of the brick
     */
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

    /**
     * Returns if the brick was hit on the sides of the brick
     * @return hitBrickHorizontal
     */
    public boolean isHitBrickHorizontal() {
        return hitBrickHorizontal;
    }

    /**
     * Returns if the brick was hit on the top or bottom
     * @return hitBrickVertical
     */
    public boolean isHitBrickVertical() {
        return hitBrickVertical;
    }

    /**
     * Randomly generates a new power up
     * @return the Power up that was generated
     */
    public PowerUp generatePowerUp(){

        int choice = rgen.nextInt(1,4);

        if(choice==1){
            PowerUp power = new PowerUp();
            return power;
        }else{
            return null;
        }
    }
}
