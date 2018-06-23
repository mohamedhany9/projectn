package com.example.ahmedel_diasty.mas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.Data;
import com.example.ahmedel_diasty.mas.Model.Model;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginForm extends AppCompatActivity {
    ApiInterface apiInterface;
    EditText APPusername,APPpassword;
    Model model;
    String type;
    TextView textView;
    Button button;
    String status = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        textView = (TextView)findViewById(R.id.proj_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"MTCORSVA.TTF");
        textView.setTypeface(typeface);
        APPusername = findViewById(R.id.username);
        APPpassword = findViewById(R.id.password);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        button = findViewById(R.id.btn_login);

    }
    public void test(View view){
        button.setEnabled(false);
        if (type.equals("student")){
            final String username = APPusername.getText().toString();
            final String password = APPpassword.getText().toString();
            model = new Model();


            if(ValidateLogin(username,password)){
                apiInterface = ApiClient.getClient().create(ApiInterface.class);



                Call<Model> studentModelCall = apiInterface.setData(username,password);
                studentModelCall.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        model = response.body();

                        status = response.body().getStudentLogin().get(0).getName();


                        Log.i("++++++++++++++++",""+response.code());

                        if (response.code()==200){
                            SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id",model.getStudentLogin().get(0).getId());
                            editor.putString("username",model.getStudentLogin().get(0).getUsername());
                            editor.putString("name",model.getStudentLogin().get(0).getName());
                            editor.putString("email",model.getStudentLogin().get(0).getEmail());
                            editor.putString("role",model.getStudentLogin().get(0).getRole());
                            editor.putString("level",model.getStudentLogin().get(0).getLevel());
                            editor.apply();
                            Log.i("++++++++++++++++",""+model.getStudentLogin().size());

                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            button.setEnabled(true);
                        }
                        else{
                            Toast.makeText(LoginForm.this, "Username or Password is inCorrect", Toast.LENGTH_SHORT).show();
                            button.setEnabled(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(LoginForm.this, "Failure", Toast.LENGTH_SHORT).show();
                        button.setEnabled(true);
                    }
                });
            }
        }
        else{
            final String username = APPusername.getText().toString();
            final String password = APPpassword.getText().toString();
            model = new Model();
            if(ValidateLogin(username,password)){
                apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Model> instructorModelCall = apiInterface.setInstructorDta(username,password);
                instructorModelCall.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        model = response.body();
                        Toast.makeText(LoginForm.this, "Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id",model.getStudentLogin().get(0).getId());
                        editor.putString("username",model.getStudentLogin().get(0).getUsername());
                        editor.putString("name",model.getStudentLogin().get(0).getName());
                        editor.putString("email",model.getStudentLogin().get(0).getEmail());
                        editor.putString("level","default");
                        editor.putString("role",model.getStudentLogin().get(0).getRole());
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(LoginForm.this, "Faliure", Toast.LENGTH_SHORT).show();
                        button.setEnabled(true);
                    }
                });
            }
        }


    }
    private boolean ValidateLogin(String username , String Password){
        if(username ==null||username.trim().length()==0){
            Toast.makeText(this, "username is Required", Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            return false;
        }
        if(Password ==null||Password.trim().length()==0){
            Toast.makeText(this, "Password is Required", Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            return false;
        }
            return true;
    }
}
