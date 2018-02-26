package com.costs.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories("cost")
@PropertySource("classpath:/com/costs/mongo.properties")
public class MongoConfig extends AbstractMongoConfiguration {
	@Value("${mongodb.databaseName}")
	private String databaseName;

	@Value("${mongodb.hostname}")
	private String hostname;

	@Value("${mongodb.port}")
	private Integer port;

	@Value("${mongodb.username}")
	private String userName;

	@Value("${mongodb.password}")
	private String password;

	@Override
	protected String getDatabaseName() {
		return this.databaseName;
	}

	@Override
	public MongoClient mongoClient() {
		List<MongoCredential> listOfCredentials = new ArrayList<>();
		listOfCredentials.add(MongoCredential.createCredential(userName, databaseName, password.toCharArray()));
		return new MongoClient(new ServerAddress(hostname, port), listOfCredentials);
	}
}