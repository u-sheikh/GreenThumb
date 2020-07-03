package com.example.greenthumb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/***
 * This class represents the First activity/page a user sees when they open application without having logged in previously.
 */

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login_button;
    TextView signUp_textView, forgot_password_TextView;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener fireBase_Listener;

    /***
     *
     * This method shows the screen that opens when user opens the app.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "MainActivity";
        //how to call database
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference("message"); */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login_button = findViewById(R.id.login_button);
        signUp_textView = findViewById(R.id.signUp_textView);
        forgot_password_TextView = findViewById(R.id.forgot_password_TextView);

        fireBase_Listener = new FirebaseAuth.AuthStateListener() {

            //this method checks for if the user is already signed in into the app.

            /***
             * This method checks if the user was already logged in when they closed app previously.
             * @param firebaseAuth : Firebase object communicates with firebase backend to verify if the user already had logged in.
             *
             */
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser list_of_User = mAuth.getCurrentUser();
                if (list_of_User != null) {
                    Toast.makeText(MainActivity.this, "successful login", Toast.LENGTH_SHORT).show();
                    Intent homePage = new Intent(MainActivity.this, HomePage.class);
                    CurrentUser.email = list_of_User.getEmail();
                    startActivity(homePage);
                } else {
                    Toast.makeText(MainActivity.this, "Please login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        // this on-click listener checks for user inputs in both email field and password.
        login_button.setOnClickListener(new View.OnClickListener() {
            /***
             *This onclick method takes in the user input data from email and password fields. if the fields were not empty the data of those fields would be checked against the firebase Authentication system
             * that checks the user credentials. once credentials are found and verified the user is given access to home screen
             * @param v
             *
             */
            @Override
            public void onClick(View v) {
                final String user_Email = email.getText().toString();
                String user_Password = password.getText().toString();
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both user email and password ", Toast.LENGTH_SHORT).show();
                    email.requestFocusFromTouch();
                    password.requestFocusFromTouch();
                } else if ((!email.getText().toString().isEmpty()) && (!password.getText().toString().isEmpty())) {
                    mAuth.signInWithEmailAndPassword(user_Email, user_Password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                //this method will authenticate with the firebase server on user credentials.

                                /***
                                 * This method displays the result of firebase communication where credentials were checked. if the credentials were correct the user would be allowed access.
                                 * @param task
                                 */
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        Toast.makeText(MainActivity.this, "successful login",
                                                Toast.LENGTH_SHORT).show();
                                        Intent homePage = new Intent(MainActivity.this, HomePage.class);
                                        CurrentUser.email = user_Email;
                                        startActivity(homePage);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(MainActivity.this, Objects.requireNonNull(task.getException()).getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        // this is onclick listener for taking user to signUp page
        signUp_textView.setOnClickListener(new View.OnClickListener() {
            /***
             * This method takes user to sign up page were they can create an account.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);

            }
        });
        // this is onclick listener for taking user to forgot password page.
        forgot_password_TextView.setOnClickListener(new View.OnClickListener() {
            /***
             * This method takes user to forgot password page of the app where they can reset their password
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(forgotPassword);

            }
        });


    }


}
