package com.verint.demo.tdd.controller;

import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/{name}")
    public ResponseEntity<Car> getCarDetails(@PathVariable("name") String name) throws Exception {
        Car car = carService.getCarDetails(name);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}
