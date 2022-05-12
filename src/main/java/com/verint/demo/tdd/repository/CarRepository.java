package com.verint.demo.tdd.repository;

import com.verint.demo.tdd.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    public Optional<Car> findByName(String name);

}
