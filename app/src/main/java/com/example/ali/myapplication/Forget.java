package com.example.ali.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Forget extends AppCompatActivity {
    Button btnForgetReset;
    String mUserName;
    Dialog dialogForget;
    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);

        //getting db
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        Button btnForgetPasswordforreset = (Button) findViewById(R.id.buttonforgetpasswordforreset);

        btnForgetPasswordforreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextspinneranswer = (EditText) findViewById(R.id.Spinneranswerforget);
                Spinner spinnerQuestion = (Spinner) findViewById(R.id.secutity_spinner_forget);


                EditText editTextUserNameforget = (EditText) findViewById(R.id.editTextUserNameToLoginForget);

                String securityQuestionSpinner = spinnerQuestion.getSelectedItem().toString();

                String userName = editTextUserNameforget.getText().toString();
                String spinneranswer = editTextspinneranswer.getText().toString();
                mUserName = userName;
                String userNameInDb = loginDataBaseAdapter.getUserName(userName);
                String spinnerInDb = loginDataBaseAdapter.getSpinner(userName);
                String spinneranswerInDb = loginDataBaseAdapter.getSpinneranswer(userName);

/*
                        if(TextUtils.isEmpty(userName)) {
                            editTextUserNameforget.setError("cannot be empty");
                            return;
                        }*/


                if (userName.equalsIgnoreCase(userNameInDb) && securityQuestionSpinner.equals(spinnerInDb) && spinneranswer.equals(spinneranswerInDb)) {
                    Toast.makeText(getApplicationContext(), " Yor are now going to reset Password ", Toast.LENGTH_SHORT).show();
                    //dialogForget.dismiss();

                    //
                    final Dialog dialogReset = new Dialog(Forget.this);
                    dialogReset.setContentView(R.layout.reset);
                    dialogReset.setTitle("reset");
                    dialogReset.show();
                    //
                    btnForgetReset = (Button) dialogReset.findViewById(R.id.buttonAccountReset);
                    btnForgetReset.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            //
                            final EditText editTextPassword = (EditText) dialogReset.findViewById(R.id.editTextPasswordReset);
                            final EditText editTextConfirmPasswordReset = (EditText) dialogReset.findViewById(R.id.editTextConfirmPasswordReset);
                            final Spinner questionSpinnerid = (Spinner) dialogReset.findViewById(R.id.secutity_spinner_reset);
                            final EditText editTextSpinnerAnswer = (EditText) dialogReset.findViewById(R.id.Spinneranswerreset);

                            //
                            String password = editTextPassword.getText().toString();
                            String conformPassword = editTextConfirmPasswordReset.getText().toString();
                            String spinnerAnswer = editTextSpinnerAnswer.getText().toString();
                            //to get text of the item selected on spinner
                            String questionSpinner = questionSpinnerid.getSelectedItem().toString();


                            //check if fields are empty
                            if (password.equals("") || conformPassword.equals("") || spinnerAnswer.equals("")) {
                                Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                                return;
                            }

                            // check if both password matches
                            if (!password.equals(conformPassword)) {
                                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                // Update the Data in Database
                                loginDataBaseAdapter.passwordReset(mUserName, password, spinnerAnswer, questionSpinner);
                                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), " reset complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                                startActivity(intent);
                                finish();

                                //dialogForget.dismiss();
                            }


                        }
                    });//close of btnforgetreset


                }//close of ifmusername
                else {
                    Toast.makeText(getApplicationContext(), "Check you user name as its not registered ", Toast.LENGTH_SHORT).show();

                }//close of else


            }//close of onclickview
        });//close of btnforgetpassword for reset


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

}
