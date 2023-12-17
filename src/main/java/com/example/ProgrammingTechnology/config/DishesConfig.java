package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        dish.setDescription("СОСИХУЙ");
        dish.setWeight(300f);
        File fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\pizza.jpg");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        dish.setPhoto(fileContent);
        service.createDish(dish);

        Dish cake = new Dish();
        cake.setDescription("Cake is a lie");
        cake.setName("Тортик");
        cake.setWeight(200f);
        cake.setPrice(500f);
        cake.setCalories(350f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\тьорт.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        cake.setPhoto(fileContent);
        service.createDish(cake);

        Dish fish = new Dish();
        fish.setPrice(440f);
        fish.setDescription("fish`ka");
        fish.setName("Риба на пару");
        fish.setWeight(450f);
        fish.setCalories(150f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\риба.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        fish.setPhoto(fileContent);
        service.createDish(fish);

        Dish kakleti = new Dish();
        kakleti.setPrice(330f);
        kakleti.setDescription("как лети");
        kakleti.setName("как каклети");
        kakleti.setWeight(350f);
        kakleti.setCalories(250f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\каклети.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        kakleti.setPhoto(fileContent);
        service.createDish(kakleti);
    }
}
