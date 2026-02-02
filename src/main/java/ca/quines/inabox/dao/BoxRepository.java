package ca.quines.inabox.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.quines.inabox.dto.Box;


public interface BoxRepository extends MongoRepository<Box, String> {

	public Box findByName(String name);
	public List<Box> findByLocation(String location);
	List<Box> findTop10ByOrderByIdDesc();
	
}
