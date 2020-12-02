package com.example.cout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private ImageView prof,score ;
    private Button code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupUIViews();

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, QuestionsActivity.class));
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, activity_leaderboard.class));
            }
        });


    }

    private void setupUIViews(){                                // setting the values from ui to these variables

        prof = (ImageView) findViewById(R.id.btnprof);
        score  = (ImageView) findViewById(R.id.btnLeaderboard);
        code = (Button)findViewById(R.id.btncode);
    }
}