package ru.durnov.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.durnov.dao.Expression;
import ru.durnov.dao.ExpressionRepository;



@Component
public class ExpressionService {
	
	
	private final ExpressionRepository expressionRepository;
	
	
	private final List<Expression> expressions;
	
	@Autowired
	public ExpressionService(ExpressionRepository expressionRepository) {
		this.expressionRepository = expressionRepository;
		this.expressions = new ArrayList<>();
	}
	
	
	public List<Expression> expressions(){
		this.expressionRepository.findAll().forEach(e -> this.expressions.add(e));
		return this.expressions;
	}
	
	public void addExpression(String str) {
		Expression expression = new Expression();
		expression.setExpression(str);
		this.expressionRepository.save(expression);
	}
	
	public void addExpression(Expression expression) {
		this.expressionRepository.save(expression);
		this.expressions.add(expression);
	}
	
	public void deleteExpression(int id) {
		this.expressions.remove(this.expressionRepository.findById(id));
		this.expressionRepository.deleteById(id);
	}
	
	@PostConstruct
	private void init() {
		Expression expression = new Expression();
		expression.setExpression("Наклейка");
		this.expressionRepository.save(expression);
		expression = new Expression();
		expression.setExpression("АК-47");
		this.expressionRepository.save(expression);
	}
	
	

}
