package com.example.cout;

import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;
import org.w3c.dom.Text;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private Button Logout;
    private FirebaseAuth firebaseAuth;
    private TextView nameLabel;
    private TextView scoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameLabel = (TextView)findViewById(R.id.name);
        scoreLabel = (TextView)findViewById(R.id.score);

        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Logoutfn(); //calling logout fn
            }
        });

    }

    private void setupUIViews(){                                // setting the values from ui to these variables

        Logout = (Button)findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        System.out.println("email:  "+currentUser.getEmail()+" UID: "+currentUser.getUid());
        final FirebaseFirestore db= FirebaseFirestore.getInstance();
        nameLabel.setText("Fetching data");
        db.collection("UsersInfo").document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nameLabel.setText("Name: "+document.get("name"));
                        scoreLabel.setText("Score: "+document.get("Score"));
                    }
                }
            }
        });



    }

    private void Logoutfn(){   //defining logout fn
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);               // getting menu from resources
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuLogout:                          // menuLogout is the id from menu for logout
                Logoutfn();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}