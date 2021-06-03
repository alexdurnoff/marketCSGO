package ru.durnov.telegramm;

import ru.durnov.dao.Item;

public class ItemMessage {
	private final Item item;
	
	public ItemMessage(Item item) {
		this.item = item;
	}

	public String message() {
		return this.item.getName() + " : " + this.item.getDescription();
	}
}
