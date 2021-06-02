package ru.durnov.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import ru.durnov.dao.Expression;

@Controller
@RequestMapping("/admin")
@Slf4j
public class MarketController {

	private final ExpressionService expressionService;

	@Autowired
	public MarketController(ExpressionService expressionService) {
		this.expressionService = expressionService;
	}

	@GetMapping
	public String adminPage(Model model) {
	    model.addAttribute("expressions", expressionService.expressions());
		return "admin";
	}


	@GetMapping("add") public String addExpression(Model model) { 
		model.addAttribute("expression", new Expression());
		return "add";
	}

	@PostMapping("add") public String addExpression(@RequestParam Expression expression) throws IOException { 
		this.expressionService.addExpression(expression);
		return "redirect:/"; }
	
	@PostMapping("delete") 
	public String deleteExpression(Expression expression)throws IOException {
		this.expressionService.deleteExpression(expression.getId());
		return "redirect:/"; }


}
