package com.costs.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("cost")
public class MongoConfig extends AbstractMongoConfiguration {
	@Override
	protected String getDatabaseName() {
		return "cost";
	}

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient();
	}

//neki ididorov komentars sddfgdfgdfgfd
}