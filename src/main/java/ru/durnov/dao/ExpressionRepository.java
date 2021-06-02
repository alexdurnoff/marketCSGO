package ru.durnov.dao;

import javax.annotation.PostConstruct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionRepository extends CrudRepository<Expression, Integer>{
	
}
