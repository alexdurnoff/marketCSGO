package ru.durnov.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import ru.durnov.dao.Item;

/*
 * Класс парсит сайт и выдает список Item, которые сейчас есть на сайте.
 */
@Component
public class ItemFromSite {
	
	private final String firstPage = "https://market.csgo.com/?r=&q=&h=";
	private final String nextPageExpression="https://market.csgo.com/?r=&q=&p=%d&h=";

	
	public List<Item> items(){
		List<Item> itemList = new ArrayList<>();
		boolean hasNextPage = true;
		int i = 1;
		while (hasNextPage) {
			String connect = null;
			if (i==1) {
				connect = "https://market.csgo.com/?r=&q=&h=";
			} else {
				connect = String.format("https://market.csgo.com/?r=&q=&p=%d&h=", i);
			}
			Document document = null;
			try {
				document = Jsoup.connect(connect).get();
			} catch (IOException e) {
				hasNextPage = false;
			}
			try {
				Elements elements = document.getAllElements();
				elements.forEach(element -> {
					if (element.ownText().contains("|") && element.nodeName().equals("div")) {
						String[] strings = element.ownText().split("[/|]");
						Item item = new Item();
						item.setName(strings[0]);
						item.setDescription(strings[1]);
						itemList.add(item);
					}
				});
				i++;
			} catch (NullPointerException e) {
				return itemList;
			}
		}
		
		return itemList;
	}
	

}
