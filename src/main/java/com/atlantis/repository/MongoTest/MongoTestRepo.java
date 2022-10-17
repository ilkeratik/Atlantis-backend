package com.atlantis.repository.MongoTest;

import com.atlantis.model.MongoTest.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTestRepo extends MongoRepository<TestModel, String> {

}
