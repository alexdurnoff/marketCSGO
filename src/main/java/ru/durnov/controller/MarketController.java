package ru.durnov.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("add") public String addExpression(String expression) throws IOException {
		this.expressionService.addExpression(expression);
		return "redirect:/admin"; }

	@GetMapping("delete/{id}")
	public String deleteById(@PathVariable int id){
		this.expressionService.deleteExpression(id);
		return "redirect:/admin";
	}
}
