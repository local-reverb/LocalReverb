package com.localreverb.localreverb.connection;
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
/**
 * Created by daevincicode on 2/16/16.
 */
public class MongoConnect {

    public String connectTest(){
    try{
        MongoClient mongoClient = new MongoClient( "ec2-54-201-12-47.us-west-2.compute.amazonaws.com" );

        @SuppressWarnings("deprecation")
        DB db = mongoClient.getDB( "mydb" );
        DBCollection coll = db.getCollection("testCollection");

        System.out.println("success");

        BasicDBObject doc = new BasicDBObject("name", "Daeshawn")
                .append("type", "person")
                .append("count", 1)
                .append("info", new BasicDBObject("x",215).append("y", 267));

        coll.insert(doc);
        DBObject dae = coll.findOne();
        System.out.println(dae);


    }
    catch( Exception e){
        System.out.println("did not insert");
    }
    return "success";}

}
