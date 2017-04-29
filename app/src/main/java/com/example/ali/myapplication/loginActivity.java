package com.example.ali.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;


     Dialog dialogForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(loginActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");


        // get the References of views
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);


        Button btnForgetpassword = (Button) dialog.findViewById(R.id.buttonforgetpassword);


        btnForgetpassword.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                final Dialog dialogForget = new Dialog(loginActivity.this);
                dialogForget.setContentView(R.layout.forget);
                dialogForget.setTitle("Forget");





                Button btnForgetPasswordforreset = (Button) dialogForget.findViewById(R.id.buttonforgetpasswordforreset);

                final EditText editTextUserNameforget = (EditText) dialogForget.findViewById(R.id.editTextUserNameToLoginForget);
                btnForgetPasswordforreset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userName = editTextUserNameforget.getText().toString();
                        String userNameInDb = loginDataBaseAdapter.getUserName(userName);
                        if (userName.equalsIgnoreCase(userNameInDb)) {
                            Toast.makeText(loginActivity.this, "Congrats: Yor are now going to reset Password ", Toast.LENGTH_SHORT).show();
                            dialogForget.dismiss();
                        }


                    }
                });

                dialogForget.show();

            }
        });




        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if (password.equals(storedPassword)) {
                    Toast.makeText(loginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(loginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });


        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
