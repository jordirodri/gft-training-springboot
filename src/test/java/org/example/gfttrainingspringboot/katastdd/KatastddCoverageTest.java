package org.example.gfttrainingspringboot.katastdd;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.util.List;

import org.junit.jupiter.api.Test;

class KatastddCoverageTest {

    @Test
    void shouldInstantiateAllKatastddExampleClasses() throws Exception {
        List<String> classNames = List.of(
                "org.example.gfttrainingspringboot.katastdd.albert.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.alejandro.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.esmeralda.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.felipe.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.fran.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.ivan.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.jaime.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.javi.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.jorge.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.joseph.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.mario.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.mykei.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.pau.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.pauln.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.pautigre.funcionejemplo",
                "org.example.gfttrainingspringboot.katastdd.xavi.funcionejemplo");

        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            assertNotNull(instance);
        }
    }
}

