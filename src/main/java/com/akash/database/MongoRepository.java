package com.akash.database;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoRepository {

	@Autowired
	private MongoDbFactory mongoDbFactory;
	private MongoClient mongoClient;
	private Jongo jongo;
	private DB db;

	@PostConstruct
	private void init() {

		String encodedCredentials = null;
		try {
			encodedCredentials = URLEncoder.encode("akashpanda2102@gmail.com", "UTF-8") + ":"
					+ URLEncoder.encode("Akash@066", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://" + encodedCredentials + "@clusterakash-zp8c7.mongodb.net/test?retryWrites=true");

		this.mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("bootdb");
		this.db = mongoClient.getDB(database.getName());
		// db = (DB) mongoDbFactory.getDb();// "bootdb"
		jongo = new Jongo(db);
	}

	public MongoCollection getCollection(String name) {
		return jongo.getCollection(name);
	}
}
