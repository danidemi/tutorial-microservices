package com.danidemi.tutorial.microservices.springboot.restclient;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    public static void main(String[] args) {
        new RestClient().run();
    }

    private void run() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<NewItem> request = new HttpEntity<>(new NewItem("bar"));
        Item foo = restTemplate.postForObject("http://localhost:8080/items", request, Item.class);

        System.out.println(foo);

    }

}
