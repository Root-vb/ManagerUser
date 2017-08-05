package com.example.bhasin.manageruser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    String Classes, Subject, ApiResponse;
    ListView listview;
    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle extras = getIntent().getExtras();
        Classes = extras.getString("Class");
        Subject = extras.getString("Subject");
        Response Res = new Response();
        ApiResponse = Res.getActualresponse();
    }

    public void Parsing() {
        int flag = 0;
        String a;
        try {
            JSONArray mainArrray = new JSONArray(ApiResponse);
            Log.e("data", mainArrray + "");
            Log.e("datalength", mainArrray.length() + "");
            flag = 0;
            for (int i = 0; i < mainArrray.length(); i++) {
                JSONObject object = mainArrray.getJSONObject(i);
                Log.e("object", object + "");
                Model model = new Model();

                if (object.getString("subject").trim().toLowerCase().equals(Subject.toLowerCase()) && object.getString("class").trim().equals(Classes)) {
                    flag = 1;
                    model.setId(object.getString("id"));
                    model.setTopic(object.getString("topic"));
                    model.setSubject(object.getString("subject"));
                    model.setClasses(object.getString("class"));
                    model.setTime(object.getString("created_date"));
                    model.setLink(object.getString("link"));
                    model.setDownloads(object.getString("downloads"));
                    list.add(model);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (flag == 0)
            setContentView(R.layout.not_found);
        else
            generate_list();

    }

    public void generate_list() {
        /*ImageButton imageButton;*/
        setContentView(R.layout.activity_results);
        Output_Adapter iAdapter = new Output_Adapter(this, list);
        listview = (ListView) findViewById(R.id.list_of_results);
        listview.setAdapter(iAdapter);
        /*imageButton = (ImageButton) findViewById(R.id.downloadButton);*/

    }

}