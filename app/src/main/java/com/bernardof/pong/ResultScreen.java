package com.bernardof.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        final TextView gameResult =
                (TextView) findViewById(R.id.game_result);

        String result = getIntent().getStringExtra("GAME_RESULT");
        if (result.equals("WIN")) {
            gameResult.setText("You Win");
        } else {
            gameResult.setText("You Lose");
        }

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.game_result_layout);
        rlayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        // Start our MainActivity class via the Intent
        startActivity(i);
        // Now shut this activity down
        finish();
    }
}
