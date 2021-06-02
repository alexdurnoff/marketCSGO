package ru.durnov.controller;

import java.util.*;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import ru.durnov.dao.Expression;
import ru.durnov.dao.ExpressionRepository;



@Component
@Slf4j
public class ExpressionService {
	
	
	private final ExpressionRepository expressionRepository;


	private final Set<Expression> expressions;

	@Autowired
	public ExpressionService(ExpressionRepository expressionRepository) {
		this.expressionRepository = expressionRepository;
		this.expressions = new HashSet<>();
	}

	public Set<Expression> expressions(){
		return this.expressions;
	}

	@Transactional
	public void addExpression(String str) {
		Expression expression = new Expression();
		expression.setExpression(str);
		this.expressionRepository.save(expression);
		this.expressions.add(expression);
	}

	@Transactional
	public void deleteExpression(int id) {
		Optional<Expression> optional = this.expressionRepository.findById(id);
		if (optional.isPresent()){
			this.expressions.remove(optional.get());
			this.expressionRepository.deleteById(id);
		}
	}
	
	@PostConstruct
	private void init() {
		Expression expression = new Expression();
		expression.setExpression("Наклейка");
		this.expressionRepository.save(expression);
		expression = new Expression();
		expression.setExpression("АК-47");
		this.expressionRepository.save(expression);
		this.expressionRepository.findAll().forEach(this.expressions::add);
	}
	
	

}
