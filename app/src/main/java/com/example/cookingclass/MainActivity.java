package com.example.cookingclass;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate;
    private int mYear, mMonth, mDay;
    RadioGroup radioGroup;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_class);

        final LinearLayout linearLayout=findViewById(R.id.calendar_layout);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        final EditText numOfPeopleEt=(EditText)findViewById(R.id.amount_of_people);
        Button doneBtn = (Button)findViewById(R.id.done_button);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.desserts:
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.dessert));
                        break;
                    case R.id.italian:
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.italian));
                        break;
                    case R.id.japanese:
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.japanese4));
                        break;
                }
            }
        });

        Spinner spinner=findViewById(R.id.in_time);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.time_array,android.R.layout.select_dialog_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numStr = numOfPeopleEt.getText().toString();

                if(!(numStr.matches("^[0-9]+$")))
                {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errornumpp_str), Toast.LENGTH_SHORT).show();
                }

                if((numStr.matches("^[0]+$")))
                {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errornumpp_str), Toast.LENGTH_SHORT).show();
                }

                else if (txtDate.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errordate_str), Toast.LENGTH_SHORT).show();
                }

                else if(radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errortype_str), Toast.LENGTH_SHORT).show();
                }

                else {
                    int num = Integer.parseInt(numStr);
                    Intent intent = new Intent(MainActivity.this, PeopleList.class);
                    intent.putExtra("howMany",num);
                    startActivity(intent);
                }
            }
        });}

    @Override
    public void onClick(View v) {

        Spinner spinner = findViewById(R.id.in_time);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.time_array,android.R.layout.select_dialog_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();

            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            Calendar mcurrentDate = Calendar.getInstance();
            mcurrentDate.add(Calendar.DATE, 1);
            datePickerDialog.getDatePicker().setMinDate(mcurrentDate.getTimeInMillis());
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {
            spinner.performClick();
        }
    }
}
