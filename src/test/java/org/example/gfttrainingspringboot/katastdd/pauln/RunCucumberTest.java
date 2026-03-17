package org.example.gfttrainingspringboot.katastdd.pauln;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "org.example.gfttrainingspringboot.katastdd.pauln"
)
public class RunCucumberTest {
}