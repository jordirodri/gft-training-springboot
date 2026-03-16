package org.example.gfttrainingspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GftTrainingSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GftTrainingSpringbootApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "hola soy albert";
    }
}
