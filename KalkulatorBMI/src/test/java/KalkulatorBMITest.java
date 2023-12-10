import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.example.KalkulatorBMI;

public class KalkulatorBMITest {

    @Test
    public void testCalculateBMI() {

        //przykładowe dane testowe
        double weight = 80.0;
        double height = 1.74;

        //wywoływanie metody do testowania
        double result = KalkulatorBMI.calculateBMI(weight, height);

        //sprawdzenie oczekiwanych wyników
        assertEquals(22.86, result, 0.01);  //oczekiwane BMI dla tych danych testowych
    }
}