package com.coviam.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.logging.Logger;

public class ApiRequest {

    private static final Logger LOGGER = Logger.getLogger(ApiRequest.class.getName());

    @Autowired
    private static RestTemplate restTemplate = new RestTemplate();

    //Get request
    public static ResponseEntity get(String ip, Map<String, String> params){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ip);
            for(Map.Entry<String, String> me: params.entrySet()){
                builder.queryParam(me.getKey(),me.getValue());
            }
            ResponseEntity responseEntity = restTemplate.getForEntity(builder.toUriString(), Object.class, params);
            LOGGER.info(responseEntity.toString());
            return responseEntity;
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }
    }

    //Post request
    public static ResponseEntity post(String ip, Object payload){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity responseEntity = restTemplate.exchange(ip, HttpMethod.POST, entity, Object.class);
            LOGGER.info(responseEntity.toString());
            return responseEntity;
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }

    }

    //Put request
    public static ResponseEntity put(String ip, Object payload){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> responseEntity = restTemplate.exchange(ip, HttpMethod.PUT, entity, Object.class);
            LOGGER.info(responseEntity.toString());
            return responseEntity;
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }
    }

    //Delete request for obj
    public static ResponseEntity delete(String ip, Object payload){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(payload,headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> responseEntity = restTemplate.exchange(ip, HttpMethod.DELETE, entity, Object.class);
            LOGGER.info(responseEntity.toString());
            return responseEntity;
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }
    }

    //Delete request with params
    public static ResponseEntity delete(String ip, Map<String, String> params){
        try {
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity(ip, Object.class, params);
            LOGGER.info(responseEntity.toString());
            return responseEntity;
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }
    }
}
