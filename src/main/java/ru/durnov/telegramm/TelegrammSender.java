package ru.durnov.telegramm;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.durnov.dao.Item;

@Component
@Slf4j
public class TelegrammSender implements Sender{
	
	private final String token = "1634468768:AAGlELAnmljg0I4VKvDxONB6WeYXGh8ugX4";
	private final long id = -1001342633168L;
	
	@Override
	public void send(Item item) throws IOException, InterruptedException, URISyntaxException {
		String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", token, id);
		 HttpClient client = HttpClient.newBuilder()
	                .version(HttpClient.Version.HTTP_1_1)
	                .followRedirects(HttpClient.Redirect.NORMAL)
	                .connectTimeout(Duration.ofSeconds(20))
	                .build();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .timeout(Duration.ofSeconds(20))
	                .POST(HttpRequest.BodyPublishers.ofString(new ItemMessage(item).message()))
	                .build();
	        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
	                .thenApply(HttpResponse::body)
	                .thenAccept(System.out::println);
	}

}
