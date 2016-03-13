package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Main GraphicsProgram for the breakout game described
 * in exercise 10.10 int the Roberts Textbook.
 */
public class BreakoutProgram extends GraphicsProgram {

    //Constants.
    private static final double PAUSE_TIME = 2.5;  //time the screen pauses
    private static final double POWER_UP_SPEED = .65; //speed of the power up
    private static final double Y_OFFSET = 75; //distance of the top of the window to the top of the bricks
    private static final int SCREEN_WIDTH = 750; //width of the game window
    private static final int SCREEN_HEIGHT = 1000; //height of the game window
    private static final int SCORE_PER_BRICK = 10; //score you get per brick
    private static final Color BG_COLOR = new Color(240, 255, 255); //color of the background
    private static final Color FIRE_COLOR = new Color(255, 69, 0); //color of the fireball
    private static final double POWER_UP_TIME = 2250; //time the power up lasts

    //Instance variables.
    private boolean powerUpApplied;  //flag to store if a power up has been applied
    private double powerUpTime;      //stores how long the power up can be applied
    private BreakoutBall gameBall;  // the ball in the game
    private Paddle gamePaddle;      //the paddle in the game
    private BrickWall gameBricks;   //the wall of bricks on the game
    private GRect gameBG;           //the background of the game
    private Player gamePlayer;      //the current player
    private GLabel playerStats;     //a label to display the number of lives
    private boolean containsPowerUp; //if the screen contains a power up
    private PowerUp powerUp;        //the power up on the screen

    /**
     * Method that initializes a new game. Initializes all the instance variables and adds the game Objects to the
     * screen.
     */
    public void init() {
        gamePlayer = new Player();

        gameBG = new GRect(SCREEN_WIDTH, SCREEN_HEIGHT);
        gameBG.setFilled(true);
        gameBG.setColor(BG_COLOR);
        gameBG.setFillColor(BG_COLOR);
        add(gameBG, 0, 0);

        gameBricks = new BrickWall();
        add(gameBricks, 0, Y_OFFSET);

        gamePaddle = new Paddle();
        add(gamePaddle, getWidth() / 2, gamePaddle.getyPos());

        gameBall = new BreakoutBall();
        add(gameBall, getWidth() / 2, getHeight() / 2);

        playerStats = new GLabel(gamePlayer.toString());
        playerStats.setFont(new Font("Helvetica", Font.BOLD, 18));
        add(playerStats, 20, 925);

        containsPowerUp = false;
        powerUpApplied = false;

        addMouseListeners();

    }


    public void run() {
        //these would all be in init, however the screen size doesnt set up in init due to a bug.
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        while (gamePlayer.getPlayerLives() >= 0) {

            gameBall.moveBall();

            gameBall.checkWallCollision(getWidth(), getHeight());
            gameBall.checkPaddleCollision(gamePaddle);
            gameBricks.checkBallCollision(gameBall);
            checkBrickCollision();
            checkMissedPaddle();
            playerStats.setLabel(gamePlayer.toString());
            handlePowerUp();
            pause(PAUSE_TIME);
            if (testForWin()) {
                break;
            }
        }
        checkForWin();
        pause(5000);
        System.exit(-1);

    }

    /**
     * Method that resets the ball and adds it back to the screen if it misses the paddle.
     */
    public void resetBall() {
        remove(gameBall);
        GLabel reset = new GLabel("!!");
        reset.setFont(new Font("Helvetica", Font.BOLD, 28));
        gamePlayer.reduceLives();
        add(reset, getWidth() / 2, getHeight() / 2);
        pause(2000);
        if (gamePlayer.getPlayerLives() >= 0) {
            for (int i = 3; i > 0; i--) {
                reset.setLabel(Integer.toString(i));
                pause(1000);
            }
        }
        remove(reset);

    }

    /**
     * Moves the power if it exists and checks if it hit the paddle. And if one has been applied, counts down Power Up time
     * until the time expires and restores the game to the normal mode.
     */
    public void handlePowerUp(){
        if (containsPowerUp) {
            movePowerUp();
            checkPowerUpCollision();
        }
        if(powerUpApplied){
            powerUpTime--;
            if (powerUpTime < 0) {
                restoreNormalMode();
            }
        }


    }


    /**
     * Method that controls the paddle and makes it follow the cursor.
     * @param e event that is called when the mouse is moved.
     */
    public void mouseMoved(MouseEvent e) {
        double xPos = e.getX();
        if (xPos + gamePaddle.getWidth() > getWidth()) {
            xPos = gamePaddle.getX();
        }
        gamePaddle.movePaddle(xPos);
    }

