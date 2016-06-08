package com.mongodb.week3;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.Helpers;

import freemarker.template.Configuration;

public class AppW3A1 {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();

        MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("school");
        final MongoCollection<Document> collection = database.getCollection("students");

        try {
            Helpers.printJson(collection.find().first());

//            // select homework type document, sort by student and then by score
//            Bson filter = Filters.eq("type", "homework");
//            Bson sort = Sorts.orderBy(Sorts.ascending("student_id"), Sorts.ascending("score"));
//            List<Document> results = collection
//                    .find(filter)
//                    .sort(sort)
//                    .into(new ArrayList<Document>());
//
//            int studentId = -1;
//            for (Document document : results) {
//                if (studentId == -1 || studentId != document.getInteger("student_id", -1)){
//                    // remove this document
//                    studentId = document.getInteger("student_id", -1);
//                    collection.deleteOne(document);
//                } else {
//                    studentId = document.getInteger("student_id", -1);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
