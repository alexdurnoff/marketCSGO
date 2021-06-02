package ru.durnov.listener;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ru.durnov.dao.Item;

class TestItemFromSite {

	@Test
	void test() {
		ItemFromSite itemFromSite = new ItemFromSite();
		List<Item> itemList = itemFromSite.items();
		itemList.forEach(i -> {
			System.out.println(i.getId() + ":" + i.getName() + ":" + i.getDescription());
		});
	}

}
