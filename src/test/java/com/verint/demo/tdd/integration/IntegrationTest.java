package com.verint.demo.tdd.integration;

import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    public int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    CarService carService;

    HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void getCarDetail(){
        HttpEntity<String> entity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<Car> response = testRestTemplate.exchange(
                "http://localhost:"+port+"/cars/duster", HttpMethod.GET, entity, Car.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("hybrid",response.getBody().getType());
    }

    @Test
    public void carNotFound(){
        HttpEntity<String> entity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<Car> response = testRestTemplate.exchange(
                "http://localhost:"+port+"/cars/scala", HttpMethod.GET, entity, Car.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
