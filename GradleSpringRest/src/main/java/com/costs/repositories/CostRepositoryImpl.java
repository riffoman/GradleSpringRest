package com.costs.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.costs.data.Cost;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Repository
public class CostRepositoryImpl implements CostRepository {
	@Autowired
	private MongoOperations mongoOperations;

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	@Override
	public <S extends Cost> List<S> saveAll(Iterable<S> entites) {
		while (entites.iterator().hasNext()) {
			mongoOperations.save(entites.iterator().next());
		}
		return null;
	}

	@Override
	public List<Cost> findAll() {
		List<Cost> listOfAllCosts = new ArrayList<Cost>();
		listOfAllCosts = mongoOperations.findAll(Cost.class);

		return listOfAllCosts;
	}

	@Override
	public List<Cost> findAll(Sort sort) {
		return null;
	}

	@Override
	public <S extends Cost> S insert(S entity) {
		mongoOperations.insert(entity);
		return null;
	}

	@Override
	public <S extends Cost> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cost> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cost> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cost> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cost> S save(S entity) {
		mongoOperations.save(entity);
		return entity;
	}

	public Cost findCostById(int id) {
		Query getCostDocumentById = new Query();
		getCostDocumentById.addCriteria(Criteria.where("_id").is(id));
		Cost costDocument = (Cost) mongoOperations.findOne(getCostDocumentById, Cost.class, "cost");

		return costDocument;
	}

	@Override
	public Optional<Cost> findById(String id) {
		Query getCostDocumentById = new Query();
		getCostDocumentById.addCriteria(Criteria.where("_id").is(id));
		Cost costDocument = (Cost) mongoOperations.find(getCostDocumentById, Cost.class);
		// metoda treba da vrati nest onormalnoa

		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Cost> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return mongoOperations.getCollection("cost").count();
	}

	@Override
	public void deleteById(String id) {
		mongoOperations.remove(Criteria.where("_id").is(id), "cost");

	}

	@Override
	public void delete(Cost entity) {
		mongoOperations.remove(entity);

	}

	@Override
	public void deleteAll(Iterable<? extends Cost> entities) {
		while (entities.iterator().hasNext()) {
			mongoOperations.remove(entities.iterator().next());
		}

	}

	@Override
	public void deleteAll() {
		mongoOperations.remove(new Query(), "cost");
	}

	@Override
	public <S extends Cost> Optional<S> findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends Cost> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cost> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Cost> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cost> findByCostDescription(String c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cost> findByCostDescriptionLike(String c) {
		// TODO Auto-generated method stub
		return null;
	}

}
