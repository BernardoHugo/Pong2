package com.bernardof.pong;

import android.content.Context;

/**
 * Created by BernardoF on 12/09/2017.
 */

public class Player extends DrawableObject {
    private final int speed = 20;

    private int targetX;
    private int lastX;

    private final int leftBound;
    private final int rightBound;

    public int getLastX() {
        return lastX;
    }

    public Player(Context context, int id, int x, int y, int screenSizeX, int screenSizeY) {
        super(context, id, x, y);
        targetX = this.x;
        leftBound = 0;
        rightBound = screenSizeX - getBitmap().getWidth();
    }

    @Override
    public void update() {

        lastX = x;

        int dist = targetX - x;
        int absDist = Math.abs(dist);
        if (absDist > 0) {
            if (absDist > speed) {
                x = dist > 0 ? x + speed : x - speed;
            } else {
                x = targetX;
            }
        }

        if (x > rightBound) {
            x = rightBound;
        }
        if (x < leftBound) {
            x = leftBound;
        }
        super.update();
    }

    public void setTargetX(int targetX) {
        if (targetX > rightBound) {
            targetX = rightBound;
        }
        if (targetX < leftBound) {
            targetX = leftBound;
        }
        this.targetX = targetX;
    }
}
