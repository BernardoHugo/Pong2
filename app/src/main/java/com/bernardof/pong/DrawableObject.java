package com.bernardof.pong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by BernardoF on 11/09/2017.
 */

public class DrawableObject {

    private Bitmap bitmap;
    protected int x, y;

    private Rect hitBox;

    //Properties
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setX(int newX) {x = newX;}
    public int getX() {
        return x;
    }

    public void setY(int newY) {y = newY;}
    public int getY() { return y; }

    public Rect getHitbox(){
        return hitBox;
    }

    //Methods
    public DrawableObject(Context context ,int id , int x , int y) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        this.x = x - bitmap.getWidth()/2;
        this.y = y - bitmap.getHeight()/2;

        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update() {
        // Refresh hit box location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }
}
