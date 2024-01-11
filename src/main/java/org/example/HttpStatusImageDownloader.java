package org.example;

import lombok.SneakyThrows;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HttpStatusImageDownloader {

    private final String PATH = Paths.get("").toAbsolutePath().toString();
    @SneakyThrows
    void downloadStatusImage(int code){
        HttpStatusChecker checker = new HttpStatusChecker();
        String url = checker.getStatusImage(code);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .timeout(Duration.of(5, SECONDS))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        FileOutputStream fos = new FileOutputStream(PATH+"/"+code+".jpg");
        fos.write(response.body().readAllBytes());
        fos.close();


        }
    }

