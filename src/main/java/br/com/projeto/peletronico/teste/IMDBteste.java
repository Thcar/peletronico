package br.com.projeto.peletronico.teste;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class IMDBteste {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		 try {
			HttpRequest request = HttpRequest
			            .newBuilder()
			            .uri(new URI("https://imdb-api.com/en/API/Search/k_8j04lg1o/topgun")).GET().build();
			
			HttpClient client = HttpClient.newBuilder()
				        .version(Version.HTTP_1_1)
				        .followRedirects(Redirect.NORMAL)
				        .connectTimeout(Duration.ofSeconds(20))
				        .build();
				   HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				   System.out.println(response.statusCode());
				   System.out.println(response.body());  
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}

}
