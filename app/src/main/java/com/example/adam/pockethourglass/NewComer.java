package com.example.adam.pockethourglass;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NewComer extends ActionBarActivity {

    Calendar Date;
    int year, month, day;
    String numberOfDays, totalMoney, endDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comer);
        final EditText editTextTotalMoney = (EditText) findViewById(R.id.editTotalMoney);//text box bta3 m3ak kam youm
        final TextView numberOfDaysView = (TextView) findViewById(R.id.numberOfDaysView); // text view bta3 youm wa7d ele bytl3 b3d ma tdos 3la el calender
        final Button toDateButton = (Button) findViewById(R.id.toDateButton);// button of the calender
        final Button Save = (Button) findViewById(R.id.save);

        totalMoney = editTextTotalMoney.getText().toString(); //edit the text view ele fel main ele by3rd el total money

        toDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pickDate(numberOfDaysView);
            }
        }); //calender choosing day


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code database here

                //Toast.makeText(getApplicationContext(), NumberOfDays +"", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("TotalMoney", totalMoney);
                intent.putExtra("N", numberOfDays).toString();
                //intent.putExtra("NumberOfDays", value)
                startActivity(intent);

            }
        });
    }

    private void pickDate(final TextView numberOfDaysView) {
        Date = Calendar.getInstance();
        day = Date.get(Calendar.DAY_OF_MONTH);
        year = Date.get(Calendar.YEAR);
        month = Date.get(Calendar.MONTH);


        //TODO: disable selection of days before today.
        DatePickerDialog datepicker = new DatePickerDialog(NewComer.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                endDateString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                numberOfDays = numberOfDays(endDateString); // text view ele bttl3 b3d ma a5tar el calender 3shan a confirm
                if (Integer.parseInt(numberOfDays) == 1)
                    numberOfDaysView.setText("يوم واحد");
                else if (Integer.parseInt(numberOfDays) == 2)
                    numberOfDaysView.setText("يومين");
                else if (Integer.parseInt(numberOfDays) >= 3 && Integer.parseInt(numberOfDays) <= 10)
                    numberOfDaysView.setText(numberOfDays + " أيام");
                else if (Integer.parseInt(numberOfDays) >= 10)
                    numberOfDaysView.setText(numberOfDays + " يوم");
            }
        }, year, month, day);
        datepicker.show();
    }
    // conda was here
    // menna was here
    // romario was here
    private String numberOfDays(String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        String[] Ssplited = currentDateandTime.split("/");
        String[] Esplited = endDateString.split("/");
//TODO: fix the equation that calculates days
        int days = Integer.valueOf(Esplited[0]) - Integer.valueOf(Ssplited[0]);
        int Month = Integer.valueOf(Esplited[1]) - Integer.valueOf(Ssplited[1]);
        int Years = Integer.valueOf(Esplited[2]) - Integer.valueOf(Ssplited[2]);
        String numberOfDays = String.valueOf(days + 30 * Month + (Years * 365));
        return numberOfDays;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
