package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class KalkulatorBMITest {

    @Test
    public void testCalculateBMI() {

        //przykładowe dane testowe
        double weight = 80.0;
        double height = 1.74;

        //wywoływanie metody do testowania
        double result = KalkulatorBMI.calculateBMI(weight, height);

        // Dopuszczalna różnica (epsilon) na drugiej pozycji po przecinku
        double epsilon = 0.01;

        //sprawdzenie oczekiwanych wyników
        assertEquals(26.42, result, epsilon);  //oczekiwane BMI dla tych danych testowych
    }
}