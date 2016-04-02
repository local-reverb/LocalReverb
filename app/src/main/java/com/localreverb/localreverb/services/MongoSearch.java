package com.localreverb.localreverb.services;
import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by daevincicode on 2/18/16.
 */
public class MongoSearch extends AsyncTask {
    private String result;
    private String resultA;
    private String resultD;
    private String dbQuery;
    private String searchOption;
    private String artistSearch = "Artist Name";
    private String eventSearch = "Event Name";
    private boolean done=false;

    public List<String> getEventColumnData() {
        return eventColumnData;
    }

    private List<String> eventColumnData = new ArrayList<String>();
    private List<String> artistColumnData = new ArrayList<String>();

    public List<String> getDescriptionColumnData() {
        return descriptionColumnData;
    }

    public List<String> getArtistColumnData() {
        return artistColumnData;
    }

    private List<String> descriptionColumnData = new ArrayList<String>();


    public String getResult() {
        return result;
    }

    public String getResultD() {
        return resultD;
    }

    public String getResultA() {
        return resultA;
    }


    MongoClient mongoClient = new MongoClient( "ec2-54-201-12-47.us-west-2.compute.amazonaws.com" );

    public MongoSearch(String x){
        this.dbQuery=x;
    }

    public MongoSearch(String dbQuery, String searchOption) {
        this.dbQuery = dbQuery;
        this.searchOption = searchOption;
    }

    public List<String> tablesResult(FindIterable<Document> results, String columnName){
        List<String> columnData = new ArrayList<String>();
        for(Document d : results){
            columnData.add(d.get(columnName).toString());
        }

        return columnData;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        searchDb();
        this.done=true;
        return null;


    }

    public boolean isDone() {
        return done;
    }

    public void searchDb(){
        try{


            @SuppressWarnings("deprecation")
            MongoDatabase db = mongoClient.getDatabase("mydb");
            MongoCollection<Document> coll = db.getCollection("testCollection");

            if(this.searchOption.equals( eventSearch)) {
                FindIterable<Document> searchResponse = coll.find(eq("Title", dbQuery));

                for (Document d : searchResponse) {
                    Log.d("print", (String) d.get("Title"));
               }

                this.result = (String) searchResponse.first().get("Title");
                this.resultA = (String) searchResponse.first().get("Artist Name");
                this.resultD = (String) searchResponse.first().get("Description");
            }


            if(this.searchOption.equals( artistSearch)) {
                FindIterable<Document> searchResponse = coll.find(eq("Artist Name", dbQuery));

                for (Document d : searchResponse) {
                    Log.d("print", (String) d.get("Artist Name"));
               }

                this.result = (String) searchResponse.first().get("Title");
                this.resultA = (String) searchResponse.first().get("Artist Name");
                this.resultD = (String) searchResponse.first().get("Description");

                eventColumnData=tablesResult(searchResponse,"Title");
                artistColumnData=tablesResult(searchResponse,"Artist Name");
                descriptionColumnData=tablesResult(searchResponse,"Description");
            }




        }



        catch( Exception e){
            System.out.println("did not insert");
        }
    }


}

