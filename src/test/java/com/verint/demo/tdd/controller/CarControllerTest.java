package com.verint.demo.tdd.controller;

import com.verint.demo.tdd.exception.CarNotFoundException;
import com.verint.demo.tdd.model.Car;
import com.verint.demo.tdd.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    @Test
    public void getCarDetails() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setName("Brio");
        car.setType("LCGC");

        BDDMockito.given(carService.getCarDetails(Mockito.anyString())).willReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/Brio"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Brio"))
                .andExpect(MockMvcResultMatchers.jsonPath("type").value("LCGC"));
    }

    @Test
    public void carNotFound() throws Exception {
        BDDMockito.given(carService.getCarDetails(Mockito.anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/Brio"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
