package ru.durnov.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestRepository {
	
	@Autowired
	private ItemRepository repository;
	
	
	@Test
	public void testCreateTwoItemsAndSaveAndAssertThatitemsExistsInRepository() {
		Item item1 = new Item();
		item1.setName("пиво ямское");
		item1.setDescription("Невкусное");
		Item item2 = new Item();
		item2.setName("Пиво макарий");
		item2.setDescription("Вкусное");
		repository.save(item1);
		repository.save(item2);
		Iterable<Item> iterable = repository.findAll();
		iterable.forEach(i -> {
			System.out.println(i.getId() + ":" + i.getName() + ":" + i.getDescription());
		});
	}
	
	/*
	 * @Test public void testThatDatabaseIsNotEmptyIfDDLAutoSetOnUpdate() {
	 * Optional<Item> optional = this.repository.findById((long) 1);
	 * Assertions.assertTrue(optional.isPresent()); }
	 */
	

}
