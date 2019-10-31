package com.example.nategaulinsstreakersimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class endgame_activity extends AppCompatActivity {

    public static final String TIME = "0:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame_activity);

        Intent intent = getIntent();
        String scoreString = intent.getStringExtra(TIME);

        TextView score = (TextView) findViewById(R.id.score_view);

        score.setText("Time: " + scoreString);

    }

    public void returnToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
