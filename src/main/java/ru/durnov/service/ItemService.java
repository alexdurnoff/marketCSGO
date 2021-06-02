package ru.durnov.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.durnov.dao.Item;
import ru.durnov.dao.ItemRepository;

@Component
public class ItemService {
	
	private final ItemRepository itemRepository;
	private final List<Item> itemList = new ArrayList<>();
	
	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
		this.items().forEach(i -> itemList.add(i));
	}
	
	public Iterable<Item> items(){
		return this.itemRepository.findAll();
	}
	
	public Optional<Item> itemById(long id) {
		return this.itemRepository.findById(id);
	}
	
	public List<Item> itemList(){
		return this.itemList;
	}
	
	public boolean containsItem(Item item) {
		for (Item i : this.itemList) {
			if ((i.getName().equals(item.getName())) 
					&& (i.getDescription().equals(item.getDescription()))) return true;
		}
		return false;
	}
	
	public void delete(Item item) {
		this.itemRepository.delete(item);
		this.itemList.remove(item);
	}
	
	public void addItem(Item item) {
		this.itemRepository.save(item);
		this.itemList.add(item);
	}

	public void updateItemList(List<Item> items) {
		this.itemList.forEach(i -> {
			if (! items.contains(i)) this.itemRepository.delete(i);
		});
		this.itemList.clear();
		items.forEach(i -> this.itemList.add(i));
	}
	
	
}
