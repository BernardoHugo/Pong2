package com.bernardof.pong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity implements GameView.OnGameFinishListener {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        gameView = new GameView(this, size.x, size.y);
        gameView.setGameFinishListener(this);
        setContentView(gameView);
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    public void onGameFinish(String result) {
        Intent i = new Intent(this, ResultScreen.class);
        i.putExtra( "GAME_RESULT",result);
        // Start our ResultScreen class via the Intent
        startActivity(i);
        // Now shut this activity down
        finish();
    }
}
