package org.example;

import lombok.SneakyThrows;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {

    private static final String URL_PATH = "https://http.cat";
    @SneakyThrows
    String getStatusImage(int code)  {
        int responseCode = 0;
        String url = URL_PATH+ "/"+code+".jpg";

        URI uri = new URI(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        responseCode= response.statusCode();
        if (responseCode>=400){
            throw new Exception(String.valueOf(responseCode));
        }

        return url;
    }
}
