package ru.durnov.telegramm;

import java.io.IOException;
import java.net.URISyntaxException;

import ru.durnov.dao.Item;

public interface Sender {
	void send(Item item) throws IOException, InterruptedException, URISyntaxException;
}
