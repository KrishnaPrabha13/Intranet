package com.srm.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.srm.core.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

}
