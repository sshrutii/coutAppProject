package com.example.cout;

import android.provider.ContactsContract;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    private ImageView prof,score,website ;
    private TextView approveText;
    public ImageView approve;
    private Button code;
    Boolean admin;
    ProfileActivity profile = new ProfileActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i  = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        admin = i.getBooleanExtra("admin",false);
        System.out.println("admin in second activity:"+admin);



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
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, Topics.class);
                intent.putExtra("flag",3);
                startActivity(intent);
            }
        });


    }


    private void setupUIViews(){
        //System.out.println("hello");// setting the values from ui to these variables
        System.out.println("Admin is:"+profile.admin);
        approve = (ImageView) findViewById(R.id.btnApprove);
        approveText = (TextView) findViewById(R.id.textApprove);
        if(admin) {
            Toast.makeText(this,"You are swtiched into admin mode",Toast.LENGTH_SHORT);
            approve.setVisibility(View.VISIBLE);
            approveText.setVisibility(View.VISIBLE);
        }
        else{
            approve.setVisibility(View.GONE);
            approveText.setVisibility(View.GONE);
        }
        prof = (ImageView) findViewById(R.id.btnProf);
        score  = (ImageView) findViewById(R.id.btnLeaderboard);
        code = (Button)findViewById(R.id.btncode);
        website = (ImageView)findViewById(R.id.btnWeb);
    }
}