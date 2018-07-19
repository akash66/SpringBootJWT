package com.akash.database;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;

public class MongoUtils {

	public static String filterToString(Bson filter) {
		return filter.toBsonDocument(Document.class, MongoClient.getDefaultCodecRegistry()).toJson();
	}
}
