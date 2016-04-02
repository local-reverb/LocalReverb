package com.localreverb.localreverb.services;
import android.os.AsyncTask;

import com.localreverb.localreverb.Event.Event;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by daevincicode on 2/16/16.
 */
public class MongoConnect extends AsyncTask{

    public MongoConnect(Event x) {
        this.x = x;
    }

    MongoClient mongoClient = new MongoClient( "ec2-54-201-12-47.us-west-2.compute.amazonaws.com" );
    @SuppressWarnings("deprecation")
    MongoDatabase db = mongoClient.getDatabase("mydb");
    MongoCollection coll = db.getCollection("testCollection");
    private Event x;

    @Override
    protected Object doInBackground(Object[] params) {
        postEvent();
        return null;


    }


    public String connectTest(){
    try{



        BasicDBObject doc = new BasicDBObject("name", "Daeshawn")
                .append("type", "person")
                .append("count", 1)
                .append("info", new BasicDBObject("x", 215).append("y", 267));

        coll.insertOne(doc);


    }
    catch( Exception e){
        System.out.println("did not insert");
    }
    return "success";}



    public void postEvent(){
        try{
            Document doc = new Document("Title",x.getTitle() )
                    .append("Artist Name", x.getArtist())
                    .append("Description", x.getDescription());


            coll.insertOne(doc);
        }
        catch (Exception e){
            System.out.print("failed");
        }


        System.out.print("success");
    }

}
