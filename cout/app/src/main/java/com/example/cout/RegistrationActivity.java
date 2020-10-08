package com.example.cout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName,userPassword,userEmail; //current user varaibles for sign-up
    private Button regButton;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;   //create an instance of  class FirebaseAuth to import all its libraries and features

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();                                  //calling setupUIViews() on create

        firebaseAuth = FirebaseAuth.getInstance();     // get an instance of authenticator in this variable firebaseAuth

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())        // if nothing blank
                {
                    String user_email = userEmail.getText().toString();          // current mail,pw
                    String user_password = userPassword.getText().toString();
                    //authenticating it
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())  //if authentication successful,added to the database and redirected to login page
                            {
                                sendEmailVerification();
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this,"Registration Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);   //on clicking on already a user, redirected to login page
                startActivity(intent);
            }
        });

    }

    private void setupUIViews(){                                // setting the values from ui to these variables
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    private boolean validate()   //validate fn definition //just checking for not empty
    {
        boolean result=false;

        String n = userName.getText().toString();       //converting username value to string and storing in local var n
        String e = userEmail.getText().toString();
        String p = userPassword.getText().toString();

        if(n.isEmpty() || e.isEmpty() || p.isEmpty() )   //if any of the fields are empty,flash a msg
        {
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }

    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();  //current user
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this,"Registration Successful , A verification mail has been sent",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut(); //was signed in with create user,so needs to be signed out
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this,"Verification mail could not be sent",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}