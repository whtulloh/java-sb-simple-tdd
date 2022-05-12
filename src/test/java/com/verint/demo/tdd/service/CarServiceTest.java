package com.verint.demo.tdd.service;

import com.verint.demo.tdd.exception.CarNotFoundException;
import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCarDetails() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setName("Pulse");
        car.setType("Sedan");

        BDDMockito.given(carRepository.findByName("Pulse")).willReturn(Optional.of(car));

        Car car1 = carService.getCarDetails("Pulse");
        Assertions.assertNotNull(car1);
        Assertions.assertEquals("Pulse", car1.getName());
        Assertions.assertEquals("Sedan", car1.getType());
    }

    @Test
    public void carNotFound() {
        BDDMockito.given(carRepository.findByName("Pulse")).willThrow(new CarNotFoundException());

        Assertions.assertThrows(CarNotFoundException.class, ()-> carService.getCarDetails("Pulse"));
    }
}
