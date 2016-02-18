package com.localreverb.localreverb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.localreverb.localreverb.connection.MongoSearch;

public class SearchDB extends AppCompatActivity {

    private String searchX;
    private TextView searchResponse;
    private Button srch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_db);

        searchResponse = (TextView)findViewById(R.id.searchResult);
        final EditText editText = (EditText) findViewById(R.id.search);

        srch = (Button) findViewById(R.id.srchButton);
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchX = editText.getText().toString();
                MongoSearch x = new MongoSearch(searchX);
                x.execute();

                searchResponse.setText(x.getResult());
            }
        });


    }

}
