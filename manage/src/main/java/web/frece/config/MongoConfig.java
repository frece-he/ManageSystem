package web.frece.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import web.frece.util.ConstantsData;



@Configuration
public class MongoConfig {
	private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);
	
	@Bean(name = "client")
	public MongoClient getMongoClient() {
		MongoClient client = null;
		
		try {
			ServerAddress serverAddress = new ServerAddress("localhost", 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
//			MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//			credentials.add(credential);
//			client = new MongoClient(addrs,credentials);
			client = new MongoClient(addrs);
		} catch (Exception e) {
			log.error("MongoConfig Error", e);
		}
		
		return client;
	}
	
	@Bean(name = "defaultDB")
	public MongoDatabase getMongoDatabase() {
		MongoDatabase defaultDB = null;		
		try {
			defaultDB = getMongoClient().getDatabase(ConstantsData.DEFAULT_DB);
		} catch (Exception e) {
			log.error("MongoConfig Error", e);
		}
		return defaultDB;
		
	}
}
