package com.example.bhasin.manageruser;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.bhasin.manageruser.R.layout.list_item;

/**
 * Created by Administrator on 05-08-2017.
 */

public class Output_Adapter extends ArrayAdapter<Model> {

    ImageButton imageButton, infoButton;
    Context context;

public Output_Adapter(Activity context, ArrayList<Model> androidSubjects) {
        super(context, 0, androidSubjects);
        this.context = context;
        }



public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(
                list_item, parent, false);
        }

// Get the {@link AndroidFlavor} object located at this position in the list
final Model currentandroidSubjects = getItem(position);

        imageButton = (ImageButton) listItemView.findViewById(R.id.downloadButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        String Link = currentandroidSubjects.getLink();

        Log.e("Down", "Downloading Started");

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Link));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, currentandroidSubjects.getTopic());
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Toast.makeText(context, "Downloading ...", Toast.LENGTH_SHORT).show();
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);



        }
        });
        infoButton = (ImageButton) listItemView.findViewById(R.id.InfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent i = new Intent(context, InfoActivity.class);
        Bundle extras = new Bundle();

        extras.putString("time", currentandroidSubjects.getTime());
        extras.putString("subject", currentandroidSubjects.getSubject());
        extras.putString("Class", currentandroidSubjects.getClasses());
        extras.putString("count", currentandroidSubjects.getDownloads());
        extras.putString("topic", currentandroidSubjects.getTopic());
        i.putExtras(extras);
        ((Results) context).startActivity(i);
        }
        });


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.topicTextView);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentandroidSubjects.getTopic());

        TextView SubjectTextView = (TextView) listItemView.findViewById(R.id.SubjectName);
        SubjectTextView.setText(currentandroidSubjects.getSubject());


        TextView NumberTextView = (TextView) listItemView.findViewById(R.id.NumberTextView);
        NumberTextView.setText("Downloads : " + currentandroidSubjects.getDownloads());

        return listItemView;
        }


        }
