package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class SecondActivity extends AppCompatActivity {

    GridLayout gridLayout;
    RelativeLayout relativeLayout,relativeLayout2;
    TextView nview;
    Button nbtn,exitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        relativeLayout = findViewById(R.id.relativelayout);
        relativeLayout2 = findViewById(R.id.relativelayout2);
        nview = findViewById(R.id.textn);
        nbtn = findViewById(R.id.btnnqueen);
        exitbtn = findViewById(R.id.btnnterminate);

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridLayout = (GridLayout) findViewById(R.id.gridview);
        gridLayout.removeAllViews();

        Intent x = getIntent();
        String n = x.getExtras().getString("n");

        nview.setText("Rows = "+n +" and Columns = "+n);

        int column = Integer.parseInt(n);
        int row = Integer.parseInt(n);

        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row);

        int total = row*column;

        for (int i = 0, c = 0, r = 0; i < total; i++, c++)
        {
            if (c == column)
            {
                //move to next row after all the columns are filled for first row
                c = 0;
                r++;
            }

            //create a new textview
            TextView textView = new TextView(this);
            String id = String.valueOf(r+1) + String.valueOf(c+1);
            textView.setId(Integer.parseInt(id));
            textView.setGravity(Gravity.CENTER);
            //textView.setText(String.valueOf(textView.getId()));

            //setting the background color in the pattern of chess board
            if (c==r) {
                textView.setBackgroundColor(Color.parseColor("#000000"));
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else if(c%2==0 && r%2==0){
                textView.setBackgroundColor(Color.parseColor("#000000"));
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else if (c%2!=0 && r%2!=0){
                textView.setBackgroundColor(Color.parseColor("#000000"));
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else {
                textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                textView.setTextColor(Color.parseColor("#000000"));
            }

            //setting the width and height for this textview
            textView.setWidth(
                    (int)width/column
                    - relativeLayout.getPaddingLeft()
                    - relativeLayout2.getPaddingLeft()
            );
            textView.setHeight(
                    (int)width/column
                    - relativeLayout.getPaddingTop()
                    - relativeLayout2.getPaddingTop()
            );

            //setting the row and column number for this textview
            GridLayout.Spec rowSpan = GridLayout.spec(r, 1);
            GridLayout.Spec colspan = GridLayout.spec(c, 1);
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                    rowSpan, colspan);

            textView.setLayoutParams(gridParam);
            gridLayout.addView(textView, gridParam);

        }
        /*
        * To access the text view, use:
        * int id = ID_TO_BE_ACCESSED
        * textview.findViewById(id)
        * */
        nbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Request queued...", Toast.LENGTH_SHORT).show();
                if (column!=0 && row!=0){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                //the green colored squares show the valid positions of N queens
                                NQueenProblem Queen = new NQueenProblem(Integer.parseInt(n), SecondActivity.this);
                            try {
                                Queen.solveNQ();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },2000);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBacktoMainActivity();
    }

    private void goBacktoMainActivity() {
        Intent main = new Intent(SecondActivity.this,MainActivity.class);
        startActivity(main);
        finish();
    }
}