package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@RequiredArgsConstructor
public class DishesConfig {
    private final DishService service;

    @Bean
     void addData() throws IOException {
        Dish dish = new Dish();
        dish.setName("Кур очка");
        dish.setPrice(300f);
        dish.setCalories(130f);
        dish.setDescription("fffffааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа" +
                "ааааааааааааааааааааааааааааааааааааааа" +
                "уцкцуаааааааааааааааааааааааааааааааааааааааааааааааааааСОСИХУЙлоокупршрррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррррр" +
                "");

        //dish.setDescription("fff");
        dish.setWeight(300f);
        File fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\pizza.jpg");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        dish.setPhoto(fileContent);
        for (int i = 0; i < 50; i++) {
            service.createDish(dish);
            dish.setId(null);
        }
    }
}
