package com.lfitzpatrick.finalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private void clearForm(ViewGroup group){
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

    public void cancelRegister(View view){

        clearForm((ViewGroup) findViewById(R.id.registerActivity));

        goToLogin(view);

    }

    public void goToLogin(View view, String username){

        Intent returnIntent = new Intent();
        returnIntent.putExtra("username", username);
        Log.i("Info", "Username in Extra set to: " + username);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void goToLogin(View view){

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    public void addUser(View view, String username, String password){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.lfitzpatrick.sharedpreferences", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString(username, password).apply();

        showMessage(view, username + " added successfully");

        goToLogin(view, username);
    }


    //Function to show error message
    public void showMessage(View view, String msg){
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void checkRegisterFields(View view){

        EditText emailText = (EditText) findViewById(R.id.emailText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        EditText confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String confirmPassword = confirmPasswordText.getText().toString();

        if (email.equals("") || password.equals("") || confirmPassword.equals("")){
            showMessage(view, "Please complete all fields before submitting");
        } else if (!password.equals(confirmPassword)){
            showMessage(view, "Password do not match");
        } else if (password.equals(confirmPassword)){
            addUser(view, email, password);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
