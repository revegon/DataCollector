/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import javafx.scene.control.Alert;
import org.bson.Document;

/**
 *
 * @author Xenon
 */
public class BFIdataTable {
    
    MongoDatabase db;
    
    String col_name = "BFIdata";

    public BFIdataTable() 
    {
        
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDatabase("thesis");
            System.out.println("connected");
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No MongoDB server found!!!");
            a.show();
            System.exit(0);
        }
    }
    
    
    public void insert(Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection(col_name);
        booklist.insertOne(doc);
    }

    public void delete(String attr, Object val)
    {
        MongoCollection<Document> booklist = db.getCollection(col_name);
        booklist.deleteOne(eq(attr, val));
    }

    public void update(String attr, Object val, Document doc)
    {
        MongoCollection<Document> booklist = db.getCollection(col_name);
        booklist.updateOne(eq(attr, val), new Document("$set", doc));       
    }
    
    public int getIndex()
    {
        Document doc = new Document();
        MongoCollection<Document> col = db.getCollection(col_name);
        if(col.count()<=0) return 1;
        for(Document d: col.find()) doc = d;
        int j =(int) doc.get("dataid");
        return j+1;
    }
    
//    public MongoCollection<Document> getData()
//    {
//        return db.getCollection(col_name);
//    }
    
}
