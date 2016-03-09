package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GRect;

/**
 * Created by Vihan on 3/8/2016.
 */
public class BrickWall extends GCompound{

    private static double BRICK_SPACING = 5.0;
    private static int NO_OF_BRICKS_IN_ROW= 10;


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


}
