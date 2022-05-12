package com.verint.demo.tdd.cache;

import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.repository.CarRepository;
import com.verint.demo.tdd.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class cacheTest {

    @MockBean
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @Test
    public void cacheTest() {
        Car car = new Car();
        car.setId(1L);
        car.setName("pulse");
        car.setType("hatchback");

        BDDMockito.given(carRepository.findByName("pulse")).willReturn(Optional.of(car));

        Car car1 = carService.getCarDetails("pulse");
        Assertions.assertNotNull(car1);
        carService.getCarDetails("pulse");

        Mockito.verify(carRepository, Mockito.times(1)).findByName("pulse");
    }

}
