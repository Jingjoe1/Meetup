package com.example.meetupapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CreateMeeting extends AppCompatActivity {

    private int mYear, mMonth, mDay, mHour, mMinute;
    final DB myDb = new DB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

       
        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this,R.array.category,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);


    }

    public void Select_Date(View view)
    {
        final EditText txtDate = (EditText)findViewById(R.id.editText6);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        txtDate.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();

    }
    public void Insert_DB(View view)
    {
        EditText meet_name = (EditText)findViewById(R.id.editText);
        EditText description = (EditText)findViewById(R.id.editText2);
        EditText date = (EditText)findViewById(R.id.editText6);
        EditText amount = (EditText)findViewById(R.id.editText4);
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        EditText locations  = (EditText)findViewById(R.id.editText7);

        String m_n = meet_name.getText().toString();
        String des = description.getText().toString();
        String da = date.getText().toString();
        String am = amount.getText().toString();
        String sp = spin.getSelectedItem().toString();
        String locate = locations.getText().toString();


        if(m_n.isEmpty() || des.isEmpty() || da.isEmpty() || am.isEmpty() ||  locate.isEmpty() ){
            Toast.makeText(CreateMeeting.this,"Insert Data Failed.",
                    Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(am)>100)
        {
            Toast.makeText(CreateMeeting.this,"Insert Data Failed.",
                    Toast.LENGTH_LONG).show();
        }
        else{
        // Statement 1
        long flg1 = myDb.Insert_Data(m_n,des,da,am,sp,locate);
        if(flg1 > 0)
        {
            Toast.makeText(CreateMeeting.this, "Insert Data Successfully",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(CreateMeeting.this,"Insert Data Failed.",
                    Toast.LENGTH_LONG).show();
        }
        Intent itn = new Intent(this,MainActivity.class);
        startActivity(itn);
        }
    }

}
