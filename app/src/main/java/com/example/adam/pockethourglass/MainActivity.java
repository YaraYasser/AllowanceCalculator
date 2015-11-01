package com.example.adam.pockethourglass;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
//Mohamed habib was here
    int m=0;
    int d=0;
    EditText add;
    TextView Todaymoney;
    EditText del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Todaymoney=(TextView) findViewById(R.id.todayMoney);
        TextView Totalmoney=(TextView) findViewById(R.id.totalMoney);
        TextView Days=(TextView) findViewById(R.id.textView);
        add=(EditText) findViewById(R.id.added);
        del=(EditText) findViewById(R.id.deleted);
        Totalmoney.setText("مفيش");
        Todaymoney.setText("مفيش");
        String str=readFromFile();
        String [] sarr=str.split("@");
        if(sarr[0]!= ""){
            Totalmoney.setText(sarr[0]+"جنيه");
            m=Integer.parseInt(sarr[0]);
        }
        if(sarr[1]!=""){
            d=Integer.parseInt(sarr[1]);
            if (d == 1)
                Days.setText("يوم واحد");
            else if (d == 2)
                Days.setText("يومين");
            else if (d >= 3 && d <= 10)
                Days.setText(d + " أيام");
            else if (d >= 10)
                Days.setText(d + " يوم");
        }
        if(!str.isEmpty()){
            Todaymoney.setText(String.valueOf(m/d)+"جنيه");
        }
    }
    /*Intent i=getIntent();
    String S= i.getStringExtra("TotalMoney");
    if(i.hasExtra("TotalMoney")) {
        money= Integer.parseInt(S);

        Totalmoney.setText(S + "جنيه");

    }
    else {

        Totalmoney.setText("لسه");
    }
    S =i.getStringExtra("N");
    if(i.hasExtra("N")) {
        d = Integer.parseInt(S);
        if (d == 1)
            Days.setText("يوم واحد");
        else if (d == 2)
            Days.setText("يومين");
        else if (d >= 3 && d <= 10)
            Days.setText(d + " أيام");
        else if (d >= 10)
            Days.setText(d + " يوم");
        if(i.hasExtra("TotalMoney")) {
            int TM = money / d;
            Todaymoney.setText(TM + "جنيه");
        }

    }
    else
            Days.setText("لسه");*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_newMoney:
                Intent _intent = new Intent(MainActivity.this, NewComer.class); // navigates to the new page
                startActivity(_intent); // starts the new comer page
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void halal (View v){
      Toast.makeText(this,"ولعه يا زميلى",Toast.LENGTH_SHORT).show();
        if(!add.getText().toString().isEmpty()) {
           int m = Integer.parseInt(add.getText().toString());

            String s=Todaymoney.getText().toString();
            s=s.replace("جنيه","");
            int n=Integer.parseInt(s);
            Todaymoney.setText(String.valueOf(n+m));
       }

    }
    public void by3yt (View v){
        Toast.makeText(this,"يعوض عليك ربنا",Toast.LENGTH_SHORT).show();
        if (! del.getText().toString().isEmpty()){
            int m=Integer.parseInt(del.getText().toString());
            String s= Todaymoney.getText().toString();
            s=s.replace("جنيه","");
            int n=Integer.parseInt(s);
            Todaymoney.setText(String.valueOf(n-m));
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
        } catch (IOException e) {

        }

        return ret;
    }
}
