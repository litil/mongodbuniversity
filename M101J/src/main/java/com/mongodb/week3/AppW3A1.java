package com.mongodb.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;

public class AppW3A1 {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();

        MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("school");
        final MongoCollection<Document> collection = database.getCollection("students");

        try {
            // get a list of document with the student id and the lower homework

            List<Document> results = collection
                    .find()
                    .into(new ArrayList<Document>());

            ArrayList<Document> scores = null;
            for (Document document : results) {
                // get and sort the scores
                scores = (ArrayList<Document>) document.get("scores");
                Collections.sort(scores, (s1, s2) -> ((Double) s1.get("score")).compareTo((Double) s2.get("score")));

                for (Document score : scores) {
                    if (score.getString("type").equals("homework")){
                        // the first homework score is the lowest one
                        // remove that score and quit the loop
                        scores.remove(score);
                        break;
                    } else {
                        // do nothing
                        continue;
                    }
                }

                // set the new array of scores
                Bson filter = new Document("_id", document.get("_id"));
                Bson newValue = new Document("scores", scores);
                Bson updateOperationDocument = new Document("$set", newValue);
                collection.updateOne(filter, updateOperationDocument);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
