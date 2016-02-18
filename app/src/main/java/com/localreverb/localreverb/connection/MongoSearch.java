package com.localreverb.localreverb.connection;
import android.os.AsyncTask;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;
/**
 * Created by daevincicode on 2/18/16.
 */
public class MongoSearch extends AsyncTask {
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String result;
    private String dbQuery;

    public MongoSearch(String x){
        this.dbQuery=x;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        searchDb();
        return null;
    }

    public String searchDb(){
        try{
            MongoClient mongoClient = new MongoClient( "ec2-54-201-12-47.us-west-2.compute.amazonaws.com" );

            @SuppressWarnings("deprecation")
            DB db = mongoClient.getDB("mydb");
            DBCollection coll = db.getCollection("testCollection");


            BasicDBObject doc = new BasicDBObject("name", dbQuery)
                    .append("type", "person")
                    .append("count", 1)
                    .append("info", new BasicDBObject("x", 215).append("y", 267));

            coll.insert(doc);
            DBObject dae = coll.findOne();

            DBObject searchResponse = (DBObject)(coll.find((DBObject) eq("name", dbQuery)));

            result = searchResponse.toString();



        }
        catch( Exception e){
            System.out.println("did not insert");
        }
        return result;}


}

