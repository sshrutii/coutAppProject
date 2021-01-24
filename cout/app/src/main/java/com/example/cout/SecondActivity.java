package com.example.cout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private ImageView prof,score,website,quiz ;
    private Button code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupUIViews();
//        Toast.makeText(SecondActivity.this,"lalazzzzzzzzzz",Toast.LENGTH_SHORT).show();

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Topics.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, activity_leaderboard.class));
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, WebsiteActivity.class));
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Topics.class);
                intent.putExtra("flag",2);
                startActivity(intent);
            }
        });


    }

    private void setupUIViews(){// setting the values from ui to these variables

//        Toast.makeText(SecondActivity.this,"lalaldfhgfg",Toast.LENGTH_SHORT).show();

        prof = (ImageView) findViewById(R.id.btnProf);
        score  = (ImageView) findViewById(R.id.btnLeaderboard);
        code = (Button)findViewById(R.id.btncode);
        website = (ImageView)findViewById(R.id.btnWeb);
        quiz = (ImageView)findViewById(R.id.btnquiz);

    }
}