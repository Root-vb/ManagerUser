package com.example.bhasin.manageruser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Spinner spinner_class,spinner_subject;
    String selected_class;
    LinearLayout btnResults;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        spinner_class = (Spinner) findViewById(R.id.class_spinner);
        spinner_subject = (Spinner) findViewById(R.id.subject_spinner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Class_string, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(adapter1);
        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                select();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
            // to close the onItemSelected
        });

        btnResults = (LinearLayout) findViewById(R.id.LLgetResults);
        btnResults.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnResults)
        {
            String Class = ((Spinner) findViewById(R.id.class_spinner)).getSelectedItem().toString().substring(0,1);
            String subject = ((Spinner) findViewById(R.id.subject_spinner)).getSelectedItem().toString();
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, Class);
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, subject);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            Intent intent = new Intent(this, Results.class);
            Bundle extras = new Bundle();
            extras.putString("Class", Class);
            extras.putString("Subject", subject);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    public void select() {
        selected_class = ((Spinner) findViewById(R.id.class_spinner)).getSelectedItem().toString();
        if(selected_class.equals("11th")||selected_class.equals("12th"))
        {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.E_and_T_Subject_string, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_subject.setAdapter(adapter1);
        }
        else if(selected_class.equals("8th")||selected_class.equals("9th"))
        {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Common_Subject_string, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_subject.setAdapter(adapter1);
        }
        else
        {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Tenth_Subject_string, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_subject.setAdapter(adapter1);
        }
    }
}
