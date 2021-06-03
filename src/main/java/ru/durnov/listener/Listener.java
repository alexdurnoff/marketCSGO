package ru.durnov.listener;

import java.io.IOException;
import java.net.URISyntaxException;
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
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					parse();
				} catch (IOException e) {
				
					e.printStackTrace();
				} catch (InterruptedException e) {
		
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	
	public void parse() throws IOException, InterruptedException, URISyntaxException {
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
