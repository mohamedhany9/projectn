package com.example.ahmedel_diasty.mas;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.ahmedel_diasty.mas.Model.Answer;
import com.example.ahmedel_diasty.mas.Model.DataQuestion;
import com.example.ahmedel_diasty.mas.Model.Question;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class QuestionMainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] Slide_desc;
    private Button NButton;
    private Button BButton;
    private Button SButton;
    private int mCurrentPage;
    private RadioGroup radioGroup;

    List<Question> questions;
    List<Answer>answers;
    Answer answer ;
    String answerData;
    ArrayList<String> arrayList = new ArrayList<String>();
    RadioButton bad;
    RadioButton good;
    RadioButton very_good;
    RadioButton excellent;

    int selectedValueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questin_activity_main);
        AndroidNetworking.initialize(QuestionMainActivity.this);


        mSlideViewPager = findViewById(R.id.slideViewPager);
        radioGroup = findViewById(R.id.radiogroup);
        bad = findViewById(R.id.bad);
        good = findViewById(R.id.good);
        very_good = findViewById(R.id.very_good);
        excellent = findViewById(R.id.excellent);
        NButton = findViewById(R.id.button);
        BButton = findViewById(R.id.button2);
        SButton = findViewById(R.id.button3);


        mSlideViewPager.addOnPageChangeListener(viewListener);
        get_Questions();
        getArrayElements();


        NButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    mSlideViewPager.setCurrentItem(mCurrentPage);
                }
                else
                {
                      selectedValueId = radioGroup.getCheckedRadioButtonId();
                    if (selectedValueId == bad.getId()) {
                        answerData = "bad";
                    } else if (selectedValueId == good.getId()) {
                        answerData = "good";
                    } else if (selectedValueId == very_good.getId()) {
                        answerData = "very good";
                    } else if (selectedValueId == excellent.getId()) {
                        answerData = "excellent";
                    }


                    if (arrayList.size() == 5) {
                        post_answer();
                        return;
                    } else {
                        arrayList.add(answerData);
                    }

                    Log.d("switch data", arrayList.toString());
                    Toast.makeText(getApplicationContext(), "array add", Toast.LENGTH_LONG).show();

                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                    getArrayElements();

                }
            }
        });




        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
                arrayList.remove(answerData);

            }
        });

        SButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_answer();
            }
        });

    }

    public void  getArrayElements (){
        for(int i = 0 ; i< arrayList.size()  ; i++){
            arrayList.get(i);
            Log.d("switch2 data", arrayList.toString());
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


            mCurrentPage = position;

            if (position == 0) {
                NButton.setEnabled(true);
                BButton.setEnabled(false);
                NButton.setText("Next");
                BButton.setVisibility(View.GONE);


            } else if (position == 4) {
                NButton.setEnabled(true);
                BButton.setEnabled(true);
                BButton.setVisibility(View.VISIBLE);
                NButton.setText("Finish");
                BButton.setText("Back");
                NButton.setVisibility(View.GONE);
                SButton.setVisibility(View.VISIBLE);


            } else {
                NButton.setEnabled(true);
                BButton.setEnabled(true);
                BButton.setVisibility(View.VISIBLE);
                NButton.setText("Next");
                BButton.setText("Back");
                SButton.setVisibility(View.INVISIBLE);
                NButton.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void get_Questions() {
        AndroidNetworking.get("http://www.syntax-eg.esy.es/api/questionsByAdmin")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(new DataQuestion().getClass(),
                        new ParsedRequestListener<DataQuestion>() {
                            @Override
                            public void onResponse(DataQuestion response) {
                                questions = response.getQuestions();
                                sliderAdapter = new SliderAdapter(QuestionMainActivity.this, questions);
                                mSlideViewPager.setAdapter(sliderAdapter);


                                Log.e("Recived_success", questions.toString());
                                Toast.makeText(getApplicationContext(),
                                        "Get DataQuestion Succussefuly ", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e("Recived_Error", anError.toString());
                            }
                        });
    }


    private void post_answer() {
        getArrayElements();
        Log.d("switch data3", arrayList.toString());
    //    final Answer answer =new Answer(answers);
          answer =new Answer(arrayList.get(0),arrayList.get(1),arrayList.get(2),arrayList.get(3) );
        AndroidNetworking.post("http://www.syntax-eg.esy.es/api/questionsByStudtents")
                .addBodyParameter(answer) // posting java object
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Toast.makeText(QuestionMainActivity.this,"Correct post " , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(QuestionMainActivity.this,"error post " , Toast.LENGTH_LONG).show();
                        Log.d("DataQuestion",error.getMessage());
                        Log.d("DataQuestion msg",answers.toString());


                    }
                });

    }


}