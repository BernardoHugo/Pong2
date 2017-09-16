package com.bernardof.pong;

import android.content.Context;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by BernardoF on 12/09/2017.
 */

public class Ball extends DrawableObject {

    private int lastX;
    private int lastY;

    private int lastLeft;
    private int lastTop;
    private int lastRight;
    private int lastBottom;

    private int speedX;
    private int speedY;
    private int movementSpeedX;
    private int movementSpeedY;
    private int bonusSpeedX;

    private Point movementDirection = new Point(1, -1);

    private float acceleration = 1.0f;
    private long accelerationTime;

    private int screenSizeX;
    private int screenSizeY;

    private final int leftBound;
    private final int rightBound;
    private final int topBound;
    private final int bottomBound;

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getLastLeft() {
        return lastLeft;
    }

    public int getLastTop() {
        return lastTop;
    }

    public int getLastRight() {
        return lastRight;
    }

    public int getLastBottom() {
        return lastBottom;
    }

    public void addToBonusSpeedX(int valueToAdd) {
        speedX += valueToAdd;
    }

    public int getMovementSpeedX() {
        return movementSpeedX;
    }

    public int getMovementSpeedY() {
        return movementSpeedY;
    }

    public Ball(Context context, int id, int x, int y, int screenSizeX, int screenSizeY) {
        super(context, id, x, y);

        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        leftBound = 0;
        rightBound = screenSizeX - getBitmap().getWidth();
        topBound = 0;
        bottomBound = screenSizeY - getBitmap().getHeight();

        //Set Ball Start Speed
        Random generator = new Random();
        speedX = 10 + generator.nextInt(5);
        speedY = 15;

        movementSpeedX = speedX;
        movementSpeedY = speedY;
    }

    @Override
    public void update() {

        //Check Acceleration
        accelerationTime += GameView.timeThisFrame;
        if (accelerationTime > 1000) {
            acceleration += 0.05f;
            accelerationTime = 0;
        }

        //Save last hitbox position
        lastLeft = getHitbox().left;
        lastTop = getHitbox().top;
        lastRight = getHitbox().right;
        lastBottom = getHitbox().bottom;

        //Update movementSpeed
        movementSpeedX = (int) Math.floor((speedX * acceleration  + bonusSpeedX ) * movementDirection.x);
        movementSpeedY = (int) Math.floor(speedY * acceleration * movementDirection.y);

        //Save last position
        lastX = x;
        lastY = y;

        x = x + movementSpeedX;
        y = y + movementSpeedY;

        super.update();
    }

    public void inverseSpeedX() {
        movementDirection.x *= -1;
    }

    public void inverseSpeedY() {
        movementDirection.y *= -1;
    }

    public void resetSpeed() {
        movementDirection.x = 1;
        movementDirection.y = -1;
        acceleration = 1;
        accelerationTime = 0;
        Random generator = new Random();
        speedX = 10 + generator.nextInt(5);
    }
}
