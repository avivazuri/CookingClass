package com.example.cookingclass;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PeopleList extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_layout);

        Bundle extras = getIntent().getExtras();
        int numOfPeople = extras.getInt("howMany");
        //The key argument here must match that used in the other activity

        Button finishBtn=new Button(PeopleList.this);

        LinearLayout numOfEtLayout = findViewById(R.id.num_of_et);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        finishBtn.setLayoutParams(layoutParams);
        finishBtn.setText(getResources().getString(R.string.next_str));
        finishBtn.setBackground(getResources().getDrawable(R.drawable.button));
        finishBtn.setTypeface(Typeface.DEFAULT_BOLD);
        finishBtn.setAllCaps(false);
        finishBtn.setTextColor(Color.WHITE);


        for(int i = 1 ;i <= numOfPeople; i++){

            final EditText nameEt = new EditText(PeopleList.this);
            EditText phoneEt = new EditText(PeopleList.this);
            TextView partiTv = new TextView(PeopleList.this);

            partiTv.setLayoutParams(layoutParams);
            nameEt.setLayoutParams(layoutParams);
            phoneEt.setLayoutParams(layoutParams);

            nameEt.setInputType(InputType.TYPE_CLASS_TEXT);
            phoneEt.setInputType(InputType.TYPE_CLASS_NUMBER);

            nameEt.setMaxLines(1);
            phoneEt.setMaxLines(1);

            partiTv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            nameEt.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            phoneEt.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            partiTv.setTextColor(Color.BLACK);
            nameEt.setTextColor(Color.BLACK);
            phoneEt.setTextColor(Color.BLACK);

            partiTv.setText(getResources().getString(R.string.parti_str) + " " + i);
            partiTv.setTypeface(Typeface.DEFAULT_BOLD);

            nameEt.setHint(getResources().getString(R.string.name_str));
            phoneEt.setHint(getResources().getString(R.string.phonenum_str));

            numOfEtLayout.addView(partiTv);
            numOfEtLayout.addView(nameEt);
            numOfEtLayout.addView(phoneEt);
        }

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PeopleList.this, getResources().getString(R.string.thanks_str), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PeopleList.this,MainActivity.class);
                startActivity(intent);
            }
        });
        numOfEtLayout.addView(finishBtn);
    }
}
