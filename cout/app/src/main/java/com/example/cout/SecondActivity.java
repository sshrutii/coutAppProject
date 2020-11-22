package com.example.cout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {
    private ImageButton prof,questions ;

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
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, QuestionsActivity.class));
            }
        });

    }

    private void setupUIViews(){                                // setting the values from ui to these variables

        prof = (ImageButton)findViewById(R.id.btnprof);
        questions = (ImageButton) findViewById(R.id.btncode);
    }
}