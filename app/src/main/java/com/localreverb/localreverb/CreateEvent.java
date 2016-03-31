package com.localreverb.localreverb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.localreverb.localreverb.Event.Event;
import com.localreverb.localreverb.services.MongoConnect;
import com.localreverb.localreverb.services.MongoSearch;

public class CreateEvent extends AppCompatActivity {
    private EditText eventTitle;
    private EditText eventArtist;
    private EditText eventDescription;
    private Button   submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventTitle = (EditText)findViewById(R.id.EventName);
        eventArtist = (EditText)findViewById(R.id.ArtistName);
        eventDescription = (EditText)findViewById(R.id.EventDescription);
        submitButton = (Button)findViewById(R.id.submitEvent);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event x = new Event(eventArtist.getText().toString(),eventTitle.getText().toString(),eventDescription.getText().toString());

              //  AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
              //  builder.setMessage(R.string.successEvent).setTitle(R.string.dialogSuccess);
                MongoConnect create = new MongoConnect(x);
                create.execute();

          //      AlertDialog dialog = builder.create();


                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);


            }
        });





    }

}
