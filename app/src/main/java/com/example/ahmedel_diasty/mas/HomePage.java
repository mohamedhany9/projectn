package com.example.ahmedel_diasty.mas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

public class HomePage extends AppCompatActivity {
    final String DEFAULT = "default";
    String username ,name ,email ,level ,role;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        textView = (TextView)findViewById(R.id.proj_name1);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"MTCORSVA.TTF");
        textView.setTypeface(typeface);
        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
         username = sharedPreferences.getString("username",DEFAULT);
         name = sharedPreferences.getString("name",DEFAULT);
         email = sharedPreferences.getString("email",DEFAULT);
         level = sharedPreferences.getString("level",DEFAULT);
         role = sharedPreferences.getString("role",DEFAULT);
         if(!username.equals(DEFAULT)){
             Intent intent = new Intent(this,Home.class);
             intent.putExtra("name",name);
             intent.putExtra("email",email);
             intent.putExtra("level",level);
             intent.putExtra("role",role);
             intent.putExtra("type","student");
             startActivity(intent);
         }
    }
    public void nextStudent(View view){
        Intent intent = new Intent(this,LoginForm.class);
        intent.putExtra("type","student");
        startActivity(intent);
    }
    public void nextInstructor(View view){
        Intent intent = new Intent(this,LoginForm.class);
        intent.putExtra("type","instructor");
        startActivity(intent);
    }
    public void newAccount(View view){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

    @Override

    public void onBackPressed() {

        //Display alert message when back button has been pressed

        backButtonHandler();

        return;

    }
    public void backButtonHandler() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title

        alertDialog.setTitle("Leave application?");

        // Setting Dialog Message

        alertDialog.setMessage("Are you sure you want to leave the application?");

        // Setting Icon to Dialog

//        alertDialog.setIcon(R.drawable.dialog_icon);

        // Setting Positive "Yes" Button

        alertDialog.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }

                });

        // Setting Negative "NO" Button

        alertDialog.setNegativeButton("NO",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Write your code here to invoke NO event

                        dialog.cancel();

                    }

                });

        // Showing Alert Message

        alertDialog.show();

    }
}
