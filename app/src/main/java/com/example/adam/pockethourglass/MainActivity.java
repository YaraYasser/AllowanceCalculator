package com.example.adam.pockethourglass;

import android.content.Context;
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
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
//Mohamed habib was here
    int money=0;
    int day=0;
    int today=0;
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

        String str=readFromFile();
        if(!str.isEmpty()) {
            String[] sarr = str.split("@");
            if (sarr[0] != "") {
                Totalmoney.setText(sarr[0] + "جنيه");
                money = Integer.parseInt(sarr[0]);
            }
            if (sarr[1] != "") {
                day = Integer.parseInt(sarr[1]);
                if (day == 1)
                    Days.setText("يوم واحد");
                else if (day == 2)
                    Days.setText("يومين");
                else if (day >= 3 && day <= 10)
                    Days.setText(day + " أيام");
                else if (day >= 10)
                    Days.setText(day + " يوم");
            }
            String r=Todaymoney.getText().toString();
            if(r.isEmpty()) {
                today = money / day;
                writeONFile(String.valueOf(today));
                String temp = readFile();
                Todaymoney.setText(temp);
            }
            else
            {
                String temp = readFile();
                Todaymoney.setText(temp);
            }

        }
        else
        {
            Totalmoney.setText("مفيش");
            Days.setText("مفيش");
        }
    }

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

            writeONFile(String.valueOf(n+m));
            String temp=readFile();
            Todaymoney.setText(temp);

        }

    }
    public void by3yt (View v){
        Toast.makeText(this,"يعوض عليك ربنا",Toast.LENGTH_SHORT).show();
        if (! del.getText().toString().isEmpty()){
            int m=Integer.parseInt(del.getText().toString());
            String s= Todaymoney.getText().toString();
            s=s.replace("جنيه","");
            int n=Integer.parseInt(s);
            writeONFile(String.valueOf(n+m));
            String temp=readFile();
            Todaymoney.setText(temp);

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
    private String readFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("today.txt");

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
    private void writeONFile(String to) {
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("today.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(to);
            outputStreamWriter.close();
        }
        catch (IOException e) {

        }
    }

}
