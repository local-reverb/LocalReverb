package com.localreverb.localreverb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.localreverb.localreverb.services.MongoSearch;

public class SearchDB extends AppCompatActivity {

    private String searchX;
    private TextView searchResponse;
    private TextView searchArtist;
    private TextView searchDescription;
    private Button srch;
    private Spinner srchOption;
    private ListView eventListView;
    private ListView artistListView;
    private ListView descriptionListView;
    private ProgressBar mProgress;
    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_db);

        searchResponse = (TextView)findViewById(R.id.searchResult);
        searchArtist = (TextView)findViewById(R.id.artistResult);
        searchDescription = (TextView)findViewById(R.id.descriptionResult);

        eventListView = (ListView)findViewById(R.id.EventColumn);
        artistListView= (ListView)findViewById(R.id.ArtistColumn);
        descriptionListView= (ListView)findViewById(R.id.DescriptionColumn);
        mProgress = (ProgressBar)findViewById(R.id.searchProgressBar);
        sContext = getApplicationContext();

        srchOption = (Spinner)findViewById(R.id.searchSpinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.search_options, android.R.layout.simple_spinner_item);
      //  ArrayAdapter<CharSequence>
        srchOption.setAdapter(adapter);
        final EditText editText = (EditText) findViewById(R.id.search);

        srch = (Button) findViewById(R.id.srchButton);



        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setProgress(20);
                String spinnerSelection = srchOption.getSelectedItem().toString();
                searchX = editText.getText().toString();
                MongoSearch x = new MongoSearch(searchX, spinnerSelection);
                x.execute();
                while (x.isDone() == false) {
                    searchResponse.setText("loading...");
                }
                searchResponse.setText(x.getResult());
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(sContext,android.R.layout.simple_list_item_1,x.getEventColumnData());
                ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(sContext,android.R.layout.simple_list_item_1,x.getArtistColumnData());
                ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(sContext,android.R.layout.simple_list_item_1,x.getDescriptionColumnData());


                eventListView.setAdapter(adapter);
                artistListView.setAdapter(adapter1);
                descriptionListView.setAdapter(adapter2);

                mProgress.setVisibility(View.INVISIBLE);
                searchArtist.setText(x.getResultA());



                searchDescription.setText(x.getResultD());


            }
        });


    }

}
