package com.example.ahmedel_diasty.mas;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ahmedel_diasty.mas.Model.Schedule;
import com.example.ahmedel_diasty.mas.Model.StudentsInLocation;
import com.example.ahmedel_diasty.mas.Model.StudentsInLocationData;
import com.example.ahmedel_diasty.mas.Remote.ApiClient;
import com.example.ahmedel_diasty.mas.Remote.ApiInterface;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManualAttendance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView recyclerView;
    private StudentRowAdapter adapter;
    private MaterialSearchView searchView;

    private ApiInterface apiInterface;
    StudentsInLocation studentsInLocation;
    StudentsInLocation studentsInLocation2;
    ArrayList<StudentsInLocationData> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        students =new ArrayList<>();
        studentsInLocation =new StudentsInLocation();
        studentsInLocation2 =new StudentsInLocation();

//        //Search View
        searchView = findViewById(R.id.searchview);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                recyclerView.setAdapter(adapter);

            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<StudentsInLocation>locationCall = apiInterface.getStudentsCall();
        locationCall.enqueue(new Callback<StudentsInLocation>() {
            @Override
            public void onResponse(Call<StudentsInLocation> call, final Response<StudentsInLocation> response) {
                studentsInLocation = response.body();
                adapter = new StudentRowAdapter(getApplicationContext(), studentsInLocation);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                Log.i("+++++++++++++","Hello world");
            }

            @Override
            public void onFailure(Call<StudentsInLocation> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Not Response", Toast.LENGTH_SHORT).show();

            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText != null && !newText.isEmpty()){
                    students.clear();
                    for(int i = 0;i<studentsInLocation.getData().size();i++){
                        String s = studentsInLocation.getData().get(i).getName();
                        if(s.contains(newText)||s.toLowerCase().contains(newText)){
                            students.add(studentsInLocation.getData().get(i));
                            Log.i("++++++++++++",""+students);

                        }
                        studentsInLocation2.setData(students);


                    }

                    StudentRowAdapter adapter1 = new StudentRowAdapter(getApplicationContext(),studentsInLocation2);
                    recyclerView.setAdapter(adapter1);
                }
                else{
                    recyclerView.setAdapter(adapter);
                }
                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        searchView.setMenuItem(menuItem);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.home){
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        }
        else if(id == R.id.schedule){
            Intent intent = new Intent(this, Lectures.class);
            startActivity(intent);
        }
        else if(id == R.id.questionMenu){
            Intent intent = new Intent(this, QuestionMainActivity.class);
            startActivity(intent);
        }

        else if(id == R.id.aboutus){
            // will be added
        }
        else {
            id = R.id.logout;
            SharedPreferences sharedPreferences = getSharedPreferences("Login data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username","default");
            editor.putString("name","default");
            editor.putString("role","default");
            editor.putString("level","default");
            editor.apply();
            Intent intent = new Intent(this,HomePage.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}