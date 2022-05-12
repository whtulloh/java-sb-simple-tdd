package com.verint.demo.tdd.service;

import com.verint.demo.tdd.exception.CarNotFoundException;
import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Cacheable("car")
    public Car getCarDetails(String name) {
        Car car = null;
        Optional<Car> optionalCar = carRepository.findByName(name);
        if (optionalCar.isPresent()){
            car = optionalCar.get();
        }else{
            throw new CarNotFoundException();
        }

        return car;
    }
}
