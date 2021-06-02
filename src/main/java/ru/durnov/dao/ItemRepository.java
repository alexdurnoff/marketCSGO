package ru.durnov.dao;

import javax.annotation.PostConstruct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{
	
	@PostConstruct
	public default void init() {
		this.deleteAll();
	}
}
