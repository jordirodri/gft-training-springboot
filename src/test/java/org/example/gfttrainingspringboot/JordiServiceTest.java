package org.example.gfttrainingspringboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JordiServiceTest {

    @Test
    void getInfo() {

        JordiService result = new JordiService();

        assertEquals("test getInfo", result.getInfo());
    }
}