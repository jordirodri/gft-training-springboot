package org.example.gfttrainingspringboot.katastdd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class KatastddCoverageTest {

    @Test
    void shouldInstantiateAllKatastddMainClasses() throws Exception {
        String basePath = "org/example/gfttrainingspringboot/katastdd";
        URI uri = Thread.currentThread().getContextClassLoader().getResource(basePath).toURI();

        List<String> classNames;
        try (Stream<Path> pathStream = Files.walk(Path.of(uri))) {
            classNames = pathStream
                    .filter(path -> path.toString().endsWith(".class"))
                    .filter(path -> !path.getFileName().toString().contains("$"))
                    .map(path -> {
                        String relative = Path.of(uri).relativize(path).toString();
                        return "org.example.gfttrainingspringboot.katastdd."
                                + relative.replace('\\', '.').replace('/', '.').replace(".class", "");
                    })
                    .filter(className -> !className.endsWith("Test"))
                    .collect(Collectors.toList());
        }

        assertFalse(classNames.isEmpty(), "No se han encontrado clases en katastdd para cubrir");

        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            assertNotNull(instance);
        }
    }
}
