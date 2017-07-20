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

public class MainActivity extends AppCompatActivity {

    public void goToRegister(View view){

        //Create an Intent in order to launch the Register Activity
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

        startActivityForResult(intent, 1);
    }

    public void goToManage(View view){

        //Create an Intent in order to launch the Manage Activity
        Intent intent = new Intent(getApplicationContext(), ManageActivity.class);

        startActivity(intent);
    }

    private void clearForm(ViewGroup group){
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);

            Log.i("Info", "Current view is: " + view.toString());

            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

    public void clearLogin(View view){

        clearForm((ViewGroup) findViewById(R.id.mainActivity));

    }

    public void tryLogin(View view){

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.lfitzpatrick.sharedpreferences", Context.MODE_PRIVATE);

        EditText usernameEditText = (EditText) findViewById(R.id.usernameText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordText);

        String username = usernameEditText.getText().toString();
        String passwordEntered = passwordEditText.getText().toString();

        String passwordStored = sharedPreferences.getString(username, "");

        Log.i("Info", "Username is: " + username);
        Log.i("Info", "Password entered is: " + passwordEntered);
        Log.i("Info", "Password stored is: " + passwordStored);

        if (passwordStored.equals(passwordEntered)) {
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            goToManage(view);
        } else {
            Toast.makeText(MainActivity.this, "Username or Password incorrect", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String username = data.getStringExtra("username");
                EditText usernameEditText = (EditText) findViewById(R.id.usernameText);
                usernameEditText.setText(username.toCharArray(), 0, username.length());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }//onActivityResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.lfitzpatrick.sharedpreferences", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("test@test.com", "password").apply();
    }
}
