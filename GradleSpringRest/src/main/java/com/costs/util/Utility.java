package com.costs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Component
@EnableWebMvc
public class Utility {
	@Autowired
	private MongoOperations mongoOperations;

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public int getSequenceNextval(int sequenceName) {

		Query getSequenceDocumentById = new Query();
		getSequenceDocumentById.addCriteria(Criteria.where("_id").is(sequenceName));
		Update updateNextval = new Update();
		updateNextval.inc("nextval", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		Sequences sequence = mongoOperations.findAndModify(getSequenceDocumentById, updateNextval, options,
				Sequences.class);

		return sequence.getSeqNextval();
	}

}
