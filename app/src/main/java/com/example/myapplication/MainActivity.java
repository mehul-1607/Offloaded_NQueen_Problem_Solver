package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText n;
    Button b;
    String val=null;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.gobtn);
        n = findViewById(R.id.inputn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!n.getText().toString().equals(""))
                {
                    val = n.getText().toString();
                    goToSecondActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "Kindly provide the value of n.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void goToSecondActivity() {
        Intent x = new Intent(this, SecondActivity.class);
        x.putExtra("n",val);
        startActivity(x);
    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}