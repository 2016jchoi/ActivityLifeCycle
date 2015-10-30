package com.example.jchoi.activitylifecycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String CREATE = "create";
    private String START = "start";
    private String RESUME = "resume";
    private String PAUSE = "pause";
    private String STOP = "stop";
    private String RESTART = "restart";
    private String DESTROY = "destroy";
    private String st;
    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });

        Button button = (Button) findViewById(R.id.forward);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StartActivity.class);
                startActivity(i); // brings up the second activity
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> dataAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.value_array));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        st = spinner1.getSelectedItem()+"";

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st = parent.getSelectedItem()+"";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button2 = (Button) findViewById(R.id.btnSubmit);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, StartActivity.class);
                in.putExtra("value", st);
                startActivity(in);
            }
        });

        Intent intent = getIntent();
        String va = intent.getStringExtra("subValue");
        if(va!=null) {
            TextView t = (TextView) findViewById(R.id.userChoice);
            t.setText("User chose " + va);
        }

        SharedPreferences setting = MainActivity.this.getSharedPreferences("Settings", 0);

        int a = execute(CREATE);
        TextView txt = (TextView)findViewById(R.id.c);
        txt.setText("onCreate was called " + a + " times");

        int startInt = setting.getInt(START, 0);
        TextView txt2 = (TextView)findViewById(R.id.start);
        txt2.setText("onStart was called "+startInt+" times");

        int resumeInt = setting.getInt(RESUME, 0);
        TextView txt3 = (TextView)findViewById(R.id.resume);
        txt3.setText("onResume was called " + resumeInt + " times");

        int b = setting.getInt(PAUSE, 0);
        TextView txt4 = (TextView)findViewById(R.id.p);
        txt4.setText("onPause was called "+b+" times");

        int stopInt = setting.getInt(STOP, 0);;
        TextView txt5 = (TextView)findViewById(R.id.stop);
        txt5.setText("onStop was called "+stopInt+" times");

        int restartInt = setting.getInt(RESTART, 0);
        TextView txt6 = (TextView)findViewById(R.id.restart);
        txt6.setText("onRestart was called "+restartInt+" times");

        int de = setting.getInt(DESTROY, 0);
        TextView txt7 = (TextView)findViewById(R.id.d);
        txt7.setText("onDestroy was called "+de+" times");
    }
    @Override
    public void onStart()
    {
        super.onStart();
        int startInt = execute(START);
        TextView txt = (TextView)findViewById(R.id.start);
        txt.setText("onStart was called "+startInt+" times");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        int resumeInt = execute(RESUME);
        TextView txt = (TextView)findViewById(R.id.resume);
        txt.setText("onResume was called "+resumeInt+" times");
    }
    @Override
    public void onPause()
    {
        super.onPause();
        int b = execute(PAUSE);
        TextView txt = (TextView)findViewById(R.id.p);
        txt.setText("onPause was called "+b+" times");
    }
    @Override
    public void onStop()
    {
        super.onStop();
        int stopInt = execute(STOP);
        TextView txt = (TextView)findViewById(R.id.stop);
        txt.setText("onStop was called "+stopInt+" times");
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        int restartInt = execute(RESTART);
        TextView txt = (TextView)findViewById(R.id.restart);
        txt.setText("onRestart was called "+restartInt+" times");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        int e = execute(DESTROY);
        TextView txt = (TextView)findViewById(R.id.d);
        txt.setText("onDestroy was called " + e + " times");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int execute(String s)
    {
        SharedPreferences setting = MainActivity.this.getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = setting.edit();
        int n = setting.getInt(s, 0);
        n++;
        editor.putInt(s, n).commit();
        return n;
    }
    public void onSubmit() {
        // closes the activity and returns to first screen
        this.finish();
    }
}
