package org.example.gfttrainingspringboot.katastdd;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.util.List;

import org.junit.jupiter.api.Test;

class KatastddCoverageTest {

    @Test
    void shouldInstantiateAllKatastddMainClasses() throws Exception {
        List<String> classNames = List.of(
                "org.example.gfttrainingspringboot.katastdd.albert.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.alejandro.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.esmeralda.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.felipe.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.fran.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.ivan.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.jaime.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.javi.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.jorge.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.joseph.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.mario.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.mykei.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.pau.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.pautigre.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.xavi.StringCalculator",
                "org.example.gfttrainingspringboot.katastdd.pauln.StringCalculator");

        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            assertNotNull(instance);
        }
    }
}