    /**
     * Checks to see if the player has won the game or not
     * @return true if there are no more bricks otherwise returns false.
     */
    public boolean testForWin() {
        if (gameBricks.getElementCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * If there is no power up on the screen then there is a random chance that a brick will generate a power up.
     */
    public void dropPowerUp() {

        if (!containsPowerUp) {

            powerUp = gameBricks.generatePowerUp();

            if (powerUp != null) {
                add(powerUp, gameBall.getX(), gameBall.getY());
                System.out.println("Should've printed the powerUp");
                containsPowerUp = true;
            }
        }
    }

    /**
     * Checks if the ball has collided with any of the bricks.
     */
    public void checkBrickCollision() {

        if (gameBricks.isHitBrickHorizontal()) {
            gamePlayer.increaseScore(SCORE_PER_BRICK);
            dropPowerUp();
            if (!gameBall.isOnFire()) {
                gameBall.flipX();
            }

        }

        if (gameBricks.isHitBrickVertical()) {
            gamePlayer.increaseScore(SCORE_PER_BRICK);
            dropPowerUp();
            if (!gameBall.isOnFire()) {
                gameBall.flipY();
            }
        }

    }

    /**
     * Checks to see if the ball missed the paddle
     */
    public void checkMissedPaddle() {
        if (gameBall.getY() > gamePaddle.getY() + gamePaddle.getHeight()) {
            resetBall();
            add(gameBall, getWidth() / 2, getHeight() / 2);
        }
    }

    /**
     * Checks to see if the paddle collided with the powerup.
     */
    public void checkPowerUpCollision() {
        if (powerUp.getY() + powerUp.getHeight() > gamePaddle.getY()) {
            if ((powerUp.getX() + powerUp.getWidth() > gamePaddle.getX())
                    && (powerUp.getX() < gamePaddle.getX() + gamePaddle.getWidth())) {
                applyPowerUp();
                powerUpApplied = true;
            }
            destroyPowerUp();
        }


    }

    /**
     * Applies the specific power Up based on which power up it is and if it collided with the paddle or not
     */
    public void applyPowerUp() {
        String powerUpType = powerUp.getTag();
        if (!powerUpApplied) {
            if (powerUpType.equalsIgnoreCase("Fireball")) {
                gameBall.setFillColor(FIRE_COLOR);
                gameBall.setColor(FIRE_COLOR);
                gameBall.setOnFire(true);
            } else if (powerUpType.equalsIgnoreCase("ShortenPaddle")) {
                gamePaddle.setSize(gamePaddle.getPaddleWidth() / 2, gamePaddle.getHeight());
            } else if (powerUpType.equalsIgnoreCase("LengthenPaddle")) {
                gamePaddle.setSize(gamePaddle.getPaddleWidth() * 2, gamePaddle.getHeight());
            }
            powerUpTime = POWER_UP_TIME;
        }

    }

    /**
     * Destroys the power up once it has hit the paddle.
     */
    public void destroyPowerUp() {
        remove(powerUp);
        powerUp = null;
        containsPowerUp = false;
    }

    /**
     * Restores the game to normal once POWER_UP_TIME is over
     */
    public void restoreNormalMode() {

        gameBall.setColor(gameBall.getBallColor());
        gameBall.setFillColor(gameBall.getBallColor());
        gameBall.setOnFire(false);
        gamePaddle.setSize(gamePaddle.getPaddleWidth(), gamePaddle.getHeight());
        powerUpApplied = false;

    }

    /**
     * End game method that prints out a label based on if the player has won or not.
     */
    public void checkForWin() {

        if (testForWin()) {
            removeAll();
            GLabel endLabel = new GLabel("You Win!");
            endLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            add(endLabel, getWidth() / 2 - endLabel.getWidth() / 2, getHeight() / 2);
        } else {
            removeAll();
            GLabel endLabel = new GLabel("You Lose!");
            endLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            add(endLabel, getWidth() / 2 - endLabel.getWidth() / 2, getHeight() / 2);
        }

    }

    /**
     * Moves the power up lower on the screen
     */
    public void movePowerUp() {
        powerUp.move(0, POWER_UP_SPEED);
    }

    /**
     *
     * @return Screen width of the game window
     */
    public double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     *
     * @return Screen height of the game window
     */
    public double getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     *
     * @return the distance between the top of the window and the bricks.
     */
    public static double getyOffset() {
        return Y_OFFSET;
    }
}
