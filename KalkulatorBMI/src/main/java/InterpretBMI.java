

public class InterpretBMI {
    public static void calculateBMI(double bmi) {
        System.out.print("Interpretacja BMI: ");

        if (bmi < 18.5) {
            System.out.println("Niedowaga");
        } else if (bmi < 24.9) {
            System.out.println("Waga normalna");
        } else if (bmi < 29.9) {
            System.out.println("Nadwaga");
        } else {
            System.out.println("Otyłość");
        }
    }
}