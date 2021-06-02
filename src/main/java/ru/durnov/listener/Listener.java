package ru.durnov.listener;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ru.durnov.dao.Item;
import ru.durnov.service.ItemService;
import ru.durnov.telegramm.Sender;


public class Listener {
	
	private final ItemService itemService;
	private final ItemFromSite ItemFromSite;
	private final Sender sender;
	
	@Autowired
	public Listener(ItemService itemService, ItemFromSite itemFromSite, Sender sender) {
		this.itemService = itemService;
		this.ItemFromSite = itemFromSite;
		this.sender = sender;
	}
	
	@PostConstruct
	private void start() throws InterruptedException {
		parse();
	}
	
	
	public void parse() {
		while (true) {
			List<Item> items = this.ItemFromSite.items();
			for (Item item : items) {
				if (!this.itemService.containsItem(item)) {
					this.sender.send(item);
					this.itemService.addItem(item);
				}
			}
			this.itemService.updateItemList(items);
		}
	}

}
