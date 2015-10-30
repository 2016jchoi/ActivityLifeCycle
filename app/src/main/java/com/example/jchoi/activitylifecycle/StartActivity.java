package com.example.jchoi.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });

        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        String v = intent.getStringExtra("value");
        TextView t = (TextView)findViewById(R.id.vchoice);
        t.setText("The value chosen was "+v);

        final EditText editT = (EditText)findViewById(R.id.enter);

        Button button2 = (Button) findViewById(R.id.valueSubmit);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                String str = editT.getText()+"";
                i.putExtra("subValue", str);
                startActivity(i);
            }
        });
    }

    public void onSubmit() {
        // closes the activity and returns to first screen
        this.finish();
    }
}
